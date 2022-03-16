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

}
