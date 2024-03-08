/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @version 3/8/2024
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLExecution;

class APIDriver {

    public static void main (String[] args) {

        // MSPlatformDB.getConnection();

        // Header / Welcome Message
        System.out.println("---Movie Streaming Platform---");
        System.out.println("Input 'L' to list all the available APIs. (Case Insensitive)");
        System.out.println("Input 'E' to exit the program. (Case Insensitive) ");
        System.out.println("Input An API Name and Assciated Input to Execute That API. (Case Sensitive)");

        String user_input = "";

        while (!user_input.equalsIgnoreCase('E')) {

            bool flag = false;

            System.out.print("MSPlatform: ");
            
            // Retrieve user input from the command line
            user_input = System.console.readLine();

            switch (user_input[0]) {

                case 'L':
                case 'l': 
                ListOfAPIs();
                break;

                case 'E':
                case 'e':
                break;

                // Subscriber APIs
                case 'S':
                callSubscriberAPIs(user_input);
                break;

                // Account APIs
                case 'A':
                callAccountAPIs(user_input);
                break;

                // ProfilePicture APIs
                case "PP":
                callProfilePictureAPIs(user_input);
                break;

                // Movie APIs
                case 'M':
                callMovieAPIs(user_input);
                break;

                // Review APIs
                case 'R':
                callReviewAPIs(user_input);
                break;

                // Viewing Party APIs
                case "VP":
                callViewingPartyAPIs(user_input);
                break;

                // Genre APIs
                case 'G': 
                callGenreAPIs(user_input);
                break;

                // Actor APIs
                case "ACT":
                callActorAPIs(user_input);
                break;

                default:
                System.out.println("MSPlatform: API Type Not Recognized. Try Again.");
            }
        }
    }

    System.out.println("Goodbye.");

    // MSPlatformDB.disconnect();
}

private static void callSubscriberAPIs(String user_input) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
        
        case 

        case 

        case 

        case 

        default: 

        // Call everything
    }
}

private static void callAccountAPIs(String user_input) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 

        default: 
    
        // Call everything
    }
}

private static void callProfilePictureAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void callMovieAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void callReviewAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void callViewingPartyAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void callGenreAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void callActorAPIs(null) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case 
            
        case 
    
        case 
    
        case 
    
        case 
    
        default: 
    
        // Call everything
    }
}

private static void ListOfAPIs() {

    // Calls all the currently available APIs

    callSubscriberAPIs(null);

    callAccountAPIs(null);

    callProfilePictureAPIs(null);

    callMovieAPIs(null);

    callReviewAPIs(null);

    callViewingPartyAPIs(null);

    callGenreAPIs(null);

    callActorAPIs(null);
}