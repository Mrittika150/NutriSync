package SavourTheFlavourMain;

import java.util.Scanner;

public class AccountMenuHandler {

    public static void AccountMenuHandle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n Account Options");
            System.out.println("1. Logout");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    NutriSync.logout();
                    back = true;
                }
                case "0" -> back = true;
                default -> System.out.println(" Invalid option.");
            }
        }
    }
}
