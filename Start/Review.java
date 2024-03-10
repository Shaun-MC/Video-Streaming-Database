/**
 * CSS 475 Team Project
 * @ team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @ version 3/8/2024
 */

 import java.sql.SQLException;

 public class Review {
  
    public static final String createReivew = "createReview";
    public static final String shareReview = "shareReview";
    public static final String updateReview = "updateReview";
    public static final String deleteReview = "deleteReview";

    /**
      * Creates a new Review and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static void createReivew(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("createReivew - creates an Reivew and adds them to the DB");
            System.out.println("Command: createReview <account_userName> <movie_name> <director_firstName> <description> <rating> <date_watched");

        } else { 
            
            // Execute API

           try {

                MovieDB.createReview(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

    /**
      * 'Shares' a review with a another user
      *
      *@ author: Shaun Cushman
      */

    public static void shareReview(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("shareReview - 'shares' a review with another user through email");
            System.out.println("Command: shareReview - <from_accountUserName> <to_accountUserName> <movie_name> <director_firstName>");

        } else { 
            
            // Execute API

           try {

                MovieDB.shareReview(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

    /**
      * Updates an accounts review w/ a new description and rating
      *
      *@ author: Shaun Cushman
      */

    public static void updateReview(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("updateReview - updates an accounts review of a movie (description and rating only)");
            System.out.println("Command: updateReview <account_userName> <movie_title> <director_firstName> <director_lastName> <newDescription> <newRating>");

        } else { 
            
            // Execute API

           try {

                MovieDB.updateReview(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

    /**
      * Deletes a accounts reivew
      *
      *@ author: Shaun Cushman
      */
      
      public static void deleteReview(String[] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("deleteReview - deletes a review from a users account");
            System.out.println("Command: deleteReview <account_userName> <movie_title> <director_firstName>");

        } else { 
            
            // Execute API

           try {

                MovieDB.deleteReview(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}
