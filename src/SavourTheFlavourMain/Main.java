package SavourTheFlavourMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "recipes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n🍴 Welcome to Savour the Flavour!");
            System.out.println("1. Add New Recipe");
            System.out.println("2. View All Recipes");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> addNewRecipeInteractive(scanner);
                case 2 -> viewRecipes();
                case 0 -> System.out.println("👋 Goodbye!");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (choice != 0);
    }

    private static void addNewRecipeInteractive(Scanner scanner) {
        System.out.print("Recipe name: ");
        String name = scanner.nextLine();

        System.out.print("Type (Breakfast, Lunch, Dinner, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Calories: ");
        int calories = Integer.parseInt(scanner.nextLine());

        System.out.print("Number of ingredients: ");
        int ingCount = Integer.parseInt(scanner.nextLine());

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingCount; i++) {
            System.out.println("Ingredient " + (i + 1) + ":");
            System.out.print("Name: ");
            String ingName = scanner.nextLine();

            System.out.print("Quantity: ");
            double quantity = Double.parseDouble(scanner.nextLine());

            System.out.print("Unit: ");
            String unit = scanner.nextLine();

            System.out.print("Is it healthy? (yes/no): ");
            boolean isHealthy = scanner.nextLine().equalsIgnoreCase("yes");

            String alt = "";
            if (!isHealthy) {
                System.out.print("Healthier alternative: ");
                alt = scanner.nextLine();
            }

            ingredients.add(new Ingredient(ingName, quantity, unit, isHealthy, alt));
        }

        System.out.print("Number of steps: ");
        int stepCount = Integer.parseInt(scanner.nextLine());

        List<String> steps = new ArrayList<>();
        for (int i = 0; i < stepCount; i++) {
            System.out.print("Step " + (i + 1) + ": ");
            steps.add(scanner.nextLine());
        }

        Recipe recipe = new Recipe(name, type, calories, ingredients, steps);
        RecipeManager.saveRecipeToTextFile(recipe, FILE_NAME);
        System.out.println("✅ Recipe saved successfully!");
    }

    private static void viewRecipes() {
        List<Recipe> recipes = RecipeManager.loadRecipesFromTextFile(FILE_NAME);
        if (recipes.isEmpty()) {
            System.out.println("📭 No recipes found.");
            return;
        }

        System.out.println("📚 All Recipes:\n");
        for (Recipe r : recipes) {
            System.out.println(r);
            System.out.println("-------------------------------------------------");
        }
    }
}
