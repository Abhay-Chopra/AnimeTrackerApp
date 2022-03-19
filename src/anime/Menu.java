package anime;

import anime.Entity.Anime;
import anime.Entity.SeasonAnime;
import anime.util.Library;
import anime.util.Reader;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public final class Menu {

    private boolean notQuit = true;
    private Library animeList = new Library();
    private Scanner scanner = new Scanner(System.in);

    private static final String[] MAIN_CMDS = {"Input from File", "Add Anime", "Remove Anime", "Help", "Output Commands", "Exit Program"};
    private static final String[] OUTPUT_CMDS = {"Print All Anime Tracked", "Total Watch Time", "Top Streamed Anime",
            "Top Streamed Genre", "Anime By Genre", "View Ratings", "View Studios", "Help", "Exit to Main Menu"};
    private static final String[] ANIME_CMDS = {"Original", "Alternate"};

    public Menu(){
        printHeader();
    }

    private void printHeader() {
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
                case 1 -> getInputFromFile(scanner, animeList);
                case 2 -> getInputFromCMD(scanner, animeList);
                case 3 -> remove(scanner, animeList);
                case 4 -> help();
                case 5 -> outputCases(scanner, animeList);
                case 6 -> notQuit = setQuit(scanner);
                default -> printError();
            }
        }
        while (notQuit);
    }

    /**
     * Creates or updates library given file from user
     * @param scanner           Scanner for getting input from user
     * @param animeList         Library of stored anime and their info
     */
    private static void getInputFromFile(Scanner scanner, Library animeList) {
        //TODO Complete function getInputFromFile
        System.out.print("Enter the anime file you want to read: ");
        String fileName = scanner.nextLine();
        Reader.Import(fileName);
    }

    /**
     * Input function that sets up case structure for main menu interface
     *
     * @param scanner              Scanner for getting input from user
     * @param animeList         Library containing all Anime and their information
     */
    private static void getInputFromCMD(Scanner scanner, Library animeList) {
        System.out.println("What kind of anime are we adding? (anything else to quit)");
        for(int i = 0; i < ANIME_CMDS.length; i++)
            System.out.println(i + ") " + ANIME_CMDS[i]);

        int input = scanner.nextInt();

        switch(input) {
            case 0 -> getAnimeFromUser(scanner, animeList);
            case 1 -> getAlternateAnime(scanner, animeList);
        }
    }

    /**
     * Adding a ORIGINAL ANIME to the library
     * @param scanner for input
     * @param animeList
     */
    private static void getAnimeFromUser(Scanner scanner, Library animeList) {
        eatNewLine(scanner);
        System.out.println("What anime would you like to add?");
        String animeTitle = scanner.nextLine().toUpperCase();
        if(animeList.contains(animeTitle)) {
            System.out.println("That anime is already tracked! Please Try Again");
            return;
        }

        System.out.println("What genres does this anime fall under? (anything else when finished)");
        for(String genre : Anime.LIST_OF_GENRES) {
            System.out.println(genre);
        }

        ArrayList<String> addedGenres = new ArrayList<>();
        String genreToAdd = "";
        do {
            genreToAdd = scanner.nextLine().toUpperCase();
            addedGenres.add(genreToAdd);
        } while(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

        System.out.println("What themes does this anime fall under? (anything else when finished)");
        for(String theme : Anime.LIST_OF_THEMES){
            System.out.println(theme);
        }

        ArrayList<String> addedThemes = new ArrayList<>();
        String themeToAdd = "";
        do {
            themeToAdd = scanner.nextLine().toUpperCase();
            addedThemes.add(genreToAdd);
        } while(Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd));

        System.out.println("How many episodes have you watched?");
        int episodes = scanner.nextInt();

        System.out.println("What would you rate this anime, out of 10?");
        double rating = scanner.nextDouble();

        System.out.println("What's the status on the current anime? (select by integer)");
        for(int i = 0; i < Anime.Status.values().length; i++) {
            System.out.println((i)+") " + Anime.Status.values()[i]);
        }
        Anime.Status status = Anime.Status.values()[scanner.nextInt()];

        System.out.println("What season did this anime air? (select by integer)");
        for(int i = 0; i < Anime.Season.values().length; i++) {
            System.out.println((i)+") " + Anime.Season.values()[i]);
        }
        Anime.Season season = Anime.Season.values()[scanner.nextInt()];

        Anime newAnime = new Anime(animeTitle, addedGenres, addedThemes, episodes, rating, status, season);

        animeList.addAnime(newAnime);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Completed adding " + newAnime.getName() + " to the list!");
        System.out.println("-----------------------------------------------------------");
    }

    private static void getAlternateAnime(Scanner scanner, Library animeList) {
        if(animeList.getAnime().length == 0) {
            System.out.println("No anime being tracked to branch from!");
            return;
        }

        System.out.println("What anime is this branched from?");

        Anime[] bufferAnime = animeList.getAnime();
        for(int i = 0; i < bufferAnime.length; i++){
            System.out.println(i+") " + bufferAnime[i].getName());
        }

        Anime parentAnime = bufferAnime[scanner.nextInt()];
        eatNewLine(scanner);

        System.out.println("What is the title of this anime?");
        String animeTitle = scanner.nextLine().toUpperCase();
        if(animeList.contains(animeTitle)) {
            System.out.println("That anime is already tracked! Please Try Again");
            return;
        }

        System.out.println("What genres does this anime fall under? (anything else when finished)");
        for(String genre : Anime.LIST_OF_GENRES) {
            System.out.println(genre);
        }

        ArrayList<String> addedGenres = new ArrayList<>();
        String genreToAdd = "";
        do {
            genreToAdd = scanner.nextLine().toUpperCase();
            addedGenres.add(genreToAdd);
        } while(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

        System.out.println("What themes does this anime fall under? (anything else when finished)");
        for(String theme : Anime.LIST_OF_THEMES){
            System.out.println(theme);
        }

        ArrayList<String> addedThemes = new ArrayList<>();
        String themeToAdd = "";
        do {
            themeToAdd = scanner.nextLine().toUpperCase();
            addedThemes.add(genreToAdd);
        } while(Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd));

        System.out.println("How many episodes have you watched?");
        int episodes = scanner.nextInt();

        System.out.println("What would you rate this anime, out of 10?");
        double rating = scanner.nextDouble();

        System.out.println("What's the status on the current anime? (select by integer)");
        for(int i = 0; i < Anime.Status.values().length; i++) {
            System.out.println((i)+") " + Anime.Status.values()[i]);
        }
        Anime.Status status = Anime.Status.values()[scanner.nextInt()];

        System.out.println("What season did this anime air? (select by integer)");
        for(int i = 0; i < Anime.Season.values().length; i++) {
            System.out.println((i)+") " + Anime.Season.values()[i]);
        }
        Anime.Season season = Anime.Season.values()[scanner.nextInt()];

        SeasonAnime sAnime = new SeasonAnime(parentAnime, animeTitle, addedGenres, addedThemes, episodes, rating, status, season);

        animeList.addAnime(sAnime);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Completed adding " + sAnime.getName() + " to the list!");
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Removes anime from all datastructures given a name (by user)
     * @param scan              Scanner for getting input from user
     * @param animeList         Library of stored anime and their info
     */
    private static void remove(Scanner scan, Library animeList) {
        if(animeList.getAnime().length == 0) {
            System.out.println("-------------------------------------");
            System.out.println("No anime currently being tracked");
            System.out.println("-------------------------------------");
        } else
        {
            System.out.println("Which of the following would you like to remove?");
            System.out.println("------------------------------------------------");
            for (Anime anime : animeList.getAnime()) {
                System.out.println(anime);
                System.out.println("------------------------------------------------");
            }
            String animeToRemove = scan.nextLine().toUpperCase();
            if (animeList.contains(animeToRemove)) {
                animeList.removeAnime(animeToRemove);
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
     * @param scanner Scanner to get input from user
     * @return Boolean false, if user wants to exit program, otherwise returns true
     */
    private static boolean setQuit(Scanner scanner) {
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
    private static void printError() {
        System.out.println("-------------------------------------");
        System.out.println("Error reading command, please enter command by integer again!");
        System.out.println("-------------------------------------");
    }

    /**
     * Sets up the output command structure for output commands menu
     * @param scanner              Scanner for getting input from user
     * @param animeList         ArrayList of stored anime
     */
    private static void outputCases(Scanner scanner, Library animeList) {
        boolean notQuit = true;
        //Looping for the output commands interface
        do {
            printOutputCMDS();
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                //TODO Add toString to Library class and Anime class as well
                case 1 -> System.out.println(animeList);
                case 2 -> System.out.println(printTopBorder() + animeList.totalWatchTime() + printBottomBorder());
                case 3 -> System.out.println(printTopBorder()  + animeList.topStreamedAnime() + printBottomBorder());
                case 4 -> System.out.println(printTopBorder()  + animeList.topStreamedGenre() + printBottomBorder());
                case 5 -> System.out.println(printTopBorder()  + animeList.getGenreByAnime(scanner) + printBottomBorder());
                case 6 -> System.out.println(printTopBorder()  + animeList.getAnimeRatings() + printBottomBorder());
                case 7 -> System.out.println(printTopBorder()  + animeList.getStudios() + printBottomBorder());
                case 8 -> helpOutputCommands();
                case 9 -> notQuit = exitToMain(scanner);
                default -> printError();
            }
        }
        while (notQuit);
    }

    private static String printTopBorder() {
        return "-----------------------------------------------------------\n";
    }

    private static String printBottomBorder(){
        return "\n-----------------------------------------------------------";
    }

    private static void printOutputCMDS() {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < OUTPUT_CMDS.length; i++)
            System.out.println(i + 1 + ") " + OUTPUT_CMDS[i]);
    }

    private static void printMainCMDS() {
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
     * @param scanner Scanner
     * @return returns Boolean => true if user does not want to exit program, otherwise false
     */
    private static boolean exitToMain(Scanner scanner) {
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

    private static void eatNewLine(Scanner scanner) {
        scanner.nextLine();
    }
}
