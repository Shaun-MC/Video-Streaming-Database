/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chris Chen
 * @version 3/8/2024
 */

 import java.sql.SQLException;

 public class ProfilePicture {
 
    public static final String insertProfilePicture = "insertProfilePicture";

    /**
      * Creates a new ProfilePicture row and inserts it into the DB
      *
      * @author: Shaun Cushman
      */

    public static void insertProfilePicture(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("insertProfilePicture - creates a new movie and adds it the DB");
            System.out.println("Command: insertProfilePicture <file_path>");

        } else { 
            
            // Execute API

           try {

            MovieDB.insertProfilePicture(user_input[2]);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}