package SavourTheFlavourMain;

import java.util.*;

public class RecipeManager {

    public static void addNewRecipe() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();

        System.out.print("Enter recipe type (Breakfast, Lunch, Dinner): ");
        String type = scanner.nextLine();

        System.out.print("Enter total calories: ");
        int calories = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter number of ingredients: ");
        int numIngredients = Integer.parseInt(scanner.nextLine());

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            System.out.println("Ingredient " + (i + 1) + ":");
            System.out.print("Name: ");
            String ingName = scanner.nextLine();

            System.out.print("Quantity: ");
            double quantity = Double.parseDouble(scanner.nextLine());

            System.out.print("Unit: ");
            String unit = scanner.nextLine();

            System.out.print("Is this healthy? (yes/no): ");
            boolean isHealthy = scanner.nextLine().equalsIgnoreCase("yes");

            String alt = "";
            if (!isHealthy) {
                System.out.print("Suggest a healthier alternative: ");
                alt = scanner.nextLine();
            }

            ingredients.add(new Ingredient(ingName, quantity, unit, isHealthy, alt));
        }

        System.out.print("Enter number of steps: ");
        int stepCount = Integer.parseInt(scanner.nextLine());
        List<String> steps = new ArrayList<>();
        for (int i = 0; i < stepCount; i++) {
            System.out.print("Step " + (i + 1) + ": ");
            steps.add(scanner.nextLine());
        }

        Recipe recipe = new Recipe(name, type, calories, ingredients, steps);
        RecipeFileManager.saveRecipes(recipe, "recipes.txt");
        System.out.println("Recipe added successfully!");
    }

    public static void viewAllRecipes() {
        List<Recipe> recipeList = RecipeFileManager.loadRecipes("recipes.txt");
        if (recipeList.isEmpty()) {
            System.out.println("No recipes found.");
            return;
        }

        System.out.println("All Recipes:");
        for (Recipe r : recipeList) {
            System.out.println("------------");
            System.out.println(r);
        }
    }

    public static void editRecipe(Scanner scanner) {
        List<Recipe> recipes = RecipeFileManager.loadRecipes("recipes.txt");
        if (recipes.isEmpty()) {
            System.out.println("No recipes to edit.");
            return;
        }
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getname());
        }
        System.out.print("Enter the number of the recipe to edit: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= recipes.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Recipe oldRecipe = recipes.get(index);
        System.out.println("Editing Recipe: " + oldRecipe.getname());
        System.out.print("Enter new name (leave blank to keep '" + oldRecipe.getname() + "'): ");
        String name = scanner.nextLine();
        if (name.isBlank()) name = oldRecipe.getname();
        System.out.print("Enter new type (leave blank to keep '" + oldRecipe.gettype() + "'): ");
        String type = scanner.nextLine();
        if (type.isBlank()) type = oldRecipe.gettype();
        System.out.print("Enter new calories (or -1 to keep " + oldRecipe.getCalories() + "): ");
        int calories = Integer.parseInt(scanner.nextLine());
        if (calories < 0) calories = oldRecipe.getCalories();
        System.out.print("Do you want to add new ingredients? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many new ingredients to add? ");
            int newCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < newCount; i++) {
                System.out.println("New Ingredient " + (i + 1) + ":");
                System.out.print("Name: ");
                String ingName = scanner.nextLine();

                System.out.print("Quantity: ");
                double quantity = Double.parseDouble(scanner.nextLine());

                System.out.print("Unit: ");
                String unit = scanner.nextLine();

                System.out.print("Is this healthy? (yes/no): ");
                boolean isHealthy = scanner.nextLine().equalsIgnoreCase("yes");

                String alt = "";
                if (!isHealthy) {
                    System.out.print("Suggest a healthier alternative: ");
                    alt = scanner.nextLine();
                }

                oldRecipe.getIngredients().add(new Ingredient(ingName, quantity, unit, isHealthy, alt));
            }
        }
        System.out.print("Do you want to add new steps? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many new steps to add? ");
            int addSteps = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < addSteps; i++) {
                System.out.print("Step " + (oldRecipe.getsteps().size() + 1) + ": ");
                oldRecipe.getsteps().add(scanner.nextLine());
            }
        }
        Recipe updatedRecipe = new Recipe(name, type, calories, oldRecipe.getIngredients(), oldRecipe.getsteps());
        recipes.set(index, updatedRecipe);
        RecipeFileManager.saveAllRecipes(recipes, "recipes.txt");
        System.out.println("Recipe updated successfully.");
    }

    public static void deleteRecipe(Scanner scanner) {
        List<Recipe> recipes = RecipeFileManager.loadRecipes("recipes.txt");
        if (recipes.isEmpty()) {
            System.out.println("No recipes to delete.");
            return;
        }
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getname());
        }
        System.out.print("Enter the number of the recipe to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= recipes.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Recipe removed = recipes.remove(index);
        RecipeFileManager.saveAllRecipes(recipes, "recipes.txt");
        System.out.println("Deleted: " + removed.getname());
    }
}
