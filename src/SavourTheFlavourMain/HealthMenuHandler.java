package SavourTheFlavourMain;

import java.util.Scanner;

public class HealthMenuHandler {

    public static void handle(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n🧘 Health Tools");
            System.out.println("1. Check BMI");
            System.out.println("2. Get Calorie Goal");
            System.out.println("3. Show Ingredient Swaps");
            System.out.println("4. Daily Health Challenge");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> BmiCalculator.evaluateBMIWithUnitChoice(scanner);
                case "2" -> CalorieGoalRecommender.recommendCalorieGoal(scanner);
                case "3" -> IngredientSwapper.suggestSwaps("recipes.txt");
                case "4" -> HealthChallengeProvider.showRandomChallenge();
                case "0" -> back = true;
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }
}
