package animelist.app;

import animelist.entity.Anime;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddController {
    @FXML
    private ComboBox<String> cmbGenres;

    @FXML
    private ComboBox<Anime.Season> cmbSeason;

    @FXML
    private ComboBox<Anime.Status> cmbStatus;

    @FXML
    private ComboBox<String> cmbThemes;

    @FXML
    private TextField txtAnimeEpisodes;

    @FXML
    private TextField txtAnimeRating;

    @FXML
    private TextField txtAnimeTitle;


    @FXML
    public void initialize() {
        cmbSeason.getItems().addAll(Anime.Season.values());
        cmbSeason.getSelectionModel().selectFirst();
        cmbStatus.getItems().addAll(Anime.Status.values());
        cmbStatus.getSelectionModel().selectFirst();
        cmbGenres.getItems().addAll(Anime.LIST_OF_GENRES);
        cmbGenres.getSelectionModel().selectFirst();
        cmbThemes.getItems().addAll(Anime.LIST_OF_THEMES);
        cmbThemes.getSelectionModel().selectFirst();
    }
}
