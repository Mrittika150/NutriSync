package SavourTheFlavourMain;

import java.util.Scanner;
public class HealthMenuHandler {
    public static void HealthMenuHandle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                   Health Tools Menu                    ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Check BMI                                           ║");
            System.out.println("║ 2. Get Calorie Goal                                    ║");
            System.out.println("║ 3. Show Ingredient Swaps                               ║");
            System.out.println("║ 4. Daily Health Challenge                              ║");
            System.out.println("║ 0. Back to Main Menu                                   ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("\nSelect an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    BmiCalculator.evaluateBMIWithUnitChoice(scanner);
                    scanner.nextLine();
                }
                case "2" -> {
                    CalorieGoalRecommender.recommendCalorieGoal(scanner);
                    scanner.nextLine();
                }
                case "3" -> IngredientSwapper.suggestSwaps("recipes.txt");
                case "4" -> HealthChallengeProvider.showRandomChallenge();
                case "0" -> back = true;
                default -> System.out.println("\n Invalid option.");
            }

            System.out.print("\n Press ENTER to return...");
            scanner.nextLine();
        }
    }
}