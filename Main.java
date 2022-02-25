import java.util.*;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06 (w/ Amir)
 * Objective: Software that tracks the user's top streamed anime category
 */
public class Main {
    //Episode time in minutes
    private static final int AVG_EP_TIME = 23;

    public static void main(String[] args) {
        boolean notQuit = true;
        String[] cmds = {"Add", "Remove", "Help", "Output Commands", "Exit Program"};

        //Structures for holding our data
        ArrayList<String> animeList = new ArrayList<>();
        ArrayList<String> studioList = new ArrayList<>();
        HashMap<String, ArrayList<String>> animeGenre = new HashMap<>();
        HashMap<String, Integer> animeEpisodeCount = new HashMap<>();
        HashMap<String, String> animeStudio = new HashMap<>();
        HashMap<String, Double> animeRating = new HashMap<>();

        //Printing out the title
        System.out.println("-----------------------------");
        System.out.println("Brandon's And Abhay's Anime List");
        System.out.println("-----------------------------");

        //Make a scanner to get user input
        Scanner scan = new Scanner(System.in);
        int input;
        //Loop for main functionality of the program
        do {
            printInterface(cmds);
            try {
                input = scan.nextInt();
                scan.nextLine();
            }catch (InputMismatchException e){
                input = 0;
            }
            switch (input) {
                case 1 -> getUserInput(scan, animeList, studioList, animeGenre, animeEpisodeCount, animeStudio, animeRating);
                case 2 -> remove(scan, animeList, animeGenre, animeEpisodeCount, animeStudio, animeRating);
                case 3 -> help();
                case 4 -> outputCases(scan, animeList, studioList, animeGenre, animeEpisodeCount, animeStudio, animeRating);
                case 5 -> notQuit = setQuit(scan);
                default -> printError();
            }
        }
        while (notQuit);

        //Only get here upon user choosing to exit
        System.out.println("-------------------------------------");
        System.out.println("Thank you for using our anime list!");
        System.out.println("-------------------------------------");
    }

    public static void printError() {
        System.out.println("-------------------------------------");
        System.out.println("Error reading command, please enter command by integer again!");
        System.out.println("-------------------------------------");
    }

    public static boolean setQuit(Scanner scan) {
        System.out.println("-------------------------------------");
        System.out.println("Are you sure you wish to quit? (Yes:1, No:0)");
        System.out.println("-------------------------------------");
        try{
            return !(scan.nextInt() == 1);
        }catch (InputMismatchException e){
            System.out.println("-------------------------------------");
            System.out.println("Error On Exit!");
            System.out.println("-------------------------------------");
            scan.nextLine();
            return true;
        }
    }

    public static void remove(Scanner scan,
                              ArrayList<String> animeList,
                              HashMap<String, ArrayList<String>> animeGenre,
                              HashMap<String, Integer> animeEpisodeCount,
                              HashMap<String, String> animeStudio,
                              HashMap<String, Double> animeScore) {
        if(animeList.isEmpty()){
            System.out.println("-------------------------------------");
            System.out.println("The anime list is empty!!");
            System.out.println("-------------------------------------");
        }else
        {
            System.out.println("Which of the following would you like to remove?");
            System.out.println("------------------------------------------------");
            for (String s : animeList)
            {
                System.out.println(s);
                System.out.println("------------------------------------------------");
            }
            String animeToRemove = scan.nextLine().toUpperCase();
            if (animeList.contains(animeToRemove)) {
                animeList.remove(animeToRemove);
                animeGenre.remove(animeToRemove);
                animeEpisodeCount.remove(animeToRemove);
                animeStudio.remove(animeToRemove);
                animeScore.remove(animeToRemove);
                System.out.println("-------------------------------------");
                System.out.println("Finished removing " + animeToRemove);
                System.out.println("-------------------------------------");
            } else {
                System.out.println("-------------------------------------");
                System.out.println("That anime is not in the list!");
                System.out.println("-------------------------------------");
            }
        }
    }

    /**
     * Displays help for each command usage
     */
    public static void help() {
        System.out.print(
                """
                        -----------------------------------------------------------
                        add -> add an anime to the list (Name, Genre, # of Episodes)
                        remove -> remove an anime via name from the list
                        output commands -> displays menu with all possible output commands
                                                
                        exit -> exit the program
                        help -> displays help for commands
                        -----------------------------------------------------------
                        """);
    }

