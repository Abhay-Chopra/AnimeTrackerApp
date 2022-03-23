package anime;

import java.util.Arrays;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 2.0
 * Tutorial: T06 (w/ Amir)
 * Date: March 3rd 2022
 * Objective: Software that tracks the user's top streamed anime category
 */
public class Main {
    //TODO UML diagram
    public static void main(String[] args) {
        if(args.length > 1){
            System.err.println("Too Many Arguments from CMD!");
            System.err.printf("Args: %s%n", Arrays.toString(args));
            System.exit(1);
        }
        Menu mainMenu = new Menu(args);
        mainMenu.Start();
    }
}
