package SavourTheFlavourMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLogIn{
     public boolean logIn(User user){
        try(BufferedReader logInbr = new BufferedReader(new FileReader("User_Info.txt"))){
            String line;
            while((line = logInbr.readLine()) != null){
                String[] userInfo = line.split(",");
                if(userInfo.length >= 2 && userInfo[1].equals(user.getPassword()) && userInfo[2].equals(user.getEmail())){
                    System.out.println("Welcome " + user.getUserName() + "!");
                    return true;
                }
                else {
                    System.out.println("Invalid username or password");
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
        return false;
     }

}
