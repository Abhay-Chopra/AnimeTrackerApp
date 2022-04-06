package animelist.app;

import animelist.util.Library;
import animelist.util.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class MainController {

    private Library animeList;

    @FXML
    private Button addAnime;

    @FXML
    private ComboBox<String> animeComboBox;

    @FXML
    private TextArea animeInfo;

    @FXML
    private Label outputArea;


    /**
     * When the controller is started, boot with these params
     */
    @FXML
    public void initialize() {
        animeList = new Library();
    }

    @FXML
    void addNewAnime(ActionEvent ignoredEvent) {
        System.out.println("Works!!!");
        try {
            addAnime.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Scene addScene = new Scene(loader.load(), 490, 370);
            Stage addStage = new Stage();
            AddController controller = loader.getController();
            controller.setAnimeList(this.animeList);
            addStage.setTitle("Add An Anime");
            addStage.setResizable(false);
            addStage.setScene(addScene);
            addStage.showAndWait();
            addAnime.setDisable(false);
            updateAnimeInfo();
        } catch (IOException e) {
            e.printStackTrace();
            addAnime.setDisable(false);
        }
    }

    @FXML
    void deleteAnime(ActionEvent ignoredEvent) {
        if(animeList.containsAnime(String.valueOf(animeComboBox.getValue()))){
            animeList.removeAnime(String.valueOf(animeComboBox.getValue()));
        }
        updateAnimeInfo();
    }

    @FXML
    void getAllStudioTracked(ActionEvent ignoredEvent) {
        updateOutput(animeList.allStudiosTracked());
    }

    private void updateOutput(String printString) {
        outputArea.setText(printString);
    }

    @FXML
    void getSortedRating(ActionEvent ignoredEvent) {
        updateOutput(animeList.getAnimeRatings());
    }

    @FXML
    void outputTopStreamedAnime(ActionEvent ignoredEvent) {
        updateOutput(animeList.topStreamedAnime());
    }

    @FXML
    void topStreamedGenre(ActionEvent ignoredEvent) {
        updateOutput(animeList.topStreamedGenre());
    }

    @FXML
    void totalWatchTime(ActionEvent ignoredEvent) {
        updateOutput(animeList.totalWatchTime());
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning about Saving File");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    /**
     * Prints anime info to TextArea within GUI(i.e. updates the anime info)
     */
    private void updateAnimeInfo() {
        updateComboBox();
        animeInfo.setFont(Font.font("Times", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
    }

    private void updateComboBox() {
        animeComboBox.getItems().addAll(animeList.toString().split("\n"));
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
                Reader.save(animeList.getAnime(),file.getName());
            }catch (RuntimeException e){
                //Adding a confirmation to quit program
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning about Saving File");
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

    @FXML
    void programInfo(ActionEvent ignoredEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About this Application");
        alert.setContentText("Authors: Brandon Greene, Abhay Chopra\nVersion: v1.3\nTA: Amir (Tutorial 06)\nThis is a GUI for our anime list program!");
        alert.show();
    }

}