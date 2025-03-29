package SavourTheFlavourMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserRegistration {
    private  static final String File_Name = "User_Info.txt";

    public boolean registerUser(User user) {
        if(emailExists(user.getUserName())){
            System.out.println("Email Already registered under the name "+user.getUserName() + "!");
            return false;
        }
        try(BufferedWriter registerWriter = new BufferedWriter(new FileWriter(File_Name,true))){
            registerWriter.write(user.getUserName()+","+user.getPassword()+","+user.getEmail());
            registerWriter.newLine();
            System.out.println("You are sucessfully registered " + user.getUserName() + "!");
            return true;
        } catch (IOException e) {
            System.out.println("IO Exception occured while trying to write to the file " );
            e.printStackTrace();
            return false;
        }
    }

    private boolean emailExists(String email) {
        try(BufferedReader emailReader = new BufferedReader(new FileReader(File_Name))){
            String line;
            while((line = emailReader.readLine()) != null){
                String[] userInfo = line.split(",");
                if(userInfo[2].equals(email)){
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception occured while trying to read from the file " );
            e.printStackTrace();
        } return false;
    }

}
