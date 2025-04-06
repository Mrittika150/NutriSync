package SavourTheFlavourMain;

import java.util.*;

public class IngredientSwapper {
    public static  void suggestSwaps(String recipeFilePath){
        List<Recipe> recipes = RecipeManager.loadRecipesFromTextFile(recipeFilePath);
        for (Recipe recipe : recipes) {
            boolean hasUnhealthy = false;
            StringBuilder stringBuilder = new StringBuilder();
            for (Ingredient ingredient : recipe.getIngredients()) {
                if(!ingredient.isHealthy() && !ingredient.getHealthierAlternative().isBlank()){
                    hasUnhealthy = true;
                    stringBuilder.append(ingredient.getName())
                            .append(" ➡ Swap with: ").append(ingredient.getHealthierAlternative()).append("\n");
                }
            }
            if(hasUnhealthy){
                System.out.println("\n🥦 Ingredient Swap Suggestions for: " + recipe.getname());
                System.out.println(stringBuilder);
            }
        }
    }
}
