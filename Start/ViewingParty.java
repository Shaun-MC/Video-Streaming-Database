/**
 * CSS 475 Team Project
 * @ team Team Spongebob
 * @ authors Shaun Cushman, Shyam Ramesh, Mike Chen, Chirs Chen
 * @ version 3/10/2024
 */

 //import java.sql.SQLException;

 public class ViewingParty{

    public static final String createViewingParty = "createViewingParty";
    public static final String joinViewingParty = "joinViewingParty";
    public static final String updateViewingPartyMovie = "updateViewingPartyMovie";

    public static void createViewingParty(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
            
            System.out.println("--Viewing Party--");
            
            // Print detailed explanation of the API
            System.out.println("createViewingParty - creates a viewing party and adds it the DB");
            System.out.println("Command: <VP> createViewingParty <title> <movie title> <director_firstName> <is_active>");

            System.out.println("");
        } else { 
            
            // Execute API

           /*try {

            MovieDB.createViewingParty(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }*/
        }
	}

    public static void joinViewingParty(String[] user_input) {

        if (user_input == null || user_input[0] == "") {
 
            // Print detailed explanation of the API
            System.out.println("joinViewingParty - joins a viewing party in the DB");
            System.out.println("Command: joinViewingParty <username>");

            System.out.println("");
        } else { 
            
            // Execute API

           /*try {

            MovieDB.joinViewingParty(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }*/
        }
    }
		
	public static void updateViewingPartyMovie(String [] user_input){
		
        if (user_input == null || user_input[0] == "") {
 
            // Print detailed explanation of the API
            System.out.println("updateViewingPartyMovie - update a movie watched in a viewing party");
            System.out.println("Command: joinViewingParty <movie name>, <director firstName>, <account user name>");

            System.out.println("");
        } else { 
		
		    /*try{
			
            MovieDB.updateViewingPartyMovie(user_input);

            } catch (SQLException e) {

                e.printStackTrace();
            }*/
		}
	}
}
