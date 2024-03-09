/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chris Chen
 * @version 3/8/2024
 */

 import java.sql.SQLException;

 public class Movie {
 
    public static final String createMovie = "createGenre";
    public static final String listAllMovies = "listAllMovies";

    /**
      * Creates a new Movie and adds it to the DB
      *
      * @author: Shyam Ramesh
      */

    public static boolean createMovie(String [] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("createNewMovie - creates a new movie and adds it the DB");
            System.out.println("Command: createMovie <Genre Name>");

        } else { 
            
            // Execute API

           try {

            MovieDB.createMovie(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

     /**
      * Lists all the Movies in the DB
      *
      * @author: Shyam Ramesh
      */

    public static void listAllMovies(String [] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("listAllMovies - Returns a list of all the movies");
            System.out.println("Command: listAllMovies <>");

        } else { 
            
            // Execute API

            try {

                MovieDB.listAllMovies(user_input);

            } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}