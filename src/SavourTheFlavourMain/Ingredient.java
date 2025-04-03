package SavourTheFlavourMain;
import java.io.Serializable;

public class Ingredient  implements Serializable{
    private String name;
    private double quantity;
    private String unit;
    private boolean isHealthy;
    private String healthierAlternative;
    public Ingredient(String name, double quantity, String unit, boolean isHealthy, String healthierAlternative) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.isHealthy = isHealthy;
        this.healthierAlternative = healthierAlternative;
    }
    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }
    public String getUnit() {
        return unit;
    }
    public boolean isHealthy() {
        return isHealthy;
    }
    public String getHealthierAlternative() {
        return healthierAlternative;
    }

    @Override
    public String toString() {
        String result = quantity + "" + unit + "" + name;
        if(!isHealthy){
            result += " (Consider using: " + healthierAlternative + ")";
        }
        return result;
    }
}
