package SavourTheFlavourTest;
import SavourTheFlavourMain.UserLogIn;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserLogIn {
    @Test
    public void testUserLogIn() {
        boolean expectedValue = true;
        UserLogIn user = new UserLogIn("Mrittika","Hijh&hsg","mrittika@gmail.com");
        assertEquals(expectedValue,user.logIn());
    }

}
