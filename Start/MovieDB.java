/*
 * DB APIs and associated methods:
 * Connect(), Disconnect(), ListAllAccounts(), ListAllSubscribers(), InsertMovies(), InsertProfilePicture(), and InsertTheme().
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
     
     //functions:
     public static Connection Connect() throws SQLException{
         
         if(connection != null && !connection.isValid(10000))
         {
             connection = null;
         }
         
         int tries = 0;
         while(connection == null || connection.isClosed())
         {
             try {
                 tries++;
                 System.out.println("Trying to connect to a DB.");
 
                 Properties property_c = new Properties();
                 property_c.put("user", USER);
                 property_c.put("password", PASSWORD);
         
                 connection = DriverManager.getConnection(URL, property_c);
 
                 if(!connection.isValid(100))
                 {
                     connection = null;
                     throw new SQLException("Connection not Valid");
                 }
     
             } catch (SQLException e) {
                 System.out.println("Failed connecting to DB !");
 
                 if(tries >= 5)
                 {
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
         }catch (SQLException e) {
                 e.printStackTrace();
             }
     }
  
 
 //accounts:
 
 
 /*
  * List all accounts with all of their information 
  * 
  * 
  * 
  * 
  * 
  */
     public static void ListAllAccounts() throws SQLException{
         System.out.println("");
         
         Statement statement = null;
         ResultSet result = null;
         try {
             
             Connection connection = Connect();
             
             String sqlStatement = "SELECT account.name, COALESCENE(accounts.email, 'None')" 
             + "FROM account " 
             + "ORDER BY account.id ASC";
             
             statement = connection.createStatement();
             result = statement.executeQuery(sqlStatement);
             
             boolean recordExists = false;
             
             while(result != null && result.next()) {
                 recordExists = true;
                 System.out.println("Account Name: " + result.getString("name"));
                 System.out.println("Email Address: " + result.getString("email"));
                 System.out.println("");
             }
             
             if(!recordExists) {
                 System.out.println("No Account is found!");
                 System.out.println("");
             }
         }catch (SQLException exception) {
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
     
 /*
  * List all subscribers and their emails per account.
  * 
  * 
  * 
  * 
  * 
  */
     
     public static void ListAllSubscribers() throws SQLException{
         System.out.println("");
         
         Statement statement = null;
         ResultSet result = null;
         
         try {
             
             Connection connection = Connect();
             
             String sqlStatement = "SELECT account.name, subscriber.firstname, subscriber.lastname, subscriber.email"
                                 + "FROM account"
                                 + "JOIN subscriber ON (account.subscriberID = subscriber.id"
                                 + "ORDER BY account.id, subscriber.firstname, subscriber.lastname";
             statement = connection.createStatement();
             result = statement.executeQuery(sqlStatement);
             
             boolean recordExists = false;
             while(result != null && result.next()) {
                 recordExists = true;
                 System.out.println("Account Name: " + result.getString("name"));
                 System.out.println("Subscriber First Name: " + result.getString("firstname"));
                 System.out.println("Subscriber Last Name: " + result.getString("lastname"));
                 System.out.println("Subscriber Email: " + result.getString("email"));
                 System.out.println("");
             }
             
             if(!recordExists) {
                 System.out.println("No subscriber is found!");
                 System.out.println("");
             }
         }catch (SQLException exception) {
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
         
         }catch (SQLException exception) {
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
     
 /*
  * insert new profile picture to the database
  * 
  * 
  */ 
     public static void InsertProfilePicture(String pfp_location) throws SQLException {
         System.out.println("");
         
         Statement statement = null;
         ResultSet result = null;
         
 try {
             
             Connection connection = Connect();
             
             String sqlStatement = "SELECT *" +
                                   "FROM ProfilePicture" +
                                   "WHERE picture = " + pfp_location;
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
                         "VALUES(" + pfp_location + ")";
         
         statement = connection.createStatement();
         result = statement.executeQuery(sqlStatement);
         
         if(result != null) {
             System.out.println("Movie successfully added!");
         }
         
         }catch (SQLException exception) {
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
 /*
 * insert new theme to the database
 * 
 * 
 */ 
         public static void InsertTheme(String name) throws SQLException {
             System.out.println("");
             
             Statement statement = null;
             ResultSet result = null;
             
     try {
                 
                 Connection connection = Connect();
                 
                 String sqlStatement = "SELECT *" +
                                       "FROM Theme" +
                                       "WHERE name = '" + name + "'";
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
             sqlStatement = "INSERT INTO Theme(name)" +
                             "VALUES( '" + name + "')";
             
             statement = connection.createStatement();
             result = statement.executeQuery(sqlStatement);
             
             if(result != null) {
                 System.out.println("Movie successfully added!");
             }
             
             }catch (SQLException exception) {
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
     
 }