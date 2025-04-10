package SavourTheFlavourMain;

import java.util.*;

public class NutriSync {

    private static final String RECIPE_FILE = "recipes.txt";
    private static User loggedInUser = null;

    public static void showBanner() {
        System.out.println("+============================+");
        System.out.println("|          NutriSync         |");
        System.out.println("|   Nourish. Track. Thrive.  |");
        System.out.println("+============================+\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        showBanner();
        boolean running = true;

        while (running) {
            System.out.println("\n👋 Welcome to NutriSync!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    User newUser = UserRegistration.getUserInputForRegistration(scanner);
                    UserRegistration.registerUser(newUser);
                }
                case "2" -> {
                    User userAttempt = UserLogIn.getUserInputForLogin(scanner);
                    String loginResult = UserLogIn.checkLoginStatus(userAttempt);
                    if (loginResult.equals("success")) {
                        loggedInUser = userAttempt;
                        System.out.println("✅ Welcome " + userAttempt.getUserName() + "!");
                        MenuHandler.showMainMenu(scanner, loggedInUser);
                    } else {
                        System.out.println("❌ " + loginResult);
                    }
                }
                case "0" -> {
                    System.out.println("👋 Goodbye!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    public static void logout() {
        loggedInUser = null;
        System.out.println("🔓 Logged out.");
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static String getRecipeFile() {
        return RECIPE_FILE;
    }
}
