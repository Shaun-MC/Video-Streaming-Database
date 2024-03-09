public class Genre {
 
    public static final String createDirector = "createGenre";
    public static final String listAllDirectors = "listAllDirectors";

    /**
      * Creates a new Director and adds it to the DB
      *
      * @author: Shyam Ramesh 
      */

    public static boolean createDirector(String [] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("createDirector - creates a new Director and adds it the DB");
            System.out.println("Command: createDirector <Director Name>");

        } else { 
            
            // Execute API

           try {

            MovieDB.createDirector(user_input);

           } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }

     /**
      * Lists all the Directors
      *
      * @author: Shyam Ramesh
      */

    public static void listAllDirectors(String [] user_input) {

        if (user_input == null) {
 
            // Print detailed explanation of the API
            System.out.println("listAllDirectors - Returns a list of all the directors");
            System.out.println("Command: listAllDirectors <Director Name>");

        } else { 
            
            // Execute API

            try {

                MovieDB.listAllDirectors(user_input);

            } catch (SQLException e) {

                e.printStackTrace();
           }
        }
    }
}