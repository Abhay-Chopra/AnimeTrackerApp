package animelist.app;

import animelist.entity.Anime;
import animelist.entity.SeasonAnime;
import animelist.entity.Studio;
import animelist.util.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;

public class AddController {
    private Library animeList;
    private final ArrayList<String> genres = new ArrayList<>();
    private final ArrayList<String> themes = new ArrayList<>();

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
        txtStudio.setVisible(true);
        txtParentAnime.setVisible(false);
        chkAltAnime.setVisible(false);

    }

    public void setAnimeList(Library list) {

        this.animeList = list;
        if(animeList.getStudios().length > 0) {
            cmbStudio.getItems().addAll(animeList.getStudios());
        }
        if(animeList.getAnime().length > 0) {
            chkAltAnime.setVisible(true);
        }

    }


    @FXML
    void cmbChangeStudio(ActionEvent event) {

        txtStudio.setVisible(cmbStudio.getValue().toString().equals("None"));

    }

    @FXML
    void displayParentAnime(ActionEvent ignoredEvent) {

        if(chkAltAnime.isSelected()) {
            txtParentAnime.setVisible(true);
            cmbAnime.setVisible(true);
            cmbAnime.getItems().addAll(animeList.getAnime());
            cmbAnime.setConverter(new StringConverter<>() {
                @Override
                public String toString(Anime anime) {
                    return anime.getName();
                }

                @Override
                public Anime fromString(String s) {
                    return cmbAnime.getValue();
                }
            });
            cmbAnime.getSelectionModel().selectFirst();
        } else {
            cmbAnime.setVisible(false);
            txtParentAnime.setVisible(false);
        }

    }

    @FXML
    void addAnime(ActionEvent ignoredEvent) {
        //Setup base variables for anime
        String title = txtAnimeTitle.getText().toUpperCase().trim();
        int episodes;
        double rating;

        //Check if title is blank
        if(!title.equals("")) {

            //Check if an anime with that title already exists
            if (!animeList.containsAnime(title)) {

                //Try catch the episodes and ratings
                try {
                    episodes = Math.round(Float.parseFloat(txtAnimeEpisodes.getText()));
                    rating = Double.parseDouble(txtAnimeRating.getText());

                    if(episodes > 0) {

                        if (rating > 0 && rating < 10) {

                            //Get the season and status from the boxes
                            Anime.Season season = cmbSeason.getValue();
                            Anime.Status status = cmbStatus.getValue();

                            //If either genre or themes boxes are empty, none were selected, error out
                            if (!genres.isEmpty() && !themes.isEmpty()) {

                                //If the combobox is "None" Studio, use the input box to create a new one, use combobox if one is selected
                                if (cmbStudio.getValue().toString().equals("None")) {
                                    //If the combo-box is "None" Studio, use the input box to create a new one, use combo-box if one is selected
                                    if (cmbStudio.getValue().toString().equals("None")) {

                                        //Store the name
                                        String studioName = txtStudio.getText().toUpperCase().trim();

                                        //Check of the name is blank and error out if is
                                        if (!txtStudio.getText().equals("")) {

                                            //Create the required objects and store them
                                            Studio newStudio = new Studio(studioName);

                                            //Check if were creating a sub anime
                                            if (chkAltAnime.isSelected()) {

                                                cmbAnime.getSelectionModel().selectFirst();
                                                Anime parentAnime = cmbAnime.getValue();
                                                SeasonAnime sAnime = new SeasonAnime(parentAnime, title, genres, themes, episodes, rating, status, season, newStudio);
                                                animeList.addAnime(sAnime);
                                                animeList.addStudio(newStudio);

                                            } else {

                                                Anime newAnime = new Anime(title, genres, themes, episodes, rating, status, season, newStudio);
                                                newStudio.addAnime(newAnime);
                                                animeList.addAnime(newAnime);
                                                animeList.addStudio(newStudio);

                                            }

                                        } else {
                                            createAlertWindow("Error, blank studio!");
                                        }

                                    } else if (cmbStudio.getSelectionModel().getSelectedItem() != null) {

                                        Studio studio = cmbStudio.getValue();

                                        //Check if were creating a sub anime
                                        if (chkAltAnime.isSelected()) {

                                            cmbAnime.getSelectionModel().selectFirst();
                                            Anime parentAnime = cmbAnime.getValue();
                                            SeasonAnime sAnime = new SeasonAnime(parentAnime, title, genres, themes, episodes, rating, status, season, studio);
                                            studio.addAnime(sAnime);
                                            animeList.addAnime(sAnime);

                                        } else {

                                            Anime newAnime = new Anime(title, genres, themes, episodes, rating, status, season, studio);
                                            studio.addAnime(newAnime);
                                            animeList.addAnime(newAnime);

                                        }

                                    } else {
                                        createAlertWindow("Error on studio!");
                                    }
                                } else {
                                    createAlertWindow("Themes/Genres error!");
                                }
                            } else {
                                createAlertWindow("Rating must be between 0-10");
                            }
                        } else {
                            createAlertWindow("Cant have negative episodes!");
                        }
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    createAlertWindow("Ratings/Episodes error!");
                }
            } else {
                createAlertWindow("Duplicate anime!");
            }
        } else {
            createAlertWindow("Title should not be blank!");
        }
    }


    @FXML
    void createAlertWindow(String theAlert) {
        Alert newAlert = new Alert(Alert.AlertType.ERROR);
        newAlert.setHeaderText(theAlert);
        newAlert.setTitle("Error Adding Anime");
        newAlert.showAndWait();
    }

    @FXML
    void addGenre(ActionEvent ignoredEvent) {
        String genre = cmbGenres.getValue();

        if(!genres.contains(genre)) {
            genres.add(genre);
            cmbPickedGenre.getItems().clear();
            cmbPickedGenre.getItems().addAll(genres);
            cmbPickedGenre.getSelectionModel().selectFirst();
        } else {
            createAlertWindow("Genre Already Added!");
        }
    }

    @FXML
    void addTheme(ActionEvent ignoredEvent) {
        String theme = cmbThemes.getValue();

        if(!themes.contains(theme)) {
            themes.add(theme);
            cmbPickedThemes.getItems().clear();
            cmbPickedThemes.getItems().addAll(themes);
            cmbPickedThemes.getSelectionModel().selectFirst();
        } else {
            createAlertWindow("Theme Already Added!");
        }

    }
}
