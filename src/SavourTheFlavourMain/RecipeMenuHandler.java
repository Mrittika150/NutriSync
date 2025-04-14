

package SavourTheFlavourMain;

import java.util.List;
import java.util.Scanner;

public class RecipeMenuHandler {

    public static void RecipeMenuHandle(Scanner scanner, User user) {
        boolean back = false;
        while (!back) {
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║                 Recipe Management Menu                 ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add New Recipe                                      ║");
            System.out.println("║ 2. View All Recipes                                    ║");
            System.out.println("║ 3. Search Recipes                                      ║");
            System.out.println("║ 4. Edit a Recipe                                       ║");
            System.out.println("║ 5. Delete a Recipe                                     ║");
            System.out.println("║ 0. Back to Main Menu                                   ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("\nSelect an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> RecipeManager.addNewRecipe();
                case "2" -> RecipeManager.viewAllRecipes();
                case "3" -> searchRecipes(scanner);
                case "4" -> RecipeManager.editRecipe(scanner);
                case "5" -> RecipeManager.deleteRecipe(scanner);
                case "0" -> back = true;
                default -> System.out.println("\n Invalid option.");
            }

            System.out.print("\n Press ENTER to return...");
            scanner.nextLine();
        }
    }

    private static void searchRecipes(Scanner scanner) {
        RecipeSearcher searcher = new RecipeSearcher("recipes.txt");

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║               Search Recipes By Category               ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Name                                                ║");
        System.out.println("║ 2. Ingredient                                          ║");
        System.out.println("║ 3. Type                                                ║");
        System.out.println("║ 4. Under a Specific Calorie Goal                       ║");
        System.out.println("║ 0. Back to Main Menu                                   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("\nSelect an option: ");
        String option = scanner.nextLine();

        List<Recipe> results;
        if (option.equals("1")) {
            System.out.print("Enter Name: ");
            String Name = scanner.nextLine();
            results = searcher.searchByName(Name);
        } else if (option.equals("2")) {
            System.out.print("Enter Ingredient: ");
            String Ingredient = scanner.nextLine();
            results = searcher.searchByIngredient(Ingredient);
        } else if (option.equals("3")) {
            System.out.print("Enter Type: ");
            String Type = scanner.nextLine();
            results = searcher.searchByType(Type);
        } else if (option.equals("4")) {
            System.out.print("Enter maximum calorie limit: ");
            int limit = Integer.parseInt(scanner.nextLine());
            results = searcher.searchByCalorie(limit);
        } else {
            System.out.println("\n Invalid option.");
            return;
        }

        if (results.isEmpty()) {
            System.out.println("\n No matching recipes found.");
        } else {
            System.out.println("\n Matching Recipes:\n");
            for (Recipe recipe : results) {
                System.out.println(recipe);
                System.out.println("-------------------------------------------------");
            }
        }
    }
}
