package SavourTheFlavourMain;

import java.util.Scanner;

public class MenuHandler {

    public static void showMainMenu(Scanner scanner, User user) {
        boolean inMenu = true;

        while (inMenu && user != null) {
            System.out.println("\n========================================");
            System.out.println(" Main Menu (Logged in as: " + user.getUserName() + ")");
            System.out.println("========================================");
            System.out.println("1. Recipe Management");
            System.out.println("2. Health Tools");
            System.out.println("3. Account Options");
            System.out.println("0. Exit");
            System.out.print("\nSelect an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> RecipeMenuHandler.RecipeMenuHandle(scanner, user);
                case "2" -> HealthMenuHandler.HealthMenuHandle(scanner);
                case "3" -> AccountMenuHandler.AccountMenuHandle(scanner);
                case "0" -> {
                    System.out.println("\n Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("\n Invalid option.");
            }
        }
    }
}
