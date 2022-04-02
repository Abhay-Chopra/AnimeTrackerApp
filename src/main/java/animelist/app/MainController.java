package animelist.app;

import animelist.util.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MainController {
    private Library animeList;

    /**
     * When the controller is started, boot with these params
     */
    @FXML
    public void initialize() {
        animeList = new Library();
    }


    }

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

    @FXML
    void getAllStudioTracked(ActionEvent event) {

    }

    @FXML
    void getSortedRating(ActionEvent event) {

    }

    @FXML
    void outputTopStreamedAnime(ActionEvent event) {

    }

    @FXML
    void topStreamedGenre(ActionEvent event) {

    }

    @FXML
    void totalWatchTime(ActionEvent event) {

    }
}