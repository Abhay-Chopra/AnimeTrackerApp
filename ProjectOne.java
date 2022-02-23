import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06 (w/ Amir)
 * Objective: Software that tracks the user's top streamed anime category
 */
public class ProjectOne {
    //Episode time in minutes
    private static final int AVG_EP_TIME = 23;

    public static void main(String[] args) {
        boolean notQuit = true;
        String[] cmds = {"Add", "Remove", "Info", "Help", "Output Commands", "Exit Program"};

        //Structures for holding our data
        ArrayList<String> animeList = new ArrayList<>();
        ArrayList<String> studioList = new ArrayList<>();
        HashMap<String, ArrayList<String>> animeGenre = new HashMap<>();
        HashMap<String, Integer> animeEpisodeCount = new HashMap<>();
        HashMap<String, String> animeStudio = new HashMap<>();
        HashMap<String, Double> animeScore = new HashMap<>();

        //Printing out the title
        System.out.println("-----------------------------");
        System.out.println("Brandon's And Abhay's Anime List");
        System.out.println("-----------------------------");

        //Make a scanner to get user input
        Scanner scan = new Scanner(System.in);

        //Loop for main functionality of the program
        do {
            printInterface(cmds);
            int input = scan.nextInt();
            scan.nextLine();
            switch (input) {
                case 1 -> getUserInput(scan, animeList, studioList, animeGenre, animeEpisodeCount, animeStudio, animeScore);
                case 2 -> remove(animeList);
                case 3 -> displayInformation();
                case 4 -> help();
                case 5 -> outputCases(scan, animeList, studioList, animeGenre, animeEpisodeCount, animeStudio, animeScore);
                case 6 -> notQuit = setQuit(scan);
                default -> printError();
            }
        }
        while (notQuit);

        //Only get here upon user choosing to exit
        System.out.println("-------------------------------------");
        System.out.println("Thank you for using our anime list!");
        System.out.println("-------------------------------------");
    }

    private static void displayInformation() {

    }

    private static void printError() {
        System.out.println("Error reading command, please enter command by integer again!");
    }

    private static boolean setQuit(Scanner scan) {
        System.out.println("Are you sure you wish to quit? (Yes:1, No:0)");
        return !(scan.nextInt() == 1);
    }

    private static void remove(ArrayList<String> animeList) {
        System.out.println("Which of the following would you like to remove? ");
        for (int i = 0; i < animeList.size(); i++) {
            System.out.printf("%s ", i);
        }
    }

    /**
     * Displays help for each command usage
     */
    private static void help() {
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
    public static void getUserInput(Scanner scan,
                                    ArrayList<String> animeList,
                                    ArrayList<String> studioList,
                                    HashMap<String, ArrayList<String>> animeGenre,
                                    HashMap<String, Integer> animeEpisodeCount,
                                    HashMap<String, String> animeStudio,
                                    HashMap<String, Double> animeScore) {
        ArrayList<String> genres = new ArrayList<>();

        System.out.println("What anime would you like to add?");
        String newAnime = scan.nextLine();
        animeList.add(newAnime);

        System.out.println("What studio worked on the anime?");
        String newStudio = scan.nextLine();
        studioList.add(newStudio);
        animeStudio.put(newAnime, newStudio);

        System.out.println("What genres does this anime fall under (ie: romance, action [done when finished])?");
        String newGenre = scan.nextLine();
        do {
            if (!newGenre.isEmpty() || newGenre.trim().length() > 0)
                genres.add(newGenre);
            System.out.println("Another genre? [done when finished]");
            newGenre = scan.nextLine();
        } while (!newGenre.equals("done"));
        animeGenre.put(newAnime, genres);

        System.out.println("How many episode have you watched?");
        animeEpisodeCount.put(newAnime, scan.nextInt());

        System.out.println("What rating would you give this anime (1-10)");
        animeScore.put(newAnime, scan.nextDouble());

        System.out.println("\nCompleted adding " + newAnime + " to the list!");

        scan.nextLine();
    }

    /**
     * Prints out the interface, ie, all the different options
     *
     * @param cmds Array containing all the commands
     */
    private static void printInterface(String[] cmds) {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < cmds.length; i++)
            System.out.println(i + 1 + ") " + cmds[i]);
    }

    /**
     * Function for all the handling of output commands
     */
    private static void outputCases(Scanner scan,
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
                case 2 -> totalWatchTime();
                case 3 -> topStreamedAnime();
                case 4 -> topStreamedGenre();
                case 5 -> giveAnimeName();
                case 6 -> helpOutputCommands();
                case 7 -> notQuit = exitToMain(scan);
                default -> printError();
            }
        }
        while (notQuit);
    }

    private static void topStreamedAnime() {
    }

    private static void topStreamedGenre() {
    }

    private static void totalWatchTime() {
    }

    private static void giveAnimeName() {
    }

    private static boolean exitToMain(Scanner scan) {
        System.out.println("Are you sure you want to quit back to the Main Menu? (Yes:1, No:0)");
        return !(scan.nextInt() == 1);
    }

    private static void allAnimeTracked(ArrayList<String> animeList) {
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

    private static void helpOutputCommands() {
        System.out.print(
                """     
                        -----------------------------------------------------------
                        Print All Anime Tracked -> Displays all anime currently being tracked
                        Total Watch Time -> Displays total watch time accumulated across all anime
                        Top Streamed Anime -> Displays your top streamed anime, determined by watch time
                        Top Streamed Genre -> Displays top anime genre, determined by watch time
                        Anime By Genre -> Given a genre, provides an anime (from current tracked anime)
                        
                        exit -> exit to main menu
                        help -> displays help for commands
                        -----------------------------------------------------------
                        """);
    }

}
