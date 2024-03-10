/**
 * CSS 475 Team Project
 * @team Team Spongebob
 * @authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chris Chen
 * @version 3/8/2024
 */

import java.sql.*;

import javax.security.auth.login.AccountException;
import javax.swing.text.View;

class APIDriver {

    public static void main (String[] args) {

        // Header / Welcome Message
        System.out.println("---Movie Streaming Platform---");
        System.out.println("Input 'L' to list all the available APIs and their required input. (Case Insensitive)");
        System.out.println("Input 'E' to exit the program. (Case Insensitive) ");
        System.out.println("Input An API Name and Assciated Input to Execute That API. (Case Sensitive)");

        String user_input = "";
        
        while (!user_input.equalsIgnoreCase("E")) {

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
                APIDriver.callAccountAPIs(commands);
                break;

                // ProfilePicture APIs
                case "PP":
                case "pp":
                APIDriver.callProfilePictureAPIs(commands);
                break;

                // Movie APIs
                case "M":
                case "m":
                APIDriver.callMovieAPIs(commands);
                break;

                // Review APIs
                case "R":
                case "r":
                APIDriver.callReviewAPIs(commands);
                break;

                // Viewing Party APIs
                case "VP":
                case "vp":
                APIDriver.callViewingPartyAPIs(commands);
                break;

                // Genre APIs
                case "G": 
                case "g":
                APIDriver.callGenreAPIs(commands);
                break;

                // Actor APIs
                case "ACT":
                case "act":
                APIDriver.callActorAPIs(commands);
                break;

                default:
                System.out.println("MSPlatform: API Type Not Recognized. Try Again.");
            }
        }

        System.out.println("Goodbye");
    }

    private static void callAccountAPIs(String[] user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            case Account.createAccount:
            Account.createAccount(user_input);
            break; 

            // Call everything
            default: 

            Account.createAccount(user_input);
            Account.inviteAccount(user_input);
        }
    }

    private static void callMovieAPIs(String[] user_input) {

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

    private static void callReviewAPIs(String[] user_input) { // LIST COMPLETE

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            case Review.createReivew:
            Review.createReivew(user_input);
            break;

            case Review.shareReview:
            Review.shareReview(user_input);

            case Review.updateReview:
            Review.updateReview(user_input);
            break;

            case Review.deleteReview:
            Review.deleteReview(user_input);
            break;
            
            // Call everything
            default: 

            Review.createReivew(null);
            Review.shareReview(null);
            Review.updateReview(null);
            Review.deleteReview(null);
        }
    }

private static void callViewingPartyAPIs(String[] user_input) { // LIST COMPLETE

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case ViewingParty.createViewingParty:
        ViewingParty.createViewingParty(user_input);
        break; 
            
        case ViewingParty.joinViewingParty:
        ViewingParty.joinViewingParty(user_input);
        break;
    
        case ViewingParty.updateViewingPartyMovie:
        ViewingParty.updateViewingPartyMovie(user_input);
        break;
    
        // Call everything
        default: 

        ViewingParty.createViewingParty(null);
        ViewingParty.joinViewingParty(null);
        ViewingParty.updateViewingPartyMovie(null);
    }
}

private static void callGenreAPIs(String[] user_input) { // LIST DONE

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

private static void callActorAPIs(String[] user_input) { // LIST COMPLETE

    // user_input == the specific API desired to be called or nothing
        // In the nothing case - call all the API for their descriptions
    switch (user_input[1]) {

        // Each API has their own case
        case Actor.createActor:
        Actor.createActor(user_input);
        break;
    
        case Actor.listActorsMovies:
        Actor.listAllActorsMovies(user_input);
        break;

        // Call everything
        default: 
        
        Actor.createActor(null);
        Actor.listAllActorsMovies(null);
    }
}

    private static void callProfilePictureAPIs(String[] user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            case ProfilePicture.insertProfilePicture: 
            ProfilePicture.insertProfilePicture(user_input);
            break;
            
            // Call everything
            default: 
            ProfilePicture.insertProfilePicture(null);
        }
    }

    private static void callDirectorAPIs(String[] user_input) {

        // user_input == the specific API desired to be called or nothing
            // In the nothing case - call all the API for their descriptions
        switch (user_input[1]) {

            // Each API has their own case
            case Director.createDirector: 
            Director.createDirector(user_input);
            break;
            
            // Call everything
            default: 
            Director.createDirector(null);
        }
    }

    

    private static void ListOfAPIs() { // DONE

        String[] temp = {"X", "X"}; // Engineer Fix
       
        // Calls all the currently available APIs
        APIDriver.callAccountAPIs(temp);

        APIDriver.callMovieAPIs(temp); // UNTESTED - Complete List

        APIDriver.callReviewAPIs(temp); // UNTESTED - Complete List

        APIDriver.callViewingPartyAPIs(temp); // Complete List

        APIDriver.callGenreAPIs(temp); // UNTESTED - Complete List

        APIDriver.callActorAPIs(temp); // UNTESTED - Complete List

        APIDriver.callProfilePictureAPIs(temp); // UNTESTED - Complete List

        APIDriver.callDirectorAPIs(temp); // UNTESTED - Complete List
    }
}