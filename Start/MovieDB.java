/** 
 * DB APIs and associated methods:
 * Connect(), Disconnect(), ListAllAccounts(), ListAllSubscribers(), InsertMovies(), InsertProfilePicture(), and InsertGenre().
 * 
 * Done by Chris Chen, may need to review for debug and further update.
 *
 */

package final_project;

import java.sql.*;
import java.util.HashMap;
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
  
 
 // Accounts:
     

  /*
   * Create an account into the student DB.
   * 
   * 
   */
    public static void InsertAccount(String username, String firstname, String lastname, int profilePicID, String email) throws SQLException {
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
 
 /*
  * List all accounts with all of their information 
  * 
  */
    public static void ListAllAccounts() throws SQLException{
        System.out.println("");
         
        Statement statement = null;
        ResultSet result = null;
        
        try {
             
            Connection connection = Connect();
             
            String sqlStatement = "SELECT account.name COALESCENE(accounts.email, 'None') " 
                                   + "FROM account " 
                                   + "ORDER BY account.id ASC";
             
            statement = connection.createStatement();
            result = statement.executeQuery(sqlStatement);
             
            boolean recordExists = false;
             
            while(result != null && result.next()) {
                 
                recordExists = true;
                System.out.println("Account First Name: " + result.getString("name"));
                System.out.println("Email Address: " + result.getString("email"));
                System.out.println("");
            }
             
            if(!recordExists) {
                
                System.out.println("No Account is found!");
                System.out.println("");
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
     
 /*
  * List all subscribers and their emails per account.
  *
  *
  */
     
  /*
   * Add new Movies to the database  
   */
    public static void InsertMovies(String type, String synopsis, String directorFirstName, String directorLastName, Timestamp runtime) throws SQLException {
        
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
     
 /*
  * Insert new profile picture to the database
  * 
  * 
  */ 
    public static void InsertProfilePicture(String path) throws SQLException {
        
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

    // Reviews
         
    public static void shareReview(final String email) {
             
    }
         
    // Viewing Parties:

    // Genre

   /** 
    * Display a list of all the genres in the database
    *
    * @author Shaun Cushman
    */

    public static boolean createGenre(String user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = getConnection();

            // Create Query
            String query = "INSERT INTO Genre(name) 
                            VALUES (?)";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatment(query);
            p_statement.setString(1, userInput[0]); // Whatever the passed in Genre name is 
            resultSet = statement.executeQuery(query);

            int success = p_statement.executeUpdate();

            if (success > 0) {

                System.out.println("Insertion of New Genre Succesful");

                return true;
            } else {

                System.out.println("Insertion of New Genre Not Succesful");

                return false;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Release SQL Objects
            p_statement.close();
            resultSet.close();
        }
    }

    /** 
    * Display a list of all the genres in the database
    *
    * @ author Shaun Cushman
    */

    public static void listAllMoviesPerGenre(String user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = getConnection();

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
            p_statement = connection.prepareStatment(query);
            p_statement.setString(1, userInput[0]) // Whatever the requested Genre name is 
            resultSet = statement.executeQuery(query);

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

    // Actors

    /**
      * Creates a new Actor and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static boolean createActor(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = getConnection();

            // Create Query
            String query = "INSERT INTO Actor(firstName, lastName) 
                            VALUES (?, ?)";

            // Assign Nessecary SQL Objects
            p_statement = connection.prepareStatment(query);
            p_statement.setString(1, userInput[1]); // Whatever the passed in firstName
            p_statement.setString(2, userInput[2]); // Whatever the passed in firstName
            
            resultSet = statement.executeQuery(query);

            int success = p_statement.executeUpdate();

            if (success > 0) {

                System.out.println("Insertion of New Actor Succesful");

                return true;
            } else {

                System.out.println("Insertion of New Actor Not Succesful");

                return false;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            // Release SQL Objects
            p_statement.close();
            resultSet.close();
        }
    }

    /**
      * Lists all the movie title associated w/ a specific actor
      *
      *@ author: Shaun Cushman
      */

    public static void listAllActorsMovies(String[] user_input) throws SQLException { // UNTESTED

        // Initializae SQL Objects
        PreparedStatement p_statement = null;
        ResultSet resultSet = null;

        try {

            // Open Connection
            Connection connection = getConnection();

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
            p_statement = connection.prepareStatment(query);
            p_statement.setString(1, userInput[1]) // Whatever the requested Actors firstName is 
            p_statement.setString(2, userInput[2]) // Whatever the requested Actors firstName is 
            
            resultSet = statement.executeQuery(query);

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





