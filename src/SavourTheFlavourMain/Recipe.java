package SavourTheFlavourMain;

import java.util.List;

public class Recipe  {
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
    public String getname() {
        return name;
    }
    public String gettype() {
        return type;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public List<String> getsteps() {
        return steps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("║ %-66s ║\n", "RECIPE"));
        sb.append("╠════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-15s : %-48s ║\n", "Name", name));
        sb.append(String.format("║ %-15s : %-48s ║\n", "Type", type));
        sb.append(String.format("║ %-15s : %-48d ║\n", "Calories", calories));
        sb.append("╠════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-66s ║\n", "Ingredients"));
        for (Ingredient ing : ingredients) {
            String status = ing.isHealthy() ? "Healthy" : "Unhealthy → Swap: " + ing.getHealthierAlternative();
            String line = String.format("─ %-15s, %-6s (%s)", ing.getName(), ing.getQuantity() + " " + ing.getUnit(), status);
            if (line.length() > 66) {
                line = line.substring(0, 63) + "...";
            }
            sb.append(String.format("║ %-66s ║\n", line));
        }
        sb.append("╠════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-66s ║\n", "Steps"));
        int count = 1;
        for (String step : steps) {
            String stepLine = count++ + ". " + step;
            if (stepLine.length() > 66) {
                stepLine = stepLine.substring(0, 63) + "...";
            }
            sb.append(String.format("║ %-66s ║\n", stepLine));
        }
        sb.append("╚════════════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}