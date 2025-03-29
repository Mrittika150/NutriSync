package SavourTheFlavourTest;
import SavourTheFlavourMain.User;
import SavourTheFlavourMain.UserLogIn;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserLogIn {
    @Test
    public void testUserLogIn() {
        boolean expectedValue = true;
        User user = new User("Mrittika","Hijh&hsg","mrittika210@gmail.com");
        UserLogIn logIn = new UserLogIn();
        assertEquals(expectedValue,logIn.logIn(user));
    }

}
