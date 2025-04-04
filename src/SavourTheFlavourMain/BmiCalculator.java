package SavourTheFlavourMain;

import java.util.Scanner;

public class BmiCalculator {

    public static double calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    public static String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    public static void evaluateBMI(double weightKg, double heightCm) {
        double bmi = calculateBMI(weightKg, heightCm);
        String category = getBMICategory(bmi);

        System.out.printf("📊 Your BMI is: %.2f (%s)%n", bmi, category);

        double heightM = heightCm / 100.0;
        double normalMin = 18.5 * heightM * heightM;
        double normalMax = 24.9 * heightM * heightM;

        switch (category) {
            case "Underweight" -> {
                double gainNeeded = normalMin - weightKg;
                System.out.printf("⚠️ You are underweight. You should gain at least %.1f kg to reach a normal BMI.%n", gainNeeded);
            }
            case "Overweight", "Obese" -> {
                double loseNeeded = weightKg - normalMax;
                System.out.printf("⚠️ You are %s. You should lose at least %.1f kg to reach a normal BMI.%n", category.toLowerCase(), loseNeeded);
            }
            default -> System.out.println("✅ You are fit! Keep it up!");
        }
    }

    public static void evaluateBMIWithUnitChoice(Scanner scanner) {
        System.out.println("Choose unit system:");
        System.out.println("1. Metric (kg, cm)");
        System.out.println("2. Imperial (lb, ft/in)");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        double weightKg = 0, heightCm = 0;

        if (choice.equals("1")) {
            System.out.print("Enter your weight (kg): ");
            weightKg = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter your height (cm): ");
            heightCm = Double.parseDouble(scanner.nextLine());
        } else if (choice.equals("2")) {
            System.out.print("Enter your weight (pounds): ");
            double weightLb = Double.parseDouble(scanner.nextLine());
            weightKg = weightLb * 0.453592;

            System.out.print("Enter your height - feet: ");
            int feet = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter your height - inches: ");
            int inches = Integer.parseInt(scanner.nextLine());

            int totalInches = feet * 12 + inches;
            heightCm = totalInches * 2.54;
        } else {
            System.out.println("❌ Invalid choice.");
            return;
        }

        evaluateBMI(weightKg, heightCm);
    }
}

