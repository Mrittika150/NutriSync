package SavourTheFlavourMain;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearcher {

    private final String recipeFilePath;

    public RecipeSearcher(String recipeFilePath) {
        this.recipeFilePath = recipeFilePath;
    }

    public List<Recipe> searchByName(String keyword) {
        List<Recipe> allRecipes = RecipeManager.loadRecipes(recipeFilePath);
        List<Recipe> matchedRecipes = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Recipe recipe : allRecipes) {
            if (recipe.getname().toLowerCase().contains(keyword)) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }
    public List<Recipe> searchByType(String typeKeyword) {
        List<Recipe> allRecipes = RecipeManager.loadRecipes(recipeFilePath);
        List<Recipe> matchedRecipes = new ArrayList<>();
        typeKeyword = typeKeyword.toLowerCase();

        for (Recipe recipe : allRecipes) {
            if (recipe.gettype().toLowerCase().contains(typeKeyword)) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }

    public List<Recipe> searchByIngredient(String ingredientKeyword) {
        List<Recipe> allRecipes = RecipeManager.loadRecipes(recipeFilePath);
        List<Recipe> matchedRecipes = new ArrayList<>();
        ingredientKeyword = ingredientKeyword.toLowerCase();

        for (Recipe recipe : allRecipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getName().toLowerCase().contains(ingredientKeyword)) {
                    matchedRecipes.add(recipe);
                    break;
                }
            }
        }

        return matchedRecipes;
    }
    public List<Recipe> searchByCalorie(int maxCalories) {
        List<Recipe> allRecipes = RecipeManager.loadRecipes(recipeFilePath);
        List<Recipe> calorieMatched = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            if (recipe.getCalories() <= maxCalories) {
                calorieMatched.add(recipe);
            }
        }

        return calorieMatched;
    }
}
