package anime;

import anime.Entity.Anime;
import anime.Entity.SeasonAnime;
import anime.Entity.Studio;
import anime.util.Library;
import anime.util.Reader;

import java.util.*;

public class Menu {

    private boolean notQuit = true;
    private final Library animeLibrary = new Library();
    private final Scanner scanner = new Scanner(System.in);

    private final String[] MAIN_CMDS = {"Input from File", "Add Anime", "Remove Anime", "Help", "Output Commands", "Exit Program"};
    private final String[] OUTPUT_CMDS = {"Print All Anime Tracked", "Total Watch Time", "Top Streamed Anime",
            "Top Streamed Genre", "Anime By Genre", "View Ratings", "View Studios", "Help", "Exit to Main Menu"};
    private final String[] ANIME_CMDS = {"Original", "Alternate"};

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
        //TODO Complete function getInputFromFile
        System.out.print("Enter the anime file you want to read: ");
        String fileName = scanner.nextLine();
        Reader.Import(fileName);
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
        eatNewLine();
        System.out.println("What anime would you like to add?");
        String animeTitle = scanner.nextLine().toUpperCase();
        if(animeLibrary.containsAnime(animeTitle)) {
            System.out.println("That anime is already tracked! Please Try Again");
            return;
        }

        System.out.println("What genres does this anime fall under? (anything else when finished)");
        for(String genre : Anime.LIST_OF_GENRES) {
            System.out.println(genre);
        }

