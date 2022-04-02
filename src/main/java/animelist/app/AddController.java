package animelist.app;

import animelist.entity.Anime;
import animelist.entity.Studio;
import animelist.util.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddController {
    private Library animeList;

    @FXML
    private CheckBox chkAltAnime;

    @FXML
    private ComboBox<Anime> cmbAnime;

    @FXML
    private ComboBox<String> cmbGenres;

    @FXML
    private ComboBox<Anime.Season> cmbSeason;

    @FXML
    private ComboBox<Anime.Status> cmbStatus;

    @FXML
    private ComboBox<String> cmbThemes;

    @FXML
    private ComboBox<Studio> cmbStudio;

    @FXML
    private TextField txtStudio;

    @FXML
    private TextField txtAnimeEpisodes;

    @FXML
    private TextField txtAnimeRating;

    @FXML
    private TextField txtAnimeTitle;

    @FXML
    private Text txtParentAnime;


    @FXML
    public void initialize() {
        Studio defaultStudio = new Studio("None");
        cmbStudio.getItems().add(defaultStudio);
        cmbStudio.getSelectionModel().selectFirst();
        cmbSeason.getItems().addAll(Anime.Season.values());
        cmbSeason.getSelectionModel().selectFirst();
        cmbStatus.getItems().addAll(Anime.Status.values());
        cmbStatus.getSelectionModel().selectFirst();
        cmbGenres.getItems().addAll(Anime.LIST_OF_GENRES);
        cmbGenres.getSelectionModel().selectFirst();
        cmbThemes.getItems().addAll(Anime.LIST_OF_THEMES);
        cmbThemes.getSelectionModel().selectFirst();
        cmbAnime.setVisible(false);
        txtStudio.setVisible(false);
        txtParentAnime.setVisible(false);
        chkAltAnime.setVisible(false);
    }

    public void setAnimeList(Library list) {
        this.animeList = list;
        if(animeList.getStudios().length > 0) {
            cmbStudio.getItems().setAll(animeList.getStudios());
        }
        if(animeList.getAnime().length > 0) {
            chkAltAnime.setVisible(true);
        }
    }


    @FXML
    void cmbChangeStudio(ActionEvent event) {
        if(cmbStudio.getValue().toString().equals("None")) {
            txtStudio.setVisible(true);
        } else {
            txtStudio.setVisible(false);
        }
    }

    @FXML
    void displayParentAnime(ActionEvent event) {
        if(chkAltAnime.isSelected()) {
            txtParentAnime.setVisible(true);
            cmbAnime.setVisible(true);
        } else {
            cmbAnime.setVisible(false);
            txtParentAnime.setVisible(false);
        }
    }
}
