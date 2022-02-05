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
        Scanner scan = new Scanner(System.in);
        String[] cmds = {"help", "add", "remove", "exit"};
        boolean notQuit = true;

        System.out.println("Welcome, please input one of the following commands!");

        for(String x : cmds)
        {
            System.out.println(x);
        }

        do
        {
            String input = scan.nextLine();
            switch (input)
            {
                case "help" -> help();
                case "add" -> add();
                case "remove" -> remove();
                case "exit" -> notQuit = setQuit();
                default -> printError();
            }
        }
        while(notQuit);

        getUserInput();
    }

    private static void printError()
    {
    }

    private static boolean setQuit()
    {
        return true;
    }

    private static void remove()
    {
    }

    private static void add()
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
    public static int[] getUserInput() {
        // get some type of input
        Scanner scan = new Scanner(System.in);
        // put data into some sort of data structure (default array)
        int[] array = {scan.nextInt()};
        scan.close();
        // For Testing
        System.out.println(Arrays.toString(array));

        // return to main; create a data structure in main to share data between functions
        return array;
    }
}
