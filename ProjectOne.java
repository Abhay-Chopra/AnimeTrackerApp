import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06 (w/ Amir)
 * Objective: Software that tracks the user's top streamed anime category
 */
public class ProjectOne {
    public static void main(String[] args) {
        getUserInput();
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
