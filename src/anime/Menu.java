package anime;

import anime.Entity.Anime;
import anime.Entity.SeasonAnime;
import anime.Entity.Studio;
import anime.util.Library;
import anime.util.Reader;

import java.io.File;
import java.util.*;

/**
 * Menu class that allows for all the operations within program (I/O and file handling)
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * TA: 06 (W/ Amir)
 * March 24th, 2022
 */
public class Menu {

    private boolean notQuit = true;
    private final Library animeLibrary = new Library();
    private final Scanner scanner = new Scanner(System.in);

    private final String[] MAIN_CMDS = {"Input from File", "Add Anime", "Remove Anime", "Help", "Output Commands", "Exit Program"};
    private final String[] OUTPUT_CMDS = {"Print All Anime Tracked", "Total Watch Time", "Top Streamed Anime",
            "Top Streamed Genre", "Anime By Genre", "View Ratings", "View Studios", "Save to File" ,"Help", "Exit to Main Menu"};
    private final String[] ANIME_CMDS = {"Original", "Alternate"};

    /**
     * Constructor for the Menu Class
     * @param args Array containing the Command Line Arguments
     */
    public Menu(String[] args){
        printHeader();
        //Checking commandline arguments
        if(args.length == 1){
            //Pre-Loading file
            ArrayList<Anime> animeList = Reader.Import(new File(args[0]));
            animeLibrary.addBulkAnime(animeList);
            System.out.printf("Pre-Loading...%s file%n%n", args[0]);
        }
    }

