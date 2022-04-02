package animelist.app;

import animelist.entity.Anime;
import animelist.util.Library;
import animelist.util.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private ComboBox<?> animeComboBox;

    @FXML
    private TextArea animeInfo;

    @FXML
    private Button deleteAnime;

    @FXML
    private TextArea outputArea;


    /**
     * When the controller is started, boot with these params
     */
    @FXML
    public void initialize() {
        animeList = new Library();
    }

    @FXML
    void addNewAnime(ActionEvent event) {
        System.out.println("Works!!!");
        try {
            addAnime.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Scene addScene = new Scene(loader.load(), 600, 400);
            Stage addStage = new Stage();
            AddController controller = loader.getController();
            controller.setAnimeList(this.animeList);
            addStage.setTitle("Add An Anime");
            addStage.setResizable(false);
            addStage.setScene(addScene);
            addStage.showAndWait();
            addAnime.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
            addAnime.setDisable(false);
        }
    }

    @FXML
    void deleteAnime(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void getAllStudioTracked(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void getSortedRating(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void outputTopStreamedAnime(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void topStreamedGenre(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void totalWatchTime(ActionEvent event) {
        System.out.println("Works!!!");
    }

    @FXML
    void changeAnimeInformation(ActionEvent event) {

    }

    /**
     * Uses FileChooser to load a file and uses reader to read from file to add anime to anime library
     * @param event Event on load clicked
     */
    @FXML
    void loadFile(ActionEvent event) {
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
                //Handle errors here
            }
        }
    }

    /**
     * Prints anime info to TextArea within GUI(i.e. updates the anime info)
     */
    private void updateAnimeInfo() {
        animeInfo.setText(animeList.allAnimeTracked());
        animeInfo.setFont(Font.font("Times", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
    }

    /**
     * Uses FileChooser to save to a file and uses writer to write to file using information from anime library
     * @param event Event on save clicked
     */
    @FXML
    void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Anime File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showSaveDialog(new Stage());
        //Handling non-null files, i.e. ignoring when no file chosen
        if (file != null) {
            try {
                Reader.save(animeList.getAnime(),file.getName());
            }catch (RuntimeException e){
                //Handle Exceptions here
            }
        }
    }

    /**
     * Handles quit button within menu items
     * @param event Event when close clicked
     */
    @FXML
    void closeProgram(ActionEvent event) {
        //Adding a confirmation to quit program
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

}