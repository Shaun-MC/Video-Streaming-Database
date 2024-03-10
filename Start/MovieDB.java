/** 
 * DB APIs and associated methods:
 * Connect(), Disconnect(), ListAllAccounts(), ListAllSubscribers(), InsertMovies(), InsertProfilePicture(), and InsertGenre().
 * 
 * Done by Chris Chen, may need to review for debug and further update.
 *
 */

import java.sql.*;
//import java.util.HashMap;
import java.util.Properties;
 
public class MovieDB {
     
    // JDBC URL, username, and password of PostgreSQL server (TO BE ACCUSTOMED)
    private static final String URL = "jdbc:postgresql://localhost:5432/movietoacct"; 
    private static final String USER = "postgres";
    private static final String PASSWORD = "2606";
    private static Connection connection = null;
     
    // Functions:
    public static Connection Connect() throws SQLException {
         
        if(connection != null && !connection.isValid(10000)) {
             
            connection = null;
         }
         
        int tries = 0;
         
        while(connection == null || connection.isClosed()) {
            
            try {
                 
                tries++;
                System.out.println("Trying to connect to a DB.");
 
                Properties property_c = new Properties();
                property_c.put("user", USER);
                property_c.put("password", PASSWORD);
         
                connection = DriverManager.getConnection(URL, property_c);
 
                if(!connection.isValid(100)) {
                     
                    connection = null;
                    throw new SQLException("Connection not Valid");
                }
     
             } catch (SQLException e) {
                 
                System.out.println("Failed connecting to DB !");
 
                if(tries >= 5) {
                     
                    connection = null;
                    throw e;
                }
 
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e1) {
                     
                    e1.printStackTrace();
                }
            }  
            
            System.out.println("");          
        }
         
        return connection;
    }
     
    public static void Disconnect(){
        
        try {
             
            if (connection != null && !connection.isClosed()) {
                
                connection.close();
            } 
         
        } catch (SQLException e) {
                 
            e.printStackTrace();
        }
     
    }

 // ---------------------------------------------------------------------------------------------------------
 // Accounts:
 // ---------------------------------------------------------------------------------------------------------

  /**
   * Insert an account into the DB.
   * 
   * @author: Chris Chen
   */
    public static void insertAccount(String username, String firstname, String lastname, int profilePicID, String email) throws SQLException {
        System.out.println("");
         
        Statement statement = null;
        ResultSet result = null;
         
        try {
             
            Connection connection = Connect();
             
            String sqlStatement = "INSERT INTO Account(firstname, lastname, username, profilepicID, email)" +
                                  "VALUES ('" + firstname + "', '" + lastname + "', '" + username + "', " + 
                                  profilePicID + ", '" + email + "')"; 	
         
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
         
            if(result != null) {
             
                System.out.println("Account successfully added!");
            }
         
         } catch (SQLException exception) {
             
            exception.getStackTrace();
         } finally {
             
            if(statement != null) {
                 
                statement.close();
            }
            
            if(result != null) {
                 
                result.close();
            }
   
        }
    }

  // ---------------------------------------------------------------------------------------------------------
  // Movie
  // ---------------------------------------------------------------------------------------------------------
     
  /**
   * Add new Movies to the database  
   * 
   * @author : Chris Chen
   */
    public static void createMovie(String type, String synopsis, String directorFirstName, String directorLastName, Timestamp runtime) throws SQLException {
        
        System.out.println("");
         
        Statement statement = null;
        ResultSet result = null;
         
        try {
             
            Connection connection = Connect();
             
            String sqlStatement = "SELECT *" +
                                   "FROM movie" +
                                   "WHERE type = " + type +
                                   "AND synopsis = " + synopsis;
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
             
            if(result != null && result.next()) {
            
                System.out.println("Input movie already exist!");
                System.out.println("");
                return;
            }
         
            if(statement != null) {
                
                statement.close();
            }
            
            if(result != null) {
                result.close();
            }

            sqlStatement = "INSERT INTO movies(type, synopsis, firstname, lastname, runtime)" +
                           "VALUES (" + type + ", " + synopsis + ", " + directorFirstName + ", " + directorLastName + ", " + runtime + ")"; 	
         
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
         
            if(result != null) {
             
                System.out.println("Movie successfully added!");
            }
         
        } catch (SQLException exception) {
             
            exception.getStackTrace();

         } finally {
             
            if(statement != null) {
                 
                statement.close();
            }
             
             if(result != null) {
                 
                result.close();
            }
           
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    // Reviews
    // ---------------------------------------------------------------------------------------------------------

    /**
      * Creates a new Review and adds it to the DB
      *
      *@ author: Shaun Cushman
      */
    public static boolean createReview(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "INSERT INTO Review (AccountID, MovieID, description, rating, watchedDate) " +
                           "VALUES ( (SELECT A.id FROM Account AS A WHERE A.userName = ?), " +
                                      "(SELECT M.id " + 
                                      "FROM Movie AS M " +  
                                      "WHERE M.title = ? AND M.directorID = " +
                                                            "(SELECT D.ID FROM Director AS D WHERE D.firstName = ? AND D.lastName = ?)" +
                                      ", ?, ?, ?)";
                            

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: Account userName
            // 2: Movie Title
            // 3: Director Firstname
            // 4: Director Lastname
            // 5: Description
            // 6: Rating
            // 7: WatchedDate
            for (int i = 1; i < 8; i++) {

                p_statement.setString(i, user_input[i]);
            }

            int success = p_statement.executeUpdate(query);
            
            if (success > 0) { // Ternary Operator Use Case

                successful_q = true;

                System.out.println("Successfully Inserted the New Review");                
            } else {

                System.out.println("Could Not Insert New Review");     
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }

    /**
      * 'Shares' a review with an another user
      *
      *@ author: Shaun Cushman
      */

    // Does not actually send anything to anybodys email, proof of concept
    public static boolean shareReview(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "SELECT (SELECT A.email FROM Account AS A WHERE A.userName = ?) AS From_email, (SELECT A.email FROM Account AS A WHERE A.userName = ?) as To_email, " +
                                    "R.ID AS reviewNum " +
                           "FROM Account AS A " +
                           "JOIN Review AS R ON (A.id = R.reviewID) " +
                           "WHERE R.MovieID = ( SELECT M.ID " +
                                                "FROM Movie AS M " +
                                                "WHERE M.title = ? AND M.directorID = (SELECT D.ID FROM Director AS D WHERE D.firstName = ? AND D.lastName = ?)";
                        
        

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: From_Account userName
            // 2: To_Account userName
            // 3: Movie Title
            // 4: Director firstName
            // 5: Director lastName
            for (int i = 1; i < 6; i++) {

                p_statement.setString(i, user_input[i]);
            }

            resultSet = p_statement.executeQuery(query);

            if (resultSet != null && resultSet.next()) { // Ternary Operator Use Case

                successful_q = true;

                System.out.println("Review Exists and Is 'Shared' With the Other User");  

                System.out.println("From Email: " + resultSet.getString("From_email"));
                System.out.println("To Email: " + resultSet.getString("To_email"));

                // Bad practice to expose surrogate key, only for the sake of proving the review exists and the 
                // From_email account could send that review
                System.err.println("Sent Review: " + resultSet.getString("reviewNum"));


            } else {

                System.out.println("Could Not Locate Review of " + user_input[3] + " For " + user_input[1]);     
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
            resultSet.close();
        }

        return successful_q;
    }

    /**
      * Updates a reviews description and rating
      *
      *@ author: Shaun Cushman
      */
    public static boolean updateReview(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query 
            String query = "UPDATE Review " +
                           "SET description = ?, rating = ? " +
                           "WHERE Review.ID = (SELECT R.ID " +
                                               "FROM Review AS R " +
                                               "JOIN Account AS A ON (A.ID = R.AccountID) " +
                                               "JOIN Movie AS M ON (M.ID = R.MovieID) " +
                                               "WHERE R.AccountID = (SELECT A.ID FROM Account AS A WHERE A.userName = ?) AND " +
                                                      "R.MovieID = (SELECT M.ID FROM Movie AS M " +
                                                                    "WHERE M.title = ? AND M.directorID = (SELECT D.ID " +
                                                                                                          "FROM Director AS D " + 
                                                                                                          "WHERE D.firstName = ? AND " +
                                                                                                                 "D.lastName = ?) )";
            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: New Descrption
            // 2: New Rating
            // 3: Account userName
            // 4: Movie Title
            // 5: Director lastName
            // 6: Director fistName
            for (int i = 1; i < 7; i++) {

                p_statement.setString(i, user_input[i]);
            }

            int success = p_statement.executeUpdate(query);

            if (success > 0) { // Ternary Operator Use Case

                successful_q = true;

                System.out.println("Successfully Updated Review");                
            } else {

                System.out.println("Could Not Update Review");     
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }

        
    /**
      * Deletes a reivew from a users account
      *
      *@ author: Shaun Cushman
      */
    
    public static boolean deleteReview(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query 
            String query = "DELETE FROM Review " +
                           "WHERE Review.ID = (SELECT R.ID " +
                                               "FROM Review AS R " +
                                               "JOIN Account AS A ON (A.ID = R.AccountID) " +
                                               "JOIN Movie AS M ON (M.ID = R.MovieID) " +
                                               "WHERE R.AccountID = (SELECT A.ID FROM Account AS A WHERE A.userName = ?) AND " +
                                                      "R.MovieID = (SELECT M.ID FROM Movie AS M " +
                                                                    "WHERE M.title = ? AND M.directorID = (SELECT D.ID " +
                                                                                                          "FROM Director AS D " + 
                                                                                                          "WHERE D.firstName = ? AND " +
                                                                                                                 "D.lastName = ?) )";
            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: Account userName
            // 2: Movie Title
            // 3: Director firstName
            // 4: Director lastName
            for (int i = 1; i < 7; i++) {

                p_statement.setString(i, user_input[i]);
            }

            int success = p_statement.executeUpdate(query);

            if (success > 0) { // Ternary Operator Use Case

                successful_q = true;

                System.out.println("Successfully Deleted Review");                
            } else {

                System.out.println("Could Not Delete Review");     
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }


    // ---------------------------------------------------------------------------------------------------------
    // Viewing Parties:
    // ---------------------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------------------
    // Genre
    // ---------------------------------------------------------------------------------------------------------

   /** 
    * Display a list of all the genres in the database
    *
    * @author Shaun Cushman
    */

    public static boolean createGenre(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "INSERT INTO Genre(name) VALUES (?)";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            p_statement.setString(1, user_input[0]); // The passed in Genre name 

            int success = p_statement.executeUpdate(query);

            if (success > 0) {

                successful_q = true;

                System.out.println("Insertion of New Genre Succesful");

            } else {

                System.out.println("Insertion of New Genre Not Succesful");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }

    /** 
    * Display a list of all the genres in the database
    *
    * @ author Shaun Cushman
    */

    public static void listAllMoviesPerGenre(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "SELECT M.title " +
                           "FROM Movie AS M " +
                           "JOIN MovieToGenre AS MTG ON (M.id = MTG.movieID) " +
                           "WHERE MTG.GenreID = (" +
                                            "SELECT G.ID " +
                                            "FROM Genre AS G " +
                                            "WHERE G.name = ? " +
                                            ") " +
                        "ORDER BY M.title";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            p_statement.setString(1, user_input[0]); // The passed in Genre name 
           
            resultSet = p_statement.executeQuery(query);

            boolean successful_q = false;

            // Display All Elements in the ResultSet 
            while (resultSet != null && resultSet.next()) {

                successful_q = true;

                System.out.println("Movie Title: " + resultSet.getString("title"));
            }

            // If there we no elements matching the query
            if (successful_q == false) {

                System.out.println("No Records Matching the Query");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
            resultSet.close();
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    // Actors
    // ---------------------------------------------------------------------------------------------------------

    /**
      * Creates a new Actor and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static boolean createActor(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "INSERT INTO Actor(firstName, lastName) VALUES (?, ?)";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: Actor firstName
            // 2: Actor lastName
            for (int i = 1; i < 3; i++) {

                p_statement.setString(i, user_input[i]);
            }

            int success = p_statement.executeUpdate();

            if (success > 0) {

                successful_q = true;

                System.out.println("Insertion of New Actor Succesful");

            } else {

                System.out.println("Insertion of New Actor Not Succesful");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }

    /**
      * Lists all the movie title associated w/ a specific actor
      *
      *@ author: Shaun Cushman
      */

    public static void listActorsMovies(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "SELECT M.title " +
                           "FROM Movie AS M " +
                           "JOIN MovieToActors AS MTA ON (M.id = MTA.movieID) " +
                           "WHERE MTA.ActorID = (" +
                                            "SELECT A.ID " +
                                            "FROM Actor AS A " +
                                            "WHERE A.fistName = ? AND A.lastName = ? " +
                                            ") " +
                          "ORDER BY M.title";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: Actor firstName
            // 2: Actor lastName
            for (int i = 1; i < 3; i++) {

                p_statement.setString(i, user_input[i]);
            }
            
            resultSet = p_statement.executeQuery(query);

            boolean successful_q = false;

            // Display All Elements in the ResultSet 
            while (resultSet != null && resultSet.next()) {

                successful_q = true;

                System.out.println("Movie Title: " + resultSet.getString("title"));
            }

            // If there we no elements matching the query
            if (successful_q == false) {

                System.out.println("No Records Matching the Query");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            // Release SQL Objects
            p_statement.close();
            resultSet.close();
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    // Profile Picture
    // ---------------------------------------------------------------------------------------------------------
 
    /**
     * Insert new profile picture to the database
     * 
     * @author: Chris Chen
     */ 

    public static void insertProfilePicture(String path) throws SQLException {
        
        System.out.println("");
         
        Statement statement = null;
        ResultSet result = null;
         
        try {
             
            Connection connection = Connect();
             
            String sqlStatement = "SELECT *" +
                                   "FROM ProfilePicture" +
                                   "WHERE picture = " + path;
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
             
            if(result != null && result.next()) {
                
                System.out.println("Input profile picture already exist!");
                System.out.println("");
                
                return;
            }
            
            if(statement != null) {
                
                statement.close();
            }
            if(result != null) {
                
                result.close();
            }
            
            sqlStatement = "INSERT INTO ProfilePictures(picture)" +
                            "VALUES(" + path + ")";
            
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
            
            if(result != null) {
                
                System.out.println("Profile Picture successfully added!");
            }
            
        } catch (SQLException exception) {
                
                exception.getStackTrace();

        }finally {
                
            if(statement != null) {
                    
                 statement.close();
            }
            if(result != null) {
                    
                 result.close();
            }
        }
    }


    // ---------------------------------------------------------------------------------------------------------
    // Director
    // ---------------------------------------------------------------------------------------------------------

    public static boolean createDirector(String[] user_input) throws SQLException {

        // Initializae SQL Objects
        PreparedStatement p_statement = null;

        boolean successful_q = false;

        try {

            // Open Connection
            Connection connection = Connect();

            // Create Query
            String query = "INSERT INTO Director(firstName, lastName) VALUES (?, ?)";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatement(query);

            // 1: Actor firstName
            // 2: Actor lastName
            for (int i = 1; i < 3; i++) {

                p_statement.setString(i, user_input[i]);
            }

            int success = p_statement.executeUpdate();

            if (success > 0) {

                successful_q = true;

                System.out.println("Insertion of New Director Succesful");

            } else {

                System.out.println("Insertion of New Director Not Succesful");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Release SQL Objects
            p_statement.close();
        }

        return successful_q;
    }

}


