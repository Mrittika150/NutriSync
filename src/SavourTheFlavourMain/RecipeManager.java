package SavourTheFlavourMain;

import java.io.*;
import java.util.*;

public class RecipeManager {
    private static final String FILE_NAME = "recipes.ser";

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
        saveRecipe(recipe);
        System.out.println("✅ Recipe added successfully!");
    }

    private static void saveRecipe(Recipe recipe) {
        List<Recipe> recipeList = loadRecipes();
        recipeList.add(recipe);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(recipeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Recipe> loadRecipes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Recipe>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void viewAllRecipes() {
        List<Recipe> recipeList = loadRecipes();
        if (recipeList.isEmpty()) {
            System.out.println("📭 No recipes found.");
            return;
        }

        System.out.println("📚 All Recipes:");
        for (Recipe r : recipeList) {
            System.out.println("------------");
            System.out.println(r);
        }
    }

    public static void saveRecipeToFile(Recipe recipe, String fileName) {
        List<Recipe> recipeList = loadRecipesFromFile(fileName);
        recipeList.add(recipe);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(recipeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Recipe> loadRecipesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Recipe>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveRecipeToTextFile(Recipe recipe, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("#Recipe\n");
            writer.write("Name: " + recipe.getname() + "\n");
            writer.write("Type: " + recipe.gettype() + "\n");
            writer.write("Calories: " + recipe.getCalories() + "\n\n");

            writer.write("Ingredients (" + recipe.getIngredients().size() + "):\n");
            for (Ingredient ing : recipe.getIngredients()) {
                String status = ing.isHealthy() ? "Healthy" : "Unhealthy (Swap: " + ing.getHealthierAlternative() + ")";
                writer.write(" - " + ing.getName() + ", " + ing.getQuantity() + " " + ing.getUnit() + " (" + status + ")\n");
            }

            writer.write("\nSteps (" + recipe.getsteps().size() + "):\n");
            int stepNum = 1;
            for (String step : recipe.getsteps()) {
                writer.write(" " + stepNum++ + ". " + step + "\n");
            }

            writer.write("\n"); // extra space between recipes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Recipe> loadRecipesFromTextFile(String fileName) {
        List<Recipe> recipes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("#Recipe")) {
                    String name = reader.readLine().split(": ")[1].trim();
                    String type = reader.readLine().split(": ")[1].trim();
                    int calories = Integer.parseInt(reader.readLine().split(": ")[1].trim());

                    reader.readLine(); // skip blank line

                    int ingCount = Integer.parseInt(reader.readLine().replaceAll("[^0-9]", ""));
                    List<Ingredient> ingredients = new ArrayList<>();

                    for (int i = 0; i < ingCount; i++) {
                        String ingLine = reader.readLine().trim().substring(2); // remove " - "
                        String[] parts = ingLine.split(", ");
                        String ingName = parts[0];

                        // Extract quantity and unit
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

                    reader.readLine(); // skip blank line

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
    public static void editRecipe(Scanner scanner) {
        List<Recipe> recipes = loadRecipesFromTextFile("recipes.txt");

        if (recipes.isEmpty()) {
            System.out.println("📭 No recipes to edit.");
            return;
        }

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getname());
        }

        System.out.print("Enter the number of the recipe to edit: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= recipes.size()) {
            System.out.println("❌ Invalid selection.");
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

        Recipe newRecipe = new Recipe(name, type, calories, oldRecipe.getIngredients(), oldRecipe.getsteps());
        recipes.set(index, newRecipe);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("recipes.ser"))) {
            out.writeObject(recipes);
            System.out.println("✅ Recipe updated successfully.");
        } catch (IOException e) {
            System.out.println("❌ Failed to update recipe.");
            e.printStackTrace();
        }
    }

    public static void deleteRecipe(Scanner scanner) {
        List<Recipe> recipes = loadRecipesFromTextFile("recipes.txt");

        if (recipes.isEmpty()) {
            System.out.println("📭 No recipes to delete.");
            return;
        }

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getname());
        }

        System.out.print("Enter the number of the recipe to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= recipes.size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        Recipe removed = recipes.remove(index);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("recipes.ser"))) {
            out.writeObject(recipes);
            System.out.println("🗑️ Deleted: " + removed.getname());
        } catch (IOException e) {
            System.out.println("❌ Failed to delete recipe.");
            e.printStackTrace();
        }
    }




}