        ArrayList<String> addedGenres = new ArrayList<>();
        String genreToAdd;
        do {
            genreToAdd = scanner.nextLine().toUpperCase();
            if(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd)) {
                addedGenres.add(genreToAdd);
                System.out.println("Added " + genreToAdd);
            }
        } while(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

        System.out.println("What themes does this anime fall under? (anything else when finished)");
        for(String theme : Anime.LIST_OF_THEMES){
            System.out.println(theme);
        }

        ArrayList<String> addedThemes = new ArrayList<>();
        String themeToAdd;
        do {
            themeToAdd = scanner.nextLine().toUpperCase();
            if(Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd)) {
                addedThemes.add(themeToAdd);
                System.out.println("Added " + themeToAdd );
            }
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
        eatNewLine();

        System.out.println("What season did this anime air? (select by integer)");
        for(int i = 0; i < Anime.Season.values().length; i++) {
            System.out.println((i)+") " + Anime.Season.values()[i]);
        }
        Anime.Season season = Anime.Season.values()[scanner.nextInt()];
        eatNewLine();

        //Studio (this is kinda sussy)
        Studio animeStudio;
        //Check if the current list of studios is empty
        if(animeLibrary.getStudios().length == 0) {
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
            for(int i = 0; i < bufferStudios.length; i++) {
                System.out.println(i+") " + bufferStudios[i].getName());
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

        Anime newAnime = new Anime(animeTitle, addedGenres, addedThemes, episodes, rating, status, season, animeStudio);

        animeStudio.addAnime(newAnime);
        animeLibrary.addAnime(newAnime);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Completed adding " + newAnime.getName() + " to the list!");
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Get an alternate anime (Season, OVA, Movie) from the user
     */
    private void getAlternateAnime() {
        if(animeLibrary.getAnime().length == 0) {
            System.out.println("No anime being tracked to branch from!");
            return;
        }

        System.out.println("What anime is this branched from?");

        Anime[] bufferAnime = animeLibrary.getAnime();
        for(int i = 0; i < bufferAnime.length; i++){
            System.out.println(i+") " + bufferAnime[i].getName());
        }

        Anime parentAnime = bufferAnime[scanner.nextInt()];
        eatNewLine();

        System.out.println("What is the title of this anime?");
        String animeTitle = scanner.nextLine().toUpperCase();
        if(animeLibrary.containsAnime(animeTitle)) {
            System.out.println("That anime is already tracked! Please Try Again");
            return;
        }

        System.out.println("What genres does this anime fall under? (anything else when finished)");
        for(String genre : Anime.LIST_OF_GENRES) {
            System.out.println(genre);
        }

        ArrayList<String> addedGenres = new ArrayList<>();
        String genreToAdd;
        do {
            genreToAdd = scanner.nextLine().toUpperCase();
            if(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd)) {
                addedGenres.add(genreToAdd);
                System.out.println("Added " + genreToAdd);
            }
        } while(Arrays.asList(Anime.LIST_OF_GENRES).contains(genreToAdd));

        System.out.println("What themes does this anime fall under? (anything else when finished)");
        for(String theme : Anime.LIST_OF_THEMES){
            System.out.println(theme);
        }

        ArrayList<String> addedThemes = new ArrayList<>();
        String themeToAdd;
        do {
            themeToAdd = scanner.nextLine().toUpperCase();
            if(Arrays.asList(Anime.LIST_OF_THEMES).contains(themeToAdd)) {
                addedThemes.add(genreToAdd);
                System.out.println("Added " + themeToAdd );
            }
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

        //Studio
        Studio animeStudio;
        if(animeLibrary.getStudios().length == 0) {
            System.out.println("No studio's yet added!");
            System.out.println("What studio produced this name?");
            String animeStudioName = scanner.nextLine().toUpperCase();
            animeStudio = new Studio(animeStudioName);
            animeLibrary.addStudio(animeStudio);
        } else {
            System.out.println("Did any of these studios produced this anime?");
            Studio[] bufferStudios = animeLibrary.getStudios();
            for(int i = 0; i < bufferStudios.length; i++) {
                System.out.println(i+") " + bufferStudios[i].getName());
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

        SeasonAnime sAnime = new SeasonAnime(parentAnime, animeTitle, addedGenres, addedThemes, episodes, rating, status, season, animeStudio);

        animeStudio.addAnime(sAnime);
        animeLibrary.addAnime(sAnime);

        System.out.println("-----------------------------------------------------------");
        System.out.println("Completed adding " + sAnime.getName() + " to the list!");
        System.out.println("-----------------------------------------------------------");
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
                //TODO Add toString to Library class and Anime class as well
                case 1 -> System.out.println(animeLibrary.allAnimeTracked());
                case 2 -> System.out.println(printTopBorder() + animeLibrary.totalWatchTime() + printBottomBorder());
                case 3 -> System.out.println(printTopBorder()  + animeLibrary.topStreamedAnime() + printBottomBorder());
                case 4 -> System.out.println(printTopBorder()  + animeLibrary.topStreamedGenre() + printBottomBorder());
                case 5 -> System.out.println(printTopBorder()  + animeLibrary.getGenreByAnime(getAnimeNameToSearch()) + printBottomBorder());
                case 6 -> System.out.println(printTopBorder()  + animeLibrary.getAnimeRatings() + printBottomBorder());
                case 7 -> System.out.println(printTopBorder()  + animeLibrary.allStudiostracked() + printBottomBorder());
                case 8 -> helpOutputCommands();
                case 9 -> notQuit = exitToMain(scanner);
                default -> printError();
            }
        }
        while (notQuit);
    }

    private String getAnimeNameToSearch() {
        if(animeLibrary.getAnime().length == 0) {
            System.out.println("No anime currently being tracked!");
            return null;
        }

        System.out.println("What anime would you like the genres for?");
        for(Anime a : animeLibrary.getAnime()){
            System.out.println(a.getName() + "\n");
        }
        eatNewLine();

        return scanner.nextLine().toUpperCase();
    }

    private String printTopBorder() {
        return "-----------------------------------------------------------\n";
    }

    private String printBottomBorder(){
        return "\n-----------------------------------------------------------";
    }

    private void printOutputCMDS() {
        System.out.println("Select A Command With The Number:");

        //Display our list of commands
        for (int i = 0; i < OUTPUT_CMDS.length; i++)
            System.out.println(i + 1 + ") " + OUTPUT_CMDS[i]);
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

    private void eatNewLine() {
        scanner.nextLine();
    }
}
