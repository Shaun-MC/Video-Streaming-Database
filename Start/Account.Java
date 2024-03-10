/**
 * CSS 475 Team Project
 * @ team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @ version 3/8/2024
 */

 import java.sql.SQLException;

 public class Actor {
  
    public static final String createAccount = "createAccount";

    /**
      * Creates a new Account and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static void createAccount(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("createAccount - creates an Account and adds them to the DB");
            System.out.println("Command: createAccount <ProfilePicID> <firstName> <lastName> <userName> <email>");

        } else { 
            
            // Execute API

           try {

                MovieDB.createAccount(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}