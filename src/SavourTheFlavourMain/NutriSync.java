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
        boolean success = UserLogIn.logIn(userAttempt);
        if (success) {
            loggedInUser = userAttempt;
        }
    }

    private static void showMainMenu(Scanner scanner) {
        boolean inMenu = true;

        while (inMenu && loggedInUser != null) {
            System.out.println("\n🍴 Main Menu (Logged in as: " + loggedInUser.getUserName() + ")");
            System.out.println("1. Add New Recipe");
            System.out.println("2. View All Recipes");
            System.out.println("3. Logout");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addNewRecipeInteractive(scanner);
                case "2" -> viewRecipes();
                case "3" -> {
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
}
