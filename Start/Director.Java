/**
 * CSS 475 Team Project
 * @ team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @ version 3/8/2024
 */

 import java.sql.SQLException;

public class Director {
 
    public static final String createDirector = "createGenre";
    public static final String listAllDirectors = "listAllDirectors";

    /**
      * Creates a new Director and adds it to the DB
      *
      * @author: Shyam Ramesh 
      */

    public static boolean createDirector(String [] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("createDirector - creates a new Director and adds it the DB");
            System.out.println("Command: createDirector <firstName> <lastName");

        } else { 
            
            // Execute API

           try {

            MovieDB.createDirector(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }

        return true;
    }
}