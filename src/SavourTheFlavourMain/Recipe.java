package SavourTheFlavourMain;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String name;
    private String type;
    private int calories;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe(String name, String type, int calories, List<Ingredient> ingredients, List<String> steps) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getCalories() {
        return calories;
    }
    public String getname(){
        return name;
    }
    public String gettype(){
        return type;
    }
    public List<Ingredient> getIngredients(){
        return ingredients;
    }
    public List<String> getsteps(){
        return steps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(30)).append("\n");
        sb.append("  RECIPE: ").append(name).append("\n");
        sb.append(" Type: ").append(type).append(" |  Calories: ").append(calories).append("\n");
        sb.append("-".repeat(30)).append("\n");
        sb.append(" Ingredients:\n");
        for (Ingredient ing : ingredients) {
            String status = ing.isHealthy() ? "Healthy" : "Unhealthy (Swap: " + ing.getHealthierAlternative() + ")";
            sb.append(" - ").append(ing.getName()).append(", ").append(ing.getQuantity()).append(" ").append(ing.getUnit()).append(" (").append(status).append(")\n");
        }
        sb.append("-".repeat(30)).append("\n");
        sb.append(" Steps:\n");
        int count = 1;
        for (String step : steps) {
            sb.append(" ").append(count++).append(". ").append(step).append("\n");
        }
        sb.append("=".repeat(30));
        return sb.toString();
    }

}
