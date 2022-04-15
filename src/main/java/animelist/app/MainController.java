package animelist.app;

import animelist.util.Library;
import animelist.util.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Manages GUI and handles its corresponding events
 * @version 1.0
 * @author Abhay Chopra, Brandon Greene
 * Date: 2022-04-08
 * TA: Amir (T06)
 */
public class MainController {

    private Library animeList;

    @FXML
    private Button addAnime;

    @FXML
    private ComboBox<String> animeComboBox;

    @FXML
    private Label animeInfo;

    @FXML
    private Label outputArea;


    /**
     * When the controller is started, boot with these params
     */
    @FXML
    public void initialize() {
        animeList = new Library();
        try{
            if(Main.getSysArgs().length > 0){
                animeList.addBulkAnime(Reader.Import(new File(Main.getSysArgs()[0])));
                updateAnimeInfo();
            }
        }catch (RuntimeException e){
            System.err.println("Invalid File!");
            System.err.println("Launching without file...");
        }
    }

    /**
     * Adds anime to library
     * @param ignoredEvent Event when add anime button clicked
     */
    @FXML
    void addNewAnime(ActionEvent ignoredEvent) {
        try {
            //Setting up adding data entry separate window
            addAnime.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Scene addScene = new Scene(loader.load(), 490, 370);
            Stage addStage = new Stage();
            //Managing controller for new window
            AddController controller = loader.getController();
            controller.setAnimeList(this.animeList);
            addStage.setTitle("Add An Anime");
            addStage.setResizable(false);
            addStage.setScene(addScene);
            addStage.showAndWait();
            addAnime.setDisable(false);
            //Updating Label containing info about anime
            updateAnimeInfo();
        } catch (IOException e) {
            e.printStackTrace();
            addAnime.setDisable(false);
        }
    }

    /**
     * Updates Label listing information about anime (using combo-box)
     */
    @FXML
    void updateAnimeText(ActionEvent ignoredEvent) {
        try{
            animeInfo.setText(animeList.getAnimeByName(animeComboBox.getValue()).toString());
            animeInfo.setFont(Font.font("Times", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
        }catch (NullPointerException e){
            animeInfo.setText("");
        }
    }

    /**
     * Deletes anime from library (using combo box from GUI)
     */
    @FXML
    void deleteAnime(ActionEvent ignoredEvent) {
        if(animeList.containsAnime(String.valueOf(animeComboBox.getValue())) && animeList.getAnime().length > 0){
            animeList.removeAnime(String.valueOf(animeComboBox.getValue()));
        }else{
            createAlert();
        }
        updateAnimeInfo();
    }

    /**
     * Action event that is handled when output button for all studio currently tracked
     */
    @FXML
    void getAllStudioTracked(ActionEvent ignoredEvent) {
        updateOutput(animeList.allStudiosTracked());
    }

    /**
     * Updates label corresponding to the output of buttons
     * @param printString String to show on GUI label
     */
    private void updateOutput(String printString) {
        if(animeList.getAnime().length > 0) {
            outputArea.setText(printString);
            outputArea.setFont(Font.font("Times", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
            outputArea.setMaxWidth(Double.MAX_VALUE);
            outputArea.setAlignment(Pos.CENTER);
        }
        else {
            createAlert();
        }
    }

    /**
     * Action event that is handled when output button for sorting of anime rating
     */
    @FXML
    void getSortedRating(ActionEvent ignoredEvent) {
        if(animeList.getAnime().length > 0) {
            updateOutput(animeList.getAnimeRatings());
        }else {
            createAlert();
        }
    }

    /**
     * Action event that is handled when output button for top streamed anime is clicked
     */
    @FXML
    void outputTopStreamedAnime(ActionEvent ignoredEvent) {
        if(animeList.getAnime().length > 0) {
        updateOutput(animeList.topStreamedAnime());
        }else{
            createAlert();
        }
    }

    /**
     * Action event that is handled when output button for top streamed genre is clicked
     */
    @FXML
    void topStreamedGenre(ActionEvent ignoredEvent) {
        if(animeList.getAnime().length > 0) {
        updateOutput(animeList.topStreamedGenre());
        }else{
            createAlert();
        }
    }

    /**
     * Action event that is handled when output button for total watch time is clicked
     */
    @FXML
    void totalWatchTime(ActionEvent ignoredEvent) {
        if(animeList.getAnime().length > 0) {
            updateOutput(animeList.totalWatchTime());
        }else{
            createAlert();
        }
    }

    /**
     * Creates an Error alert for no anime currently being stored
     */
    private void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Anime Not Found");
        alert.setContentText("No anime currently stored!");
        alert.show();
    }

    /**
     * Uses FileChooser to load a file and uses reader to read from file to add anime to anime library
     * @param ignoredEvent Event on load clicked
     */
    @FXML
    void loadFile(ActionEvent ignoredEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Anime File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(new Stage());
        //Handling non-null files, i.e. ignoring when no file chosen
        if (file != null) {
            try {
                animeList.addBulkAnime(Reader.Import(file));
                updateAnimeInfo();
            }catch (RuntimeException e){
                //Adding a confirmation to quit program
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Saving File");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    /**
     * Prints anime info to TextArea within GUI(i.e. updates the anime info)
     */
    private void updateAnimeInfo() {
        animeInfo.setFont(Font.font("Times", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
        animeInfo.setMaxWidth(Double.MAX_VALUE);
        animeInfo.setAlignment(Pos.CENTER);
        updateComboBox();
    }

    /**
     * Updates the combo-box containing anime
     */
    private void updateComboBox() {
        animeComboBox.getItems().removeAll(animeComboBox.getItems());
        animeComboBox.getItems().addAll(animeList.toString().split("\n"));
        if(animeList.toString().split("\n").length > 0 && !animeList.toString().split("\n")[0].equals("")){
            animeComboBox.setValue(animeList.getAnime()[0].getName());
        }
    }

    /**
     * Uses FileChooser to save to a file and uses writer to write to file using information from anime library
     * @param ignoredEvent Event on save clicked
     */
    @FXML
    void saveFile(ActionEvent ignoredEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Anime File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showSaveDialog(new Stage());
        //Handling non-null files, i.e. ignoring when no file chosen
        if (file != null) {
            try {
                Reader.save(animeList.getAnime(),file);
            }catch (RuntimeException e){
                //Adding a confirmation to quit program
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Saving File");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    /**
     * Handles quit button within menu items
     * @param ignoredEvent Event when close clicked
     */
    @FXML
    void closeProgram(ActionEvent ignoredEvent) {
        //Adding a confirmation to quit program
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * Presents info about program using alert
     * @param ignoredEvent Event when about menu item clicked
     */
    @FXML
    void programInfo(ActionEvent ignoredEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About this Application");
        alert.setContentText("Authors: Brandon Greene, Abhay Chopra\nVersion: v1.3\nTA: Amir (Tutorial 06)\nThis is a GUI for our anime list program!");
        alert.show();
    }

}