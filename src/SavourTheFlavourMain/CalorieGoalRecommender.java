package SavourTheFlavourMain;

import java.util.Scanner;

public class CalorieGoalRecommender {
    public static void recommendCalorieGoal(Scanner scanner) {
        System.out.println("\n Let's calculate your daily calorie goal!");

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter your gender (M/F): ");
        String gender = scanner.nextLine().toUpperCase();

        System.out.print("Enter your weight in kg: ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter your height in cm: ");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.println("Select your activity level:");
        System.out.println("1. Sedentary\n2. Lightly active\n3. Moderately active\n4. Very active");
        int activity = Integer.parseInt(scanner.nextLine());

        System.out.println("What's your goal?\n1. Lose weight\n2. Maintain weight\n3. Gain weight");
        int goal = Integer.parseInt(scanner.nextLine());

        double bmr;
        if (gender.equals("M")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        }
        else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        double activityMultiplier;
        switch (activity) {
            case 1 -> activityMultiplier = 1.2;
            case 2 -> activityMultiplier = 1.375;
            case 3 -> activityMultiplier = 1.55;
            case 4 -> activityMultiplier = 1.725;
            default -> activityMultiplier = 1.2;
        }

        double maintenanceCalories = bmr * activityMultiplier;
        double goalCalories = switch (goal) {
            case 1 -> maintenanceCalories - 500;
            case 3 -> maintenanceCalories + 500;
            default -> maintenanceCalories;
        };

        System.out.printf(" Your estimated daily calorie goal is: %.0f calories.%n", goalCalories);
    }
}

