package SavourTheFlavourMain;

import java.util.List;
import java.util.Scanner;

public class RecipeMenuHandler {

    public static void handle(Scanner scanner, User user) {
        boolean back = false;
        while (!back) {
            System.out.println("\n👨‍🍳 Recipe Management");
            System.out.println("1. Add New Recipe");
            System.out.println("2. View All Recipes");
            System.out.println("3. Search Recipes");
            System.out.println("4. Edit a Recipe");
            System.out.println("5. Delete a Recipe");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> RecipeManager.addNewRecipe();
                case "2" -> RecipeManager.viewAllRecipes();
                case "3" -> searchRecipes(scanner);
                case "4" -> RecipeManager.editRecipe(scanner);
                case "5" -> RecipeManager.deleteRecipe(scanner);
                case "0" -> back = true;
                default -> System.out.println("❌ Invalid option.");
            }

            System.out.print("↩️ Press ENTER to return...");
            scanner.nextLine();
        }
    }

    private static void searchRecipes(Scanner scanner) {
        RecipeSearcher searcher = new RecipeSearcher("recipes.txt");

        System.out.println("\n🔎 Search Recipes By:");
        System.out.println("1. Name");
        System.out.println("2. Ingredient");
        System.out.println("3. Type");
        System.out.println("4. Under a Specific Calorie Goal");
        System.out.print("Select an option: ");
        String option = scanner.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Recipe> results;
        if (option.equals("1")) {
            results = searcher.searchByName(keyword);
        } else if (option.equals("2")) {
            results = searcher.searchByIngredient(keyword);
        } else if (option.equals("3")) {
            results = searcher.searchByType(keyword);
        } else if (option.equals("4")) {
            System.out.print("Enter maximum calorie limit: ");
            int limit = Integer.parseInt(scanner.nextLine());
            results = searcher.searchByCalorie(limit);
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
    }
}
