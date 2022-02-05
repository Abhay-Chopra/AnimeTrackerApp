import java.util.*;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06 (w/ Amir)
 * Objective: Software that tracks the user's top streamed anime category
 */
public class ProjectOne
{
    public static void main(String[] args)
    {
        HashMap<String, HashMap<ArrayList<String>, Integer>> anime = new HashMap<>();
        boolean notQuit = true;
        String[] cmds = {"help", "add", "remove", "exit"};

        //Print out the list of available commands
        System.out.println("Welcome, please input one of the following commands!");
        for(String x : cmds)
        {
            System.out.print(x + " ");
        }

        //Make a scanner to get user input
        Scanner scan = new Scanner(System.in);
        do
        {
            String input = scan.nextLine();
            switch (input)
            {
                case "help" -> help(cmds);
                case "add" -> anime = getUserInput();
                case "remove" -> remove();
                case "exit" -> notQuit = setQuit(scan);
                default -> printError();
            }
        }
        while(notQuit);
    }

    private static void printError()
    {
        System.out.println("Error reading command, please enter again!");
    }

    private static boolean setQuit(Scanner scan)
    {
        System.out.println("Are you sure you wish to quit? (Y, N)");
        return !scan.nextLine().equals("Y");
    }

    private static void remove()
    {
    }

    /**
     * Display help for the command usage
     * @param cmds : an array of commands for the user to interact with
     */
    private static void help(String[] cmds)
    {
        //Display help for the commands
        for(String x : cmds)
        {
            switch(x)
            {
                case "help" -> System.out.println("help -> displays help for commands");
                case "add" -> System.out.println("add -> add an anime to the list (Name, Genre, # of Episodes)");
                case "remove" -> System.out.println("remove -> remove an anime via name from the list");
                case "exit" -> System.out.println("exit -> exit the program");
            }
        }
    }

    /**
     * Gets input & sanitizes input (from System.in stream)
     *
     * @return HasMap<String, ArrayList<String>>
     */
    public static HashMap<String, HashMap<ArrayList<String>, Integer>> getUserInput() {
        // get some type of input
        Scanner scan = new Scanner(System.in);
        // getting anime name
        System.out.println("What is the anime name?");
        String animeName = scan.nextLine().strip().toLowerCase();

        // bringing vars into scope`
        ArrayList<String> genresList = new ArrayList<>();
        int episodesWatched;

        // getting the genre corresponding to the anime
        System.out.println("Please enter the genres of this anime, ie, \"romance,action\" (exit to quit): ");
        String genresInput = scan.nextLine().strip().toLowerCase();
        String[] genres = genresInput.split(",");
        Collections.addAll(genresList, genres);

        // getting number of episodes watched
        System.out.println("How many episodes did you watch of " + animeName + "?");
        episodesWatched = Integer.parseInt(scan.nextLine());

        // creating nested HashMaps
        HashMap<String, HashMap<ArrayList<String>, Integer>> outerHash = new HashMap<>();
        HashMap<ArrayList<String>, Integer> innerHash = new HashMap<>();

        innerHash.put(genresList, episodesWatched);
        outerHash.put(animeName, innerHash);

        return outerHash;
    }
}
