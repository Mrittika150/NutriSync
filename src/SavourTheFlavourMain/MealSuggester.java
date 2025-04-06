package SavourTheFlavourMain;


import java.util.*;

public class MealSuggester {
    public static void suggestMeals(Scanner scanner, String recipeFilePath) {
        System.out.print("Enter your target calorie limit: ");
        int target = Integer.parseInt(scanner.nextLine());

        List<Recipe> recipes = RecipeManager.loadRecipesFromTextFile(recipeFilePath);
        List<Recipe> suggestions = new ArrayList<>();

        for (Recipe r : recipes) {
            if (r.getCalories() <= target) {
                suggestions.add(r);
            }
        }

        if (suggestions.isEmpty()) {
            System.out.println("🚫 No meals found under that calorie limit.");
        } else {
            System.out.println("\n🍽️ Meals under " + target + " calories:");
            for (Recipe r : suggestions) {
                System.out.println("-------------------");
                System.out.println(r);
            }
        }
    }
}
