package SavourTheFlavourMain;

import java.io.*;
import java.util.Scanner;

public class UserRegistration {
    private  static final String File_Name = "User_Info.txt";
    public static User getUserInputForRegistration(Scanner scanner) {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        return new User(username, password, email);
    }


    public static boolean registerUser(User user) {
        if(emailExists(user.getEmail())){
            System.out.println("Email Already registered under the name "+user.getUserName() + "!");
            return false;
        }
        try(BufferedWriter registerWriter = new BufferedWriter(new FileWriter(File_Name,true))){
            File file = new File(File_Name);
            if (file.length() > 0 && !fileEndsWithNewline(file)) {
                registerWriter.newLine();
            }
            registerWriter.write(user.getUserName()+","+user.getPassword()+","+user.getEmail());
            registerWriter.newLine();
            registerWriter.flush();
            System.out.println("You are sucessfully registered " + user.getUserName() + "!");
            return true;
        } catch (IOException e) {
            System.out.println("IO Exception occured while trying to write to the file " );
            e.printStackTrace();
            return false;
        }
    }

     public static boolean emailExists(String email) {
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
    private static boolean fileEndsWithNewline(File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            if (raf.length() == 0) return true; // empty file is fine
            raf.seek(raf.length() - 1);
            int lastByte = raf.read();
            return lastByte == '\n';
        }
    }

}
