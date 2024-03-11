/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chris Chen
 * @version 3/8/2024
 */

import java.sql.SQLException;
import java.sql.Timestamp; 
import java.text.ParseException; 
import java.text.SimpleDateFormat;

public class Movie {
 
    public static final String createMovie = "createMovie";

    /**
      * Creates a new Movie and adds it to the DB
      *
      * @author: Shyam Ramesh
      */

    public static boolean createMovie(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
            
            System.out.println("--Movie--");

            // Print detailed explanation of the API
            System.out.println("createNewMovie - creates a new movie and adds it the DB");
            System.out.println("Command: createMovie <synopsis> <directorFirstName> <directorLastName> <runtime>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            java.util.Date parsedDate = dateFormat.parse(user_input[6]);

            Timestamp timestamp = new Timestamp(parsedDate.getTime());

            MovieDB.createMovie(user_input[2], user_input[3], user_input[4], 
                                user_input[5], timestamp);

           } catch (SQLException e) {

                e.printStackTrace();
           } catch (ParseException e) {

                e.printStackTrace();
           }
        }

        return true;
    }

    /**
      * List important movie information 
      *
      * @author: Shyam Ramesh
      */

    public static void listMovieInformation(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
 
            // Print detailed explanation of the API
            System.out.println("listMovieInformation - lists a movies title, runtime, and the directors first and last name");
            System.out.println("Command: listMovieInformation <'Z'>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

            MovieDB.listMovieInformation(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}