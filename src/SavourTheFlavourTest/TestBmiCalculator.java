package SavourTheFlavourTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import SavourTheFlavourMain.BmiCalculator;

public class TestBmiCalculator {
    @Test
    public void testBMICalculationSimple() {
        double bmi = BmiCalculator.calculateBMI(70, 170);
        assertEquals(24.22, bmi, 0.01);
    }
    @Test
    public void testBMICalculation_Imperial() {

        double weightKg = 154 * 0.453592;
        double heightCm = ((5 * 12) + 7) * 2.54;

        double bmi = BmiCalculator.calculateBMI(weightKg, heightCm);
        assertEquals(24.1, bmi, 0.05);
    }
}
