/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chris Chen
 * @version 3/8/2024
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class APIDriver {

    public static void main (String[] args) {

        // Header / Welcome Message
        System.out.println("---Movie Streaming Platform---");
        System.out.println("Input 'L' to list all the available APIs and their required input. (Case Insensitive)");
        System.out.println("Input 'E' to exit the program. (Case Insensitive) ");
        System.out.println("Input An API Name and Assciated Input to Execute That API. (Case Sensitive)");

        String user_input = "";
        
        while (!user_input.equalsIgnoreCase("E")) {

            boolean flag = false;

            System.out.print("MSPlatform: ");
            
            // Retrieve user input from the command line
            user_input = System.console().readLine();

            String[] commands = user_input.split(" ", 2);

            switch (commands[1]) {

                case "L":
                case "l": 
                ListOfAPIs();
                break;

                case "E":
                case "e":
                break;

                // Account APIs
                case "A":
                case "a":
                APIDriver.callAccountAPIs(user_input);
                break;

                // ProfilePicture APIs
                case "PP":
                case "pp":
                APIDriver.callProfilePictureAPIs(user_input);
                break;

                // Movie APIs
                case "M":
                case "m":
                APIDriver.callMovieAPIs(user_input);
                break;

                // Review APIs
                case "R":
                case "r":
                APIDriver.callReviewAPIs(user_input);
                break;

                // Viewing Party APIs
                case "VP":
                case "vp":
                APIDriver.callViewingPartyAPIs(user_input);
                break;

                // Genre APIs
                case "G": 
                case "g":
                APIDriver.callGenreAPIs(user_input);
                break;

                // Actor APIs
                case "ACT":
                case "act":
                APIDriver.callActorAPIs(user_input);
                break;

                default:
                System.out.println("MSPlatform: API Type Not Recognized. Try Again.");
            }
        }

        System.out.println("Goodbye");
    }

    private static void callAccountAPIs(String user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            /*case: 
                
            case:
        
            case:
        
            case: 
        
            case: */

            // Call everything
            default: 
        
        }
    }

    private static void callProfilePictureAPIs(String[] user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            /*case: 
                
            case: 
        
            case: 
        
            case: 
        
            case: */
            
            // Call everything
            default: 
        
        }
    }

    private static void callMovieAPIs(null) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            /*case: 
                
            case: 
        
            case: 
        
            case: 
        
            case: */
            
            // Call everything
            default: 
        
        }
    }

    private static void callReviewAPIs(String[] user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            /*case: 
                
            case: 
        
            case:
        
            case: 
        
            case: */
            
            // Call everything
            default: 
        
        }
    }

private static void callViewingPartyAPIs(String[] user_input) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        /*case: 
            
        case: 
    
        case: 
    
        case: 
    
        case: */
    
        // Call everything
        default: 
    }
}

private static void callGenreAPIs(String[] user_input) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case Genre.createGenre:
        Genre.createGenre(null);
        break;

        case Genre.listAllMoviesPerGenre:
        Genre.listAllMoviesPerGenre(null);
        break;

        // Call everything
        default: 
        Genre.createGenre(null);
        Genre.listAllMoviesPerGenre(null);
    }
}

private static void callActorAPIs(String[] user_input) {

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case Actor.createActor:
        Actor.createActor(null);
        break;
    
        case Actor.listActorsMovies:
        Actor.listAllActorsMovies(null):
        break;

        // Call everything
        default: 
        Actor.createActor(null);
        Actor.listAllActorsMovies(null);
    }
}

private static void ListOfAPIs() {

    // Calls all the currently available APIs
    APIDriver.callAccountAPIs("X");

    APIDriver.callProfilePictureAPIs("X");

    APIDriver.callMovieAPIs("X");

    APIDriver.callReviewAPIs("X");

    APIDriver.callViewingPartyAPIs("X");

    APIDriver.callGenreAPIs("X"); // UNTESTED

    APIDriver.callActorAPIs("X"); // UNTESTED
}