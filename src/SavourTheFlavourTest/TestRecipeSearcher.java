package SavourTheFlavourTest;
import SavourTheFlavourMain.Recipe;
import SavourTheFlavourMain.RecipeSearcher;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestRecipeSearcher {
    private static final String TEST_RECIPE_FILE = "recipes.txt";
    @Test
    public void testRecipeSearchByName() {
        RecipeSearcher searcher = new RecipeSearcher(TEST_RECIPE_FILE);
        List<Recipe> results = searcher.searchByName("Omlete");

        assertFalse(results.isEmpty(), "Should find recipes with 'smoothie' in the name");
    }
    @Test
    public void testSearchByIngredient() {
        RecipeSearcher searcher = new RecipeSearcher(TEST_RECIPE_FILE);
        List<Recipe> results = searcher.searchByIngredient("eggs");

        assertFalse(results.isEmpty(), "Should find recipes containing 'banana'");
    }

}
