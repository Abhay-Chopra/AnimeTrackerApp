import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Arrays;
import java.util.Scanner;

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
        boolean notQuit = true;
        String[] cmds = {"help", "add", "remove", "exit"};

        //Print out the list of available commands
        System.out.println("Welcome, please input one of the following commands!");
        for(String x : cmds)
        {
            System.out.println(x);
        }

        //Make a scanner to get user input
        Scanner scan = new Scanner(System.in);
        do
        {
            String input = scan.nextLine();
            switch (input)
            {
                case "help" -> help(cmds);
                case "add" -> getUserInput();
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
    public static HashMap<String, ArrayList<String>> getUserInput() {
        // get some type of input
        Scanner scan = new Scanner(System.in);
        // getting anime name
        String animeName = scan.nextLine().strip().toLowerCase();
        ArrayList<String> genre = new ArrayList<>();
        boolean gettingGenre = true;
        // looping for genre names
        do{
            // getting the genre corresponding to the anime
            System.out.println("Please enter the genre of this anime, ie, \"romance\": ");
            String genreName = scan.nextLine().strip().toLowerCase();
            // conditional for turning off the sentinel
            if(genreName.equals("exit")) {gettingGenre = false;}
            else{genre.add(genreName);}

        }while(gettingGenre);
        HashMap<String, ArrayList<String>> tracker = new HashMap<>();
        tracker.put(animeName, genre);
        return tracker;
    }
}
