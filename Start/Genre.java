/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @version 3/8/2024
 */

 import java.sql.SQLException;

 public class Genre {
 
     public static final String cmdListAllGenres = "listAllGenres"
     public static final String cmdListMostEnjoyedGenre = "listMostEnjoyedGenre"
     public static final String cmdListLeastEnjoyedGenre = "listLeastEnjoyedGenre"
 
     /**
      * Lists all the avialable Genres for movies
      *
      * @author: Shaun Cushman
      */
 
     public static void listAllGenres(String[] user_input) {
 
         if (user_input == null) {
 
             // Print detailed explanation of the API
             System.out.println("listAllGenres - Returns a list of all the genres that have movies assigned with them");
             System.out.println("Command: listAllGenres")
         } else {
 
             // Execute API
         }
     }
 
     /**
      * Lists the genre with the highest average rating score associated w/ their movies
      *
      * @author: Shaun Cushman
      */
     public static void listMostPopularGenre(String[] user_input) {
 
         if (user_input == null) {
 
             // Display explanation of the APIs purpose
             System.out.println("listMostEnjoyedGenre - Lists the name of the genre w/ the highest average rating score associated w/ their movies, and their avg score");
             System.out.println("Command: listMostEnjoyedGenre")
         } else {
 
             // Execute API
         }
     }
 
     /**
      * Lists the genre with the lowest average rating score associated w/ their movies
      *
      * @author: Shaun Cushman
      */
     public static void listLeastPopularGenre(String[] user_input) {
 
        if (user_input == null) {
 
            // Display detailed explanation of APIs purpose
            System.out.println("listLeastEnjoyedGenre - Lists the name of the genre w/ the lowest average rating score associated w/ their movies, and their avg score");
            System.out.println("Command: listLeastEnjoyedGenre")
        } else {
 
            // Execute API
        }
     }
 }