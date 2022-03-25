package anime.util;
import java.util.*;
import anime.Entity.Anime;
import anime.Entity.Studio;

/**
 * Allows for packaging together multiple anime, i.e. everything that is currently being tracked
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * TA: 06 (W/ Amir)
 * March 24th, 2022
 */
public class Library {
    //The only container in the class, a List of Anime
    private final ArrayList<Anime> animeList;
    private final ArrayList<Studio> studios;

    /**
     * Constructor for the Library class
     */
    public Library() {
        animeList = new ArrayList<>();
        studios = new ArrayList<>();
    }

    /**
     * Checks if tracked anime have some particular anime
     * @param animeName String name of Anime
     * @return boolean if stored Anime contain the particular anime
     */
    public boolean containsAnime(String animeName){
        for (Anime anime: animeList) {
            if (anime.getName().equals(animeName)){
                return true;
            }
        }
        return false;
    }

    //All of our anime accessing code and processing goes here

    /**
     * Adds an anime into tracked Anime
     * @param newAnime Anime to add
     */
    public void addAnime(Anime newAnime) {
        animeList.add(newAnime);
    }

    /**
     * Removes anime from tracking
     * @param animeToRemove Anime to remove
     */
    public void removeAnime(String animeToRemove) {
        animeList.removeIf(anime -> anime.getName().equals(animeToRemove));
    }

    /**
     * Adds studio to tracked studio list
     * @param newStudio Studio to Add
     */
    public void addStudio(Studio newStudio) {
        studios.add(newStudio);
    }

    public void removeStudio(String studioName) {
        studios.removeIf(studio -> studio.getName().equals(studioName));
    }

    /**
     * Print the contents of the map of anime -> rating (double)
     *
     * @return String containing all anime ratings'
     */
    public String getAnimeRatings() {
        if(animeList.isEmpty()){return "No anime currently being tracked!";}
        animeList.sort(new AnimeRatingComparator());
        StringBuilder returnString = new StringBuilder();
        for(Anime anime: animeList)
        {
            returnString.append(anime.getName()).append(" -> ").append(anime.getRating()).append("\n");
        }
        returnString.deleteCharAt(returnString.length()-1);
        return returnString.toString();
    }

    /**
     * Determines the top streamed anime based on total watch time of each anime by sorting
     * the list and returning the top three streamed shows
     *
     * @return String that containsAnime the top streamed anime from all stored anime
     */
    public String topStreamedAnime() {
        //Return if empty
        if(animeList.isEmpty())
            return "No anime being tracked!";

        //Sort the anime in descending order
        animeList.sort(new AnimeWatchTimeComparator().reversed());
        //String builder for easy string building
        StringBuilder retString = new StringBuilder();
        retString.append("Your Top Streamed Anime Are").append("\n");
        //Iterate over the anime in the top 3 spots and append to the builder
        for(int i = 0; i < 3; i++) {
            retString.append(animeList.get(i).getName()).append(" -> Watch Time: ").append(animeList.get(i).getEpisodes()*Anime.EPISODE_LENGTH).append(" minutes").append("\n");
        }

        return retString.toString().strip();
    }

    /**
     * Calculates the top streamed anime genre, from anime currently stored
     *
     * @return String that containsAnime all the genres of a particular anime
     */
    public String topStreamedGenre() {

        if(animeList.isEmpty())
            return "No currently tracked anime";

        HashMap<String, Integer> genresToEpisodes = new HashMap<>();

        // will get the genres and their respective watch times
        for(Anime a : this.animeList) {
            for(String s : a.getGenres()) {
                if(!genresToEpisodes.containsKey(s)) {
                    genresToEpisodes.put(s, a.getEpisodes()*Anime.EPISODE_LENGTH);
                } else {
                    genresToEpisodes.put(s, (genresToEpisodes.get(s) + (a.getEpisodes() * Anime.EPISODE_LENGTH)));
                }
            }
        }

        int highestWatchTime = 0;
        String bigGenre = "";
        //Going through the genres and getting the highest watch time
        for(String s : genresToEpisodes.keySet()) {
            if(genresToEpisodes.get(s) > highestWatchTime) {
                highestWatchTime = genresToEpisodes.get(s);
                bigGenre = s;
            }
        }

        return "The most watched genre is " + bigGenre + " with a total watch time of " + highestWatchTime;
    }

    /**
     * Calculates total watch time (given an estimated 23 mins per anime episode)
     *
     * @return String that containsAnime the total watch-time for user
     */
    public String totalWatchTime() {
        int totalEpisodes = 0;
        for (Anime anime : animeList) {
            totalEpisodes += anime.getEpisodes();
        }
        return "Total Watch Time (estimated): " + (totalEpisodes * Anime.EPISODE_LENGTH + " minutes");
    }

    /**
     * Get the genre of a supplied anime, given by user
     *
     * @return String that containsAnime all genre for a particular anime
     */
    public String getGenreByAnime(String searchedAnime) {
        if (this.containsAnime(searchedAnime)) {
            ArrayList<String> animeGenres = new ArrayList<>();
            for (Anime anime: animeList) {
                if (anime.getName().equals(searchedAnime)){
                    animeGenres = anime.getGenres();
                }
            }
            StringBuilder genres = new StringBuilder();
            for (String genre: animeGenres) {
                genres.append(genre).append(", ");
            }
            genres.deleteCharAt(genres.length()-2);
            return "The anime " + searchedAnime + " has genres " + genres;
        } else {
            return "That anime is not stored! Try again";
        }
    }

    /**
     * Build a string of the information of all anime tracked
     *
     * @return the built string of the anime currently tracked
     */
    public String allAnimeTracked() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Anime a : this.animeList) {
            stringBuilder.append("---------------\n").append(a.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Convert the ArrayList to a fixed array and send it off
     *
     * @return the fixed array of the animeList
     */
    public Anime[] getAnime() {
        Anime[] animeArray = new Anime[animeList.size()];
        animeArray = animeList.toArray(animeArray);
        return animeArray;
    }

    /**
     * Display a list of the studios currently in Library
     *
     * @return Array of Studios
     */
    public Studio[] getStudios() {
        Studio[] studioArray = new Studio[studios.size()];
        studioArray = studios.toArray(studioArray);
        return studioArray;
    }

    /**
     * Build a string of the information of all studios tracked
     *
     * @return the built string of the studios currently tracked
     */
    public String allStudiosTracked() {

        if(studios.size() == 0)
            return "No Studios Being Tracked!";

        StringBuilder stringBuilder = new StringBuilder();
        //Going through all studios tracked of current library
        for(Studio s : this.studios) {
            stringBuilder.append(s.toString()).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        return stringBuilder.toString();
    }

    /**
     * Add multiple anime at once
     *
     * @param givenAnimeList ArrayList containing additional anime
     */
    public void addBulkAnime(ArrayList<Anime> givenAnimeList) {
        for (Anime anime: givenAnimeList) {
            if (!this.containsAnime(anime.getName())) {
                animeList.add(anime);
                //Confirming that studios is not empty before looping
                if(!studios.contains(anime.getStudio())){
                    studios.add(anime.getStudio());
                }
            }
        }
    }
}