    /**
     * The main input function
     *
     * @param scan              scanner for getting the input
     * @param animeList         the main list of stored anime
     * @param studioList        the main list of stored studios
     * @param animeGenre        the linked anime/genre hashmap
     * @param animeEpisodeCount the linked anime/episodeCount hashmap
     * @param animeStudio       tying anime and studios together
     * @param animeScore        giving anime a 1-10 score
     */
    //TODO: Maybe come back and compartmentalize this method
    public static void getUserInput(Scanner scan,
                                    ArrayList<String> animeList,
                                    ArrayList<String> studioList,
                                    HashMap<String, ArrayList<String>> animeGenre,
                                    HashMap<String, Integer> animeEpisodeCount,
                                    HashMap<String, String> animeStudio,
                                    HashMap<String, Double> animeScore) {
        boolean notAdded = true;
        ArrayList<String> genres = new ArrayList<>();
        System.out.println("What anime would you like to add?");
        String newAnime = "";
        while (notAdded) {
            newAnime = scan.nextLine().toUpperCase();
            if (animeList.contains(newAnime)) {
                System.out.println("That anime is already tracked! Please try again");
            } else {
                animeList.add(newAnime);
                notAdded = false;
            }
        }

        System.out.println("What studio worked on the anime?");
        String newStudio = scan.nextLine().toUpperCase();
        studioList.add(newStudio);
        animeStudio.put(newAnime, newStudio);

        System.out.println("What genres does this anime fall under (ie: romance, action [done when finished])?");
        String newGenre = scan.nextLine().toUpperCase();
        do {
            if (!newGenre.isEmpty() || newGenre.trim().length() > 0){
                genres.add(newGenre);
            }
            System.out.println("Another genre? [done when finished]");
            newGenre = scan.nextLine().toUpperCase();
        } while (!newGenre.equals("DONE"));
        animeGenre.put(newAnime, genres);

        //Adding episodes watched
        int episodesWatched = 0;
        do{
            if (episodesWatched < 0){
                System.out.println("Cant add a negative count!");
            }
            try{
                System.out.println("How many episode have you watched?");
                episodesWatched = scan.nextInt();
                animeEpisodeCount.put(newAnime, episodesWatched);
            }catch (InputMismatchException e){
                System.out.println("Error adding episode count! Try again! (negative or non-integer error!)");
                episodesWatched = 0;
                scan.nextLine();
            }
        }while (episodesWatched <= 0);

        System.out.println("What rating would you give this anime (0-10)");
        double rating = -1;
        while (rating < 0 || rating > 10)
        {
            try
            {
                rating = scan.nextDouble();
                if (rating < 0 || rating > 10)
                    System.out.println("Rating must be between 0 and 10! Please input again");
                else
                    animeScore.put(newAnime, rating);
            } catch (InputMismatchException e)
            {
                System.out.println("Error! Please input a double value!");
                scan.nextLine();
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Completed adding " + newAnime + " to the list!");
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Prints out the interface, ie, all the different options
     *
     * @param cmds Array containing all the commands
     */
    public static void printInterface(String[] cmds) {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < cmds.length; i++)
            System.out.println(i + 1 + ") " + cmds[i]);
    }

    /**
     * Function for all the handling of output commands
     */
    public static void outputCases(Scanner scan,
                                   ArrayList<String> animeList,
                                   ArrayList<String> studioList,
                                   HashMap<String, ArrayList<String>> animeGenre,
                                   HashMap<String, Integer> animeEpisodeCount,
                                   HashMap<String, String> animeStudio,
                                   HashMap<String, Double> animeScore) {
        String[] outputCmds = {"Print All Anime Tracked", "Total Watch Time", "Top Streamed Anime",
                "Top Streamed Genre", "Anime By Genre", "Help", "Exit to Main Menu"};
        boolean notQuit = true;
        //Looping for the output commands interface
        do {
            printInterface(outputCmds);
            int input = scan.nextInt();
            scan.nextLine();
            switch (input) {
                case 1 -> allAnimeTracked(animeList);
                case 2 -> totalWatchTime(animeList, animeEpisodeCount);
                case 3 -> topStreamedAnime(animeList, animeEpisodeCount);
                case 4 -> topStreamedGenre(animeList, animeGenre, animeEpisodeCount);
                case 5 -> getGenreByAnime(scan, animeGenre);
                case 6 -> helpOutputCommands();
                case 7 -> notQuit = exitToMain(scan);
                default -> printError();
            }
        }
        while (notQuit);
    }

    /**
     * Determines the top streamed anime based on total watch time of each anime
     *
     * @param animeList         Arraylist containing all anime being tracked
     * @param animeEpisodeCount //TODO Javadoc
     */
    public static void topStreamedAnime(ArrayList<String> animeList, HashMap<String, Integer> animeEpisodeCount) {
        System.out.println("-----------------------------------------------------------");
        if (animeList.size() > 0) {
            String topStreamedAnime = animeList.get(0);
            Integer episodeCount = -1;
            for (String anime : animeList) {
                if (animeEpisodeCount.get(anime) > episodeCount) {
                    topStreamedAnime = anime;
                    episodeCount = animeEpisodeCount.get(anime);
                }
            }
            System.out.printf("Your current top streamed anime is: %s\nEstimated watch time: %s minutes\n", topStreamedAnime, episodeCount * AVG_EP_TIME);
        } else {
            System.out.println("No anime is currently being tracked!");
        }
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * @param animeList         ArrayList
     * @param animeGenre        HashMap
     * @param animeEpisodeCount HashMap
     */
    //TODO Test function, by removing and adding anime
    public static void topStreamedGenre(ArrayList<String> animeList, HashMap<String, ArrayList<String>> animeGenre,
                                        HashMap<String, Integer> animeEpisodeCount) {
        // creating intermediate data structure
        HashMap<String, Integer> genreToEpisodeMapping = new HashMap<>();
        System.out.println("-----------------------------------------------------------");
        if (animeList.size() > 0) {
            for (String anime : animeList) {
                ArrayList<String> genreList = animeGenre.get(anime);
                for (String genre : genreList) {
                    // if  key already exists, its being updated
                    if (genreToEpisodeMapping.containsKey(genre)) {
                        genreToEpisodeMapping.put(genre, genreToEpisodeMapping.get(genre) + animeEpisodeCount.get(anime));
                    }
                    // creating a new key and item pair within hashmap
                    else {
                        genreToEpisodeMapping.put(genre, animeEpisodeCount.get(anime));
                    }
                }
            }
            Set<String> genreKeySet = genreToEpisodeMapping.keySet();
            ArrayList<String> genresList = new ArrayList<>(genreKeySet);
            String topStreamedGenre = genresList.get(0);
            int numberOfEpisodes = 0;
            //looping through all genres
            for (String genre : genresList) {
                if (genreToEpisodeMapping.get(genre) > numberOfEpisodes) {
                    topStreamedGenre = genre;
                    numberOfEpisodes = genreToEpisodeMapping.get(genre);
                }
            }
            //printing out to console
            System.out.printf("Your top streamed anime genre is: %s\n", topStreamedGenre);
            System.out.printf("Estimated Watch Time: %s minutes\n", numberOfEpisodes * AVG_EP_TIME);
        } else {
            System.out.println("No anime is currently being tracked!");
        }
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * @param animeList         ArrayList
     * @param animeEpisodeCount HashMap
     */
    public static void totalWatchTime(ArrayList<String> animeList, HashMap<String, Integer> animeEpisodeCount) {
        int watchTime = 0;
        for (String anime : animeList) {
            watchTime += animeEpisodeCount.get(anime);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("Total Watch Time (estimated): %s minutes\n", watchTime * AVG_EP_TIME);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Get the genre of a supplied anime
     *
     * @param scan       scanner for inputs
     * @param animeGenre the map of anime -> a list of genre
     */
    public static void getGenreByAnime(Scanner scan, HashMap<String, ArrayList<String>> animeGenre) {
        System.out.println("What anime's genres would you like to search?");
        String searchedAnime = scan.nextLine().toUpperCase();
        if (animeGenre.containsKey(searchedAnime)) {
            System.out.println("The anime " + searchedAnime + " has genres " + animeGenre.get(searchedAnime));
        } else {
            System.out.println("That anime is not stored! Try again");
        }
    }

    /**
     * @param scan Scanner
     * @return True if user does not want to exit program, otherwise false
     */
    public static boolean exitToMain(Scanner scan) {
        System.out.println("-------------------------------------");
        System.out.println("Are you sure you want to quit back to the Main Menu? (Yes:1, No:0)");
        System.out.println("-------------------------------------");
        try{
            return !(scan.nextInt() == 1);
        }catch (InputMismatchException e){
            System.out.println("-------------------------------------");
            System.out.println("Error On Exit!");
            System.out.println("-------------------------------------");
            scan.nextLine();
            return true;
        }
    }

    /**
     * @param animeList ArrayList
     */
    public static void allAnimeTracked(ArrayList<String> animeList) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("The anime(s) currently tracked:");
        if (animeList.size() == 0) {
            System.out.println("No anime currently being tracked!");
        } else {
            for (int i = 0; i < animeList.size(); i++) {
                System.out.println(i + 1 + ") " + animeList.get(i));
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public static void helpOutputCommands() {
        System.out.print(
                """     
                        -----------------------------------------------------------
                        Print All Anime Tracked -> Displays all anime currently being tracked
                        Total Watch Time -> Displays total watch time accumulated across all anime
                        Top Streamed Anime -> Displays your top streamed anime, determined by watch time
                        Top Streamed Genre -> Displays top anime genre, determined by watch time
                        Anime By Genre -> Given an anime, returns all the genres of anime (from current tracked anime)
                                                
                        exit -> exit to main menu
                        help -> displays help for commands
                        -----------------------------------------------------------
                        """);
    }
}
