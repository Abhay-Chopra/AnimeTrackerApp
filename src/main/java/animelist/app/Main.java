package animelist.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main launches GUI and manages CMD args
 * @version 1.3
 * @author Abhay Chopra, Brandon Greene
 * Date: 2022-04-08
 * TA: Amir (T06)
 */
public class Main extends Application {

    private static String[] sysArgs;
    public static void main(String[] args) {
        sysArgs = args;
        launch();
    }

    /**
     * Getter for CMD args
     * @return List containing args
     */
    public static String[] getSysArgs() {
        return sysArgs;
    }


    /**
     * Launches GUI
     * @param stage Stage for GUI
     * @throws IOException general Exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 630);
        stage.setTitle("Abhay and Brandon's Anime List v1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}