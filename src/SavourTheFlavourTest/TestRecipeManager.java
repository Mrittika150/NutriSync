package SavourTheFlavourTest;
import SavourTheFlavourMain.RecipeManager;
import SavourTheFlavourMain.Recipe;
import SavourTheFlavourMain.Ingredient;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestRecipeManager {
    private static final String TEST_TXT = "test_recipes.txt";

    @BeforeEach
    void clearFile() {
        File file = new File(TEST_TXT);
        if (file.exists()) file.delete();
    }

    @Test
    void testSaveAndLoadFromReadableTextFile() {
        Ingredient i1 = new Ingredient("Oats", 100, "grams", true, "");
        Ingredient i2 = new Ingredient("Sugar", 2, "tbsp", false, "Honey");

        Recipe r = new Recipe(
                "Oatmeal",
                "Breakfast",
                250,
                List.of(i1, i2),
                List.of("Boil water", "Add oats and sugar", "Cook for 5 mins")
        );

        RecipeManager.saveRecipeToTextFile(r, TEST_TXT);
        List<Recipe> loaded = RecipeManager.loadRecipesFromTextFile(TEST_TXT);

        assertEquals(1, loaded.size());
        Recipe loadedRecipe = loaded.get(0);
        assertEquals("Oatmeal", loadedRecipe.getname());
        assertEquals("Breakfast", loadedRecipe.gettype());
        assertEquals(250, loadedRecipe.getCalories());

        List<Ingredient> ings = loadedRecipe.getIngredients();
        assertEquals(2, ings.size());
        assertEquals("Sugar", ings.get(1).getName());
        assertFalse(ings.get(1).isHealthy());
        assertEquals("Honey)", ings.get(1).getHealthierAlternative());

        assertEquals(3, loadedRecipe.getsteps().size());
        assertEquals("Boil water", loadedRecipe.getsteps().get(0));
    }

    @Test
    void testViewAllRecipes_doesNotCrash() {

        Ingredient i1 = new Ingredient("Bread", 2, "slices", true, "");
        Recipe r = new Recipe("Toast", "Breakfast", 200, List.of(i1), List.of("Toast the bread"));
        RecipeManager.saveRecipeToTextFile(r, TEST_TXT);


        List<Recipe> loaded = RecipeManager.loadRecipesFromTextFile(TEST_TXT);
        assertDoesNotThrow(() -> {
            for (Recipe recipe : loaded) {
                System.out.println(recipe.toString());
            }
        });
    }

    @Test
    void testSaveCreatesFileIfNotExists() {
        File file = new File(TEST_TXT);
        assertFalse(file.exists());

        Ingredient i1 = new Ingredient("Apple", 1, "unit", true, "");
        Recipe r = new Recipe("Apple Snack", "Snack", 80, List.of(i1), List.of("Wash apple", "Eat"));

        RecipeManager.saveRecipeToTextFile(r, TEST_TXT);

        assertTrue(file.exists());
    }

    @Test
    void testLoadFromEmptyFileReturnsEmptyList() throws IOException {
        File file = new File(TEST_TXT);
        file.createNewFile();

        List<Recipe> recipes = RecipeManager.loadRecipesFromTextFile(TEST_TXT);
        assertNotNull(recipes);
        assertTrue(recipes.isEmpty());
    }
}
