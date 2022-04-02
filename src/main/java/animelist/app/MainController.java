package animelist.app;

import animelist.entity.Anime;
import animelist.util.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
}