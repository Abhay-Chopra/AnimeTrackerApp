package anime;

import anime.util.Library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private boolean notQuit = true;
    private Library animeList = new Library();
    private Scanner scanner = new Scanner(System.in);

    private final String[] MAIN_CMDS = {"Add Anime", "Remove Anime", "Help", "Output Commands", "Exit Program"};
    private final String[] OUTPUT_CMDS = {"Print All Anime Tracked", "Total Watch Time", "Top Streamed Anime",
            "Top Streamed Genre", "Anime By Genre", "View Ratings", "View Studios", "Help", "Exit to Main Menu"};

    public Menu(){
        printHeader();
    }

    private void printHeader(){
        System.out.println("-----------------------------");
        System.out.println("Brandon's And Abhay's Anime List");
        System.out.println("-----------------------------");
    }

    public void Start() {
        int input;
        do {
            printMainCMDS();
            try {
                input = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                input = 0;
                scanner.nextLine();
            }
            switch (input) {
                case 1 ->
                case 2 ->
                case 3 ->
                case 4 ->
                case 5 ->
                default ->
            }
        }
        while (notQuit);
    }


    private void printMainCMDS() {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < MAIN_CMDS.length; i++)
            System.out.println(i + 1 + ") " + MAIN_CMDS[i]);
    }

    /**
     *  Displays quick usability guide for each command (for output command options)
     */
    private static void helpOutputCommands() {
        System.out.print(
                """     
                        -----------------------------------------------------------
                        Print All Anime Tracked -> Displays all anime currently being tracked
                        Total Watch Time -> Displays total watch time accumulated across all anime
                        Top Streamed Anime -> Displays your top streamed anime, determined by watch time
                        Top Streamed Genre -> Displays top anime genre, determined by watch time
                        Anime By Genre -> Given an anime, returns all the genres of anime (from current tracked anime)
                        View Ratings -> See all tracked anime and their ratings
                        View Studios -> See all currently tracked studios
                                                
                        exit -> exit to main menu
                        help -> displays help for commands
                        -----------------------------------------------------------
                        """);
    }

    /**
     * Confirms exit from output command menu to main menu
     * @param scan Scanner
     * @return returns Boolean => true if user does not want to exit program, otherwise false
     */
    private static boolean exitToMain(Scanner scan) {
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
}
