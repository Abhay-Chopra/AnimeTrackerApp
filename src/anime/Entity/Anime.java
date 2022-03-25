package anime.Entity;
import java.util.ArrayList;

/**
 * Anime Class that has various fields and methods related to streaming
 */
public class Anime {
    private String name;
    private ArrayList<String> genres;
    private ArrayList<String> themes;
    private int episodes;
    private double rating;
    private Status status;
    private Season season;
    private Studio studio;

    //Not changing and similar for all anime
    public static final int EPISODE_LENGTH = 23;
    public static final String[] LIST_OF_GENRES = {"ACTION", "DRAMA", "COMEDY", "ADVENTURE", "SUPERNATURAL", "FANTASY"};
    public static final String[] LIST_OF_THEMES = {"SUPERPOWER", "MARTIAL ARTS", "MECHA", "DEMONS", "MILITARY"};

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

    /**
     * Constructor for Anime
     * @param name Anime name
     * @param genres List of genres of Anime
     * @param themes List containing themes of Anime
     * @param episodes Episode watch count of Anime
     * @param rating Rating of Anime
     * @param status Watch Status of Anime
     * @param season the Season type of Anime
     * @param animeStudio Studio that produced the Anime
     */
    public Anime(String name, ArrayList<String> genres, ArrayList<String> themes,
                 int episodes, double rating, Status status, Season season, Studio animeStudio) {
        this.name = name;
        this.genres = genres;
        this.themes = themes;
        this.episodes = episodes;
        this.rating = rating;
        this.status = status;
        this.season = season;
        this.studio = animeStudio;
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

    public Studio getStudio() {
        return this.studio;
    }

    public void setStudio(Studio studio){
        this.studio = studio;
    }

    /**
     * Overwrites the toString of Object
     * @return string representation of Anime objects
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Genres: " + this.genres.toString() + "\n" + "Themes: " + this.themes.toString() + "\n" + "Rating: " + this.rating + "\n" +
                "Episodes: " + this.episodes + "\n" + "Status: " + this.status.toString() + "\n" + "Season: " +this.season.toString()
                + "\n" + "Studio: " + this.studio.getName();
    }
}
