package SavourTheFlavourTest;
import SavourTheFlavourMain.User;
import SavourTheFlavourMain.UserRegistration;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestUserRegistration {
    @Test
    public void testUserRegistration() {
         boolean expectedValue = true;
         User user = new User("Mirhimah","Hjfcbgg*9","mihrimah@gmail.com");
         UserRegistration registration = new UserRegistration();
         assertEquals(registration.registerUser(user), expectedValue);
    }
    @Test
    public void testUserRegistration2() {
        boolean expectedValue = true;
        User user = new User("Nafisa","coolnafisa$04","nafisa@gmail.com");
        UserRegistration registration = new UserRegistration();
        assertEquals(registration.registerUser(user), expectedValue);
    }

    @Test
    public void testEmailExists(){
        boolean expectedValue = true;
        UserRegistration registration = new UserRegistration();
        assertEquals(registration.emailExists("mihrimah@gmail.com"), expectedValue);
    }
}
