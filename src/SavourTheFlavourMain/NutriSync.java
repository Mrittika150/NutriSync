package SavourTheFlavourMain;

import SavourTheFlavourMain.Ingredient;
import SavourTheFlavourMain.Recipe;
import SavourTheFlavourMain.RecipeManager;
import SavourTheFlavourMain.User;

import java.util.*;
import java.io.*;

public class NutriSync {
    private static final String USER_FILE = "User_info.txt";
    private static final String RECIPE_FILE = "recipes.txt";

    private static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n👋 Welcome to NutriSync!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> signUp(scanner);
                case "2" -> login(scanner);
                case "0" -> {
                    System.out.println("👋 Goodbye!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }

            if (loggedInUser != null) {
                showMainMenu(scanner);
            }
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User newUser = new User(username, password, email);
        UserRegistration.registerUser(newUser);
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User userAttempt = new User(username, password, email);

        String loginResult = UserLogIn.checkLoginStatus(userAttempt);
        if (loginResult.equals("success")) {
            loggedInUser = userAttempt;
            System.out.println("✅ Welcome " + username + "!");
        } else {
            System.out.println("❌ " + loginResult);
        }
    }

    private static void showMainMenu(Scanner scanner) {
        boolean inMenu = true;

        while (inMenu && loggedInUser != null) {
            System.out.println("\n🍴 Main Menu (Logged in as: " + loggedInUser.getUserName() + ")");
            System.out.println("1. Add New Recipe");
            System.out.println("2. View All Recipes");
            System.out.println("3. Search Recipes");
            System.out.println("4. Check BMI");
            System.out.println("5. Get Calorie Goal");
            System.out.println("6. Suggest Meals by Calorie Goal");
            System.out.println("7. Show Healthier Ingredient Swaps");
            System.out.println("8. Daily Health Challenge");
            System.out.println("9. Logout");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addNewRecipeInteractive(scanner);
                case "2" -> viewRecipes();
                case "3" -> searchRecipes(scanner);
                case "4" -> BmiCalculator.evaluateBMIWithUnitChoice(scanner);
                case "5" -> CalorieGoalRecommender.recommendCalorieGoal(scanner);
                case "6" -> MealSuggester.suggestMeals(scanner,RECIPE_FILE);
                case "7" -> IngredientSwapper.suggestSwaps(RECIPE_FILE);
                case "8" -> HealthChallengeProvider.showRandomChallenge();
                case "9" -> {
                    loggedInUser = null;
                    System.out.println("🔓 Logged out.");
                    inMenu = false;
                }
                case "0" -> {
                    System.out.println("👋 Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void addNewRecipeInteractive(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("🚫 You must be logged in to add recipes.");
            return;
        }

        RecipeManager.addNewRecipe();

        System.out.print("↩️ Press ENTER to return to menu...");
        scanner.nextLine();
    }

    private static void viewRecipes() {
        if (loggedInUser == null) {
            System.out.println("🚫 You must be logged in to view recipes.");
            return;
        }

        RecipeManager.viewAllRecipes();

        System.out.print("↩️ Press ENTER to return to menu...");
        new Scanner(System.in).nextLine();
    }
    private static void searchRecipes(Scanner scanner) {
        if (loggedInUser == null) {
            System.out.println("🚫 You must be logged in to search recipes.");
            return;
        }

        RecipeSearcher searcher = new RecipeSearcher(RECIPE_FILE);

        System.out.println("\n🔎 Search Recipes By:");
        System.out.println("1. Name");
        System.out.println("2. Ingredient");
        System.out.print("Select an option: ");
        String option = scanner.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Recipe> results;
        if (option.equals("1")) {
            results = searcher.searchByName(keyword);
        } else if (option.equals("2")) {
            results = searcher.searchByIngredient(keyword);
        } else {
            System.out.println("❌ Invalid option.");
            return;
        }

        if (results.isEmpty()) {
            System.out.println("📭 No matching recipes found.");
        } else {
            System.out.println("\n🍽️ Matching Recipes:\n");
            for (Recipe recipe : results) {
                System.out.println(recipe);
                System.out.println("-------------------------------------------------");
            }
        }

        System.out.print("↩️ Press ENTER to return to menu...");
        scanner.nextLine();
    }

}
