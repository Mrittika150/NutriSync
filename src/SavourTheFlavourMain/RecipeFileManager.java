package SavourTheFlavourMain;

import java.io.*;
import java.util.*;

public class RecipeFileManager {

    public static void saveRecipes(Recipe recipe, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("#Recipe\n");
            writer.write("Name: " + recipe.getname() + "\n");
            writer.write("Type: " + recipe.gettype() + "\n");
            writer.write("Calories: " + recipe.getCalories() + "\n\n");

            writer.write("Ingredients (" + recipe.getIngredients().size() + "):\n");
            for (Ingredient ing : recipe.getIngredients()) {
                String status = ing.isHealthy() ? "Healthy"
                        : "Unhealthy (Swap: " + ing.getHealthierAlternative() + ")";
                writer.write(" - " + ing.getName() + ", " + ing.getQuantity() + " " + ing.getUnit() + " (" + status + ")\n");
            }

            writer.write("\nSteps (" + recipe.getsteps().size() + "):\n");
            int stepNum = 1;
            for (String step : recipe.getsteps()) {
                writer.write(" " + stepNum++ + ". " + step + "\n");
            }

            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAllRecipes(List<Recipe> recipes, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Recipe recipe : recipes) {
                writer.write("#Recipe\n");
                writer.write("Name: " + recipe.getname() + "\n");
                writer.write("Type: " + recipe.gettype() + "\n");
                writer.write("Calories: " + recipe.getCalories() + "\n\n");

                writer.write("Ingredients (" + recipe.getIngredients().size() + "):\n");
                for (Ingredient ing : recipe.getIngredients()) {
                    String status = ing.isHealthy() ? "Healthy"
                            : "Unhealthy (Swap: " + ing.getHealthierAlternative() + ")";
                    writer.write(" - " + ing.getName() + ", " + ing.getQuantity() + " " + ing.getUnit() + " (" + status + ")\n");
                }

                writer.write("\nSteps (" + recipe.getsteps().size() + "):\n");
                int stepNum = 1;
                for (String step : recipe.getsteps()) {
                    writer.write(" " + stepNum++ + ". " + step + "\n");
                }

                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Recipe> loadRecipes(String fileName) {
        List<Recipe> recipes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("#Recipe")) {
                    String name = reader.readLine().split(": ")[1].trim();
                    String type = reader.readLine().split(": ")[1].trim();
                    int calories = Integer.parseInt(reader.readLine().split(": ")[1].trim());

                    reader.readLine(); // blank line

                    int ingCount = Integer.parseInt(reader.readLine().replaceAll("[^0-9]", ""));
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int i = 0; i < ingCount; i++) {
                        String ingLine = reader.readLine().trim().substring(2);
                        String[] parts = ingLine.split(", ");
                        String ingName = parts[0];
                        String[] qtyUnit = parts[1].split(" ");
                        double qty = Double.parseDouble(qtyUnit[0]);
                        String unit = qtyUnit[1];
                        boolean isHealthy = ingLine.contains("Healthy") && !ingLine.contains("Unhealthy");
                        String alt = "";
                        if (ingLine.contains("Unhealthy (Swap:")) {
                            int start = ingLine.indexOf("Swap: ") + 6;
                            int end = ingLine.lastIndexOf(")");
                            alt = ingLine.substring(start, end).trim();
                        }
                        ingredients.add(new Ingredient(ingName, qty, unit, isHealthy, alt));
                    }

                    reader.readLine(); // blank line
                    int stepCount = Integer.parseInt(reader.readLine().replaceAll("[^0-9]", ""));
                    List<String> steps = new ArrayList<>();
                    for (int i = 0; i < stepCount; i++) {
                        String stepLine = reader.readLine().trim();
                        steps.add(stepLine.substring(stepLine.indexOf(". ") + 2));
                    }

                    recipes.add(new Recipe(name, type, calories, ingredients, steps));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }
}
