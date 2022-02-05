import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
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
                case "help" -> help();
                case "add" -> getUserInput();
                case "remove" -> remove();
                case "exit" -> notQuit = setQuit(scan);
                default -> printError();
            }
        }
        while(notQuit);

        getUserInput();
    }

    private static void printError()
    {
        System.out.println("Error reading command, please enter again!");
    }

    private static boolean setQuit(Scanner scan)
    {
        System.out.println("Are you sure you wish to quit? (Y, N)");
        return scan.nextLine().equals("Y");
    }

    private static void remove()
    {
    }

    private static void help()
    {
    }

    /**
     * Gets input & sanitizes input (from System.in stream)
     *
     * @return Some type of storage structure (Array, ArrayLists, HashMap)
     */
    public static HashMap<String, String> getUserInput() {
        // get some type of input
        Scanner scan = new Scanner(System.in);
        // getting anime name
        String animeName = scan.nextLine().strip();
        scan.close();
        ArrayList<String> genre = new ArrayList<>();
        boolean gettingGenre = true;
        // looping for genre names
        do{
            // getting the genre (multiple)
            System.out.println("Please enter the genre of this anime, ie, \"romance\": ");
            String genreName = scan.nextLine().strip();
            // turning off the sentinel
            if(genreName.equals("exit")) {gettingGenre = false;}
            else{genre.add(genreName);}

        }while(gettingGenre);
        HashMap<String, String> tracker = new HashMap<>();
        return tracker;
    }
}
