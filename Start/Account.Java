/**
 * CSS 475 Team Project
 * @ team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @version 3/8/2024
 */

import java.sql.SQLException;

public class Account {
  
    public static final String createAccount = "createAccount";
    public static final String listAccountInfo = "listAccountInfo";

    /**
      * Creates a new Account and adds it to the DB
      *
      *@ author: Shaun Cushman
      */

    public static void createAccount(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
            
            System.out.println("--Account--");

            // Print detailed explanation of the API
            System.out.println("createAccount - creates an Account and adds them to the DB");
            System.out.println("Command: <A> createAccount <ProfilePicID> <firstName> <lastName> <userName> <email>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

                MovieDB.createAccount(Integer.parseInt(user_input[2]), user_input[3], 
                                      user_input[4], user_input[5], user_input[6]);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

    public static void listAccountInfo(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
 
            // Print detailed explanation of the API
            System.out.println("listAccountInfo - lists all the account usernames and emails");
            System.out.println("Command: <A> listAccountInfo <'Z'>");

            System.out.println("");

        } else { 
            
            // Execute API

           try {

                MovieDB.listAccountInfo(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        } 
    }
}