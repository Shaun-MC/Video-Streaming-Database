/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @version 3/8/2024
 */

import java.sql.SQLException;

public class Genre {
 
    public static final String createGenre = "createGenre";
    public static final String listAllMoviesPerGenre = "listAllMoviesPerGenre";

    /**
      * Creates a new Genre and adds it to the DB
      *
      * @author: Shaun Cushman
      */

    public static void createGenre(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
            
            System.out.println("--Genre--");

            // Print detailed explanation of the API
            System.out.println("createNewGenre - creates a new genre and adds it the DB");
            System.out.println("Command: createNewGenre <Genre Name>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

            MovieDB.createGenre(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

     /**
      * Lists all the Movies asscoiated w/ a Genre
      *
      * @author: Shaun Cushman
      */

    public static void listAllMoviesPerGenre(String [] user_input) {

        if (user_input == null || user_input[0] == "") {
 
            // Print detailed explanation of the API
            System.out.println("listAllMoviesPerGenre - Returns a list of all the movies associated w/ a genre");
            System.out.println("Command: listAllMoviesPerGenre <Genre Name>");

            System.out.println("");

        } else { 
            
            // Execute API

            try {

                MovieDB.listAllMoviesPerGenre(user_input);

            } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}