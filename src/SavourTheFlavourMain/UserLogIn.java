package SavourTheFlavourMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLogIn {
    private String userName;
    private String password;
    private String email;

    public UserLogIn(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
     public boolean logIn(){
        try(BufferedReader logInbr = new BufferedReader(new FileReader("User_Info.txt"))){
            String line;
            while((line = logInbr.readLine()) != null){
                String[] userInfo = line.split(",");
                if(userInfo[0].equals(this.userName) && userInfo[1].equals(this.password)){
                    System.out.println("Welcome " + this.userName + "!");
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
