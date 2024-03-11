/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @version 3/8/2024
 */

 import java.sql.SQLException;

 public class Actor {
  
    public static final String createActor = "createActor";
    public static final String listActorsMovies = "listActorsMovies";

    /**
      * Creates a new Actor and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static void createActor(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
            
            System.out.println("--Actor--");

            // Print detailed explanation of the API
            System.out.println("createActor - creates an Actor and adds them to the DB");
            System.out.println("Command: <ACT> createActor <Actor Fistname> <Actor Lastname>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

                MovieDB.createActor(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

    /**
      * Lists all the movie title associated w/ a specific actor
      *
      *@ author: Shaun Cushman
      */

    public static void listActorsMovies(String[] user_input) {

        if (user_input == null || user_input[0] == "") {

            // Print detailed explanation of the API
            System.out.println("listActorsMovies - lists all the movie title associated w/ a specific actor");
            System.out.println("Command: <ACT> listActorsMovies <Actor Fistname> <Actor Lastname>");

            System.out.println("");
        } else { 
            
            // Execute API

           try {

                MovieDB.listActorsMovies(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}