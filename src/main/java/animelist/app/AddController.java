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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class AddController {
    private Library animeList;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> themes = new ArrayList<>();

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
    private ComboBox<String> cmbPickedGenre;

    @FXML
    private ComboBox<String> cmbPickedThemes;


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
            cmbAnime.getItems().addAll(animeList.getAnime());
        } else {
            cmbAnime.setVisible(false);
            txtParentAnime.setVisible(false);
        }
    }

    @FXML
    void addAnime(ActionEvent event) {
        String title = txtAnimeTitle.getText();
        int episodes;
        double rating;

        try{
            episodes = Integer.parseInt(txtAnimeEpisodes.getText());
            rating = Double.parseDouble(txtAnimeRating.getText());
        } catch (InputMismatchException e) {
            //TODO: Give error alert
            e.printStackTrace();
        }

        Anime.Season season = cmbSeason.getValue();
        Anime.Status status = cmbStatus.getValue();
    }

    @FXML
    void addGenre(ActionEvent event) {
        String genre = cmbGenres.getValue();

        if(!genres.contains(genre)) {
            genres.add(genre);
            cmbPickedGenre.getItems().clear();
            cmbPickedGenre.getItems().addAll(genres);
            cmbPickedGenre.getSelectionModel().selectFirst();
        } else {
            //TODO: Give error alert window
            System.out.println("Error!");
        }
    }

    @FXML
    void addTheme(ActionEvent event) {
        String theme = cmbThemes.getValue();

        if(!themes.contains(theme)) {
            themes.add(theme);
            cmbPickedThemes.getItems().clear();
            cmbPickedThemes.getItems().addAll(themes);
            cmbPickedThemes.getSelectionModel().selectFirst();;
        } else {
            //TODO: Give error alert window
            System.out.println("Error!");
        }

    }
}
