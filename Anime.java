
import java.util.ArrayList;

public class Anime {
    //Fields and Enums
    private String name;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> themes = new ArrayList<>();
    private int episodes;
    private double rating;
    private Status status;
    private Season season;

    //Not changing and similar for all anime, public?
    public static final int EPISODE_LENGTH = 23;
    public static final String[] LIST_OF_GENRES = {"ACTION", "DRAMA", "COMEDY", "ADVENTURE", "SUPERNATURAL", "FANTASY"};
    public static final String[] LIST_OF_THEMES = {"SUPER POWER", "MARTIAL ARTS", "MECHA", "DEMONS", "MILITARY"};

    //Status and Season ENUMS
    public enum Status {
        WATCHING,
        FINISHED,
        DROPPED
    }

    public enum Season {
        WINTER,
        SPRING,
        SUMMER,
        FALL
    }

    //Constructor and Methods beyond here
    public Anime() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<String> themes) {
        this.themes = themes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

}