    /**
     * Internal system to display and handle all the cases of the menu system
     */
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
                case 1 -> getInputFromFile();
                case 2 -> getInputFromCMD();
                case 3 -> remove();
                case 4 -> help();
                case 5 -> outputCases();
                case 6 -> notQuit = setQuit();
                default -> printError();
            }
        }
        while (notQuit);
    }

    /**
     * Creates or updates library given file from user
     */
    private void getInputFromFile() {
        //Getting a file name from the user (that has stored data)
        System.out.print("Enter the anime file you want to read: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);

        // flag for the while loop
        boolean loopFlag = true;
        while (loopFlag){
            //Confirming that the file is valid
            if(file.isFile() && file.canRead() && file.exists()){
                ArrayList<Anime> animeList = Reader.Import(file);
                animeLibrary.addBulkAnime(animeList);
                loopFlag = false;
                System.out.printf("%sSuccessfully read from %s!%s\n", printTopBorder(),file,printBottomBorder());
            }
            else{
                System.err.print("Please enter a valid file: ");
                fileName = scanner.nextLine();
                file = new File(fileName);
            }
        }
    }

    /**
     * Input function that sets up case structure for main menu interface
     */
    private void getInputFromCMD() {
        System.out.println("What kind of anime are we adding? (anything else to quit)");
        for(int i = 0; i < ANIME_CMDS.length; i++)
            System.out.println(i + ") " + ANIME_CMDS[i]);

        int input;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input detected! Returning to menu");
            eatNewLine();
            return;
        }

        switch(input) {
            case 0 -> getAnimeFromUser();
            case 1 -> getAlternateAnime();
            default -> System.out.println("Nothing chosen! Returning to menu!");
        }
    }

    /**
     * Adding a ORIGINAL ANIME to the library
     */
    private void getAnimeFromUser() {
        try {
            //clear the new line
            eatNewLine();
            //ask the for an anime title to add and check if we already have it
            System.out.println("What anime would you like to add?");
            String animeTitle = scanner.nextLine().toUpperCase();
            if (animeLibrary.containsAnime(animeTitle)) {
                System.out.println("That anime is already tracked! Please Try Again");
                return;
            }

            //list out the genres available and get them from the user
            System.out.println("What genres does this anime fall under? (anything else when finished)");
            for (String genre : Anime.LIST_OF_GENRES) {
                System.out.println(genre);
            }

            //getting genre input
            ArrayList<String> addedGenres = new ArrayList<>();
            String genreToAdd;
            do {
                genreToAdd = scanner.nextLine().toUpperCase();
                if (Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd)) {
                    addedGenres.add(genreToAdd);
                    System.out.println("Added " + genreToAdd);
                }
            } while (Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

            //listing the themes for the user
            System.out.println("What themes does this anime fall under? (anything else when finished)");
            for (String theme : Anime.LIST_OF_THEMES) {
                System.out.println(theme);
            }

            //getting theme input from the user
            ArrayList<String> addedThemes = new ArrayList<>();
            String themeToAdd;
            do {
                themeToAdd = scanner.nextLine().toUpperCase();
                if (Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd)) {
                    addedThemes.add(themeToAdd);
                    System.out.println("Added " + themeToAdd);
                }
            } while (Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd));

            //getting episodes
            System.out.println("How many episodes have you watched?");
            int episodes = scanner.nextInt();

            //getting rating
            System.out.println("What would you rate this anime, out of 10?");
            double rating = scanner.nextDouble();

            //iterate the status enum and print options to the users
            System.out.println("What's the status on the current anime? (select by integer)");
            for (int i = 0; i < Anime.Status.values().length; i++) {
                System.out.println((i) + ") " + Anime.Status.values()[i]);
            }
            Anime.Status status = Anime.Status.values()[scanner.nextInt()];
            eatNewLine();

            //iterate the season enum and print the option to the users
            System.out.println("What season did this anime air? (select by integer)");
            for (int i = 0; i < Anime.Season.values().length; i++) {
                System.out.println((i) + ") " + Anime.Season.values()[i]);
            }
            Anime.Season season = Anime.Season.values()[scanner.nextInt()];
            eatNewLine();

            //Studio
            Studio animeStudio;
            //Check if the current list of studios is empty
            if (animeLibrary.getStudios().length == 0) {
                //Go about adding a new studio for the anime, and list of studios tracked
                System.out.println("No studio's yet added!");
                System.out.println("What studio produced this name?");
                String animeStudioName = scanner.nextLine().toUpperCase();
                animeStudio = new Studio(animeStudioName);
                animeLibrary.addStudio(animeStudio);
            } else {
                //Iterate and output the current tracked studios to the user for selection
                System.out.println("Did any of these studios produced this anime? (anything else for adding a new one)");
                Studio[] bufferStudios = animeLibrary.getStudios();
                for (int i = 0; i < bufferStudios.length; i++) {
                    System.out.println(i + ") " + bufferStudios[i].getName());
                }
                //If anything out of the array, or mismatch input, we will add a new studio
                try {
                    animeStudio = bufferStudios[scanner.nextInt()];
                    eatNewLine();
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("What studio produced this name?");
                    String animeStudioName = scanner.nextLine().toUpperCase();
                    animeStudio = new Studio(animeStudioName);
                    animeLibrary.addStudio(animeStudio);
                }
            }

            //create the anime and store it in the library
            Anime newAnime = new Anime(animeTitle, addedGenres, addedThemes, episodes, rating, status, season, animeStudio);

            animeStudio.addAnime(newAnime);
            animeLibrary.addAnime(newAnime);

            System.out.println("-----------------------------------------------------------");
            System.out.println("Completed adding " + newAnime.getName() + " to the list!");
            System.out.println("-----------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("Error while adding anime: "+e.toString());
            eatNewLine();
        }
    }

    /**
     * Get an alternate anime (Season, OVA, Movie) from the user
     */
    private void getAlternateAnime() {
        try {
            //if no anime to parent from return this
            if (animeLibrary.getAnime().length == 0) {
                System.out.println("No anime being tracked to branch from!");
                return;
            }

            System.out.println("What anime is this branched from?");

            //get the array of anime and print to the user for selection
            Anime[] bufferAnime = animeLibrary.getAnime();
            for (int i = 0; i < bufferAnime.length; i++) {
                System.out.println(i + ") " + bufferAnime[i].getName());
            }

            //get the parent anime of this alternate anime
            Anime parentAnime = bufferAnime[scanner.nextInt()];
            eatNewLine();

            //get the title of the anime
            System.out.println("What is the title of this anime?");
            String animeTitle = scanner.nextLine().toUpperCase();
            if (animeLibrary.containsAnime(animeTitle)) {
                System.out.println("That anime is already tracked! Please Try Again");
                return;
            }

            //print out genres and get them from the user
            System.out.println("What genres does this anime fall under? (anything else when finished)");
            for (String genre : Anime.LIST_OF_GENRES) {
                System.out.println(genre);
            }

            ArrayList<String> addedGenres = new ArrayList<>();
            String genreToAdd;
            do {
                genreToAdd = scanner.nextLine().toUpperCase();
                if (Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd)) {
                    addedGenres.add(genreToAdd);
                    System.out.println("Added " + genreToAdd);
                }
            } while (Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

            //print out the themes and get them from the user
            System.out.println("What themes does this anime fall under? (anything else when finished)");
            for (String theme : Anime.LIST_OF_THEMES) {
                System.out.println(theme);
            }

            ArrayList<String> addedThemes = new ArrayList<>();
            String themeToAdd;
            do {
                themeToAdd = scanner.nextLine().toUpperCase();
                if (Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd)) {
                    addedThemes.add(themeToAdd);
                    System.out.println("Added " + themeToAdd);
                }
            } while (Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd));

            //get episodes
            System.out.println("How many episodes have you watched?");
            int episodes = 0;
            try {
                episodes = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Didn't give a count of episodes watched!");
                System.exit(1);
            }

            //get rating
            System.out.println("What would you rate this anime, out of 10?");
            double rating = scanner.nextDouble();

            //print out status and get the status from the user
            System.out.println("What's the status on the current anime? (select by integer)");
            for (int i = 0; i < Anime.Status.values().length; i++) {
                System.out.println((i) + ") " + Anime.Status.values()[i]);
            }
            Anime.Status status = Anime.Status.values()[scanner.nextInt()];

            //print the seasons and get the season from the user
            System.out.println("What season did this anime air? (select by integer)");
            for (int i = 0; i < Anime.Season.values().length; i++) {
                System.out.println((i) + ") " + Anime.Season.values()[i]);
            }
            Anime.Season season = Anime.Season.values()[scanner.nextInt()];

            //Studio
            Studio animeStudio;
            if (animeLibrary.getStudios().length == 0) {
                System.out.println("No studio's yet added!");
                System.out.println("What studio produced this name?");
                String animeStudioName = scanner.nextLine().toUpperCase();
                animeStudio = new Studio(animeStudioName);
                animeLibrary.addStudio(animeStudio);
            } else {
                System.out.println("Did any of these studios produced this anime?");
                Studio[] bufferStudios = animeLibrary.getStudios();
                for (int i = 0; i < bufferStudios.length; i++) {
                    System.out.println(i + ") " + bufferStudios[i].getName());
                }
                System.out.println("NONE");
                try {
                    animeStudio = bufferStudios[scanner.nextInt()];
                    eatNewLine();
                } catch (InputMismatchException e) {
                    System.out.println("What studio produced this name?");
                    String animeStudioName = scanner.nextLine().toUpperCase();
                    animeStudio = new Studio(animeStudioName);
                    animeLibrary.addStudio(animeStudio);
                }
            }

            //create the sub anime and ship it to the library
            SeasonAnime sAnime = new SeasonAnime(parentAnime, animeTitle, addedGenres, addedThemes, episodes, rating, status, season, animeStudio);

            animeStudio.addAnime(sAnime);
            animeLibrary.addAnime(sAnime);

            System.out.println("-----------------------------------------------------------");
            System.out.println("Completed adding " + sAnime.getName() + " to the list!");
            System.out.println("-----------------------------------------------------------");
        } catch (InputMismatchException e) {
            System.out.println("Error while adding anime: "+e.toString());
            eatNewLine();
        }
    }

    /**
     * Removes anime from all datastructures given a name (by user)
     */
    private void remove() {
        if(animeLibrary.getAnime().length == 0) {
            System.out.println("-------------------------------------");
            System.out.println("No anime currently being tracked");
            System.out.println("-------------------------------------");
        } else
        {
            System.out.println("Which of the following would you like to remove?");
            System.out.println("------------------------------------------------");
            for (Anime anime : animeLibrary.getAnime()) {
                System.out.println(anime);
                System.out.println("------------------------------------------------");
            }
            String animeToRemove = scanner.nextLine().toUpperCase();
            if (animeLibrary.containsAnime(animeToRemove)) {
                animeLibrary.removeAnime(animeToRemove);
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
     * Function confirms that the user wants to exit the program
     * @return Boolean false, if user wants to exit program, otherwise returns true
     */
    private boolean setQuit() {
        System.out.println("-------------------------------------");
        System.out.println("Are you sure you wish to quit? (Yes:1, No:0)");
        System.out.println("-------------------------------------");
        try{
            return !(scanner.nextInt() == 1);
        }catch (InputMismatchException e){
            System.out.println("-------------------------------------");
            System.out.println("Error On Exit!");
            System.out.println("-------------------------------------");
            scanner.nextLine();
            return true;
        }
    }

    /**
     * Function prints an indication that no valid menu option was selected
     */
    private void printError() {
        System.out.println("-------------------------------------");
        System.out.println("Error reading command, please enter command by integer again!");
        System.out.println("-------------------------------------");
    }

    /**
     * Sets up the output command structure for output commands menu
     */
    private void outputCases() {
        boolean notQuit = true;
        //Looping for the output commands interface
        do {
            printOutputCMDS();
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1 -> System.out.println(animeLibrary.allAnimeTracked());
                case 2 -> System.out.println(printTopBorder() + animeLibrary.totalWatchTime() + printBottomBorder());
                case 3 -> System.out.println(printTopBorder()  + animeLibrary.topStreamedAnime() + printBottomBorder());
                case 4 -> System.out.println(printTopBorder()  + animeLibrary.topStreamedGenre() + printBottomBorder());
                case 5 -> System.out.println(printTopBorder()  + animeLibrary.getGenreByAnime(getAnimeNameToSearch()) + printBottomBorder());
                case 6 -> System.out.println(printTopBorder()  + animeLibrary.getAnimeRatings() + printBottomBorder());
                case 7 -> System.out.println(printTopBorder()  + animeLibrary.allStudiosTracked() + printBottomBorder());
                case 8 -> callReader();
                case 9 -> helpOutputCommands();
                case 10 -> notQuit = exitToMain(scanner);
                default -> printError();
            }
        }
        while (notQuit);
    }

    /**
     * Calls reader to save all data currently being tracked into a text file
     */
    private void callReader() {
        System.out.print("Please provide the name of the file you want to write to: ");
        String fileName = scanner.nextLine();
        Reader.save(animeLibrary.getAnime(), fileName);
        System.out.printf("%sSuccessfully saved to file %s%s%n", printTopBorder(), fileName, printBottomBorder());
    }

    /**
     *
     * @return Anime Name
     */
    private String getAnimeNameToSearch() {
        if(animeLibrary.getAnime().length == 0) {
            System.out.println("No anime currently being tracked!");
            return null;
        }

        System.out.println("What anime would you like the genres for?");
        for(Anime a : animeLibrary.getAnime()){
            System.out.println(a.getName());
        }

        return scanner.nextLine().toUpperCase();
    }

    private String printTopBorder() {
        return "-----------------------------------------------------------\n";
    }

    private String printBottomBorder(){
        return "\n-----------------------------------------------------------";
    }

    /**
     * Displays the CMDS for the inner menu
     */
    private void printOutputCMDS() {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < OUTPUT_CMDS.length; i++)
            System.out.println(i + 1 + ") " + OUTPUT_CMDS[i]);
    }

    /**
     * Displays the CMDS for the outer menu
     */
    private void printMainCMDS() {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < MAIN_CMDS.length; i++)
            System.out.println(i + 1 + ") " + MAIN_CMDS[i]);
    }

    /**
     *  Displays quick usability guide for each command (for output command options)
     */
    private void helpOutputCommands() {
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
     * @param scanner Scanner
     * @return returns Boolean => true if user does not want to exit program, otherwise false
     */
    private boolean exitToMain(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("Are you sure you want to quit back to the Main Menu? (Yes:1, No:0)");
        System.out.println("-------------------------------------");
        try{
            return !(scanner.nextInt() == 1);
        }catch (InputMismatchException e){
            System.out.println("-------------------------------------");
            System.out.println("Error On Exit!");
            System.out.println("-------------------------------------");
            scanner.nextLine();
            return true;
        }

    }

    /**
     * Displays quick usability guide for each command (from main menu)
     */
    private void help() {
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
     * Gets rid of a new line character from the user using the scanner
     */
    private void eatNewLine() {
        scanner.nextLine();
    }

    /**
     * Header for start of program
     */
    private void printHeader() {
        System.out.println("-----------------------------");
        System.out.println("Brandon's And Abhay's Anime List");
        System.out.println("-----------------------------");
    }
}
