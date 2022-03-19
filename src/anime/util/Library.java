package anime.util;
import java.util.*;
import anime.Entity.Anime;
import anime.Entity.Studio;

public class Library {
    //The only container in the class, a List of Anime
    private ArrayList<Anime> animeList;
    private ArrayList<Studio> studios;

    public Library() {
        animeList = new ArrayList<>();
        studios = new ArrayList<>();
    }

    public boolean containsAnime(String animeName){
        for (Anime anime: animeList) {
            if (anime.getName().equals(animeName)){
                return true;
            }
        }
        return false;
    }

    //All of our anime accessing code and processing goes here
    public void addAnime(Anime newAnime) {
        animeList.add(newAnime);
    }

    public void removeAnime(String animeToRemove) {
        animeList.removeIf(anime -> anime.getName().equals(animeToRemove));
    }

    public void addStudio(Studio newStudio) {
        studios.add(newStudio);
    }

    public void removeStudio(String studioName) {
        studios.removeIf(studio -> studio.getName().equals(studioName));
    }

    /**
     * Print the contents of the map of anime -> rating (double)
     * @return String containing all anime ratings'
     */
    public String getAnimeRatings() {
        if(animeList.isEmpty()){return "No anime currently being tracked!";}
        StringBuilder returnString = new StringBuilder();
        for(Anime anime: animeList)
        {
            returnString.append(anime.getName()).append(" -> ").append(anime.getRating()).append("\n");
        }
        returnString.deleteCharAt(returnString.length()-1);
        return returnString.toString();
    }

    /**
     * Determines the top streamed anime based on total watch time of each anime
     *
     * @return String that containsAnime the top streamed anime from all stored anime
     */
    public String topStreamedAnime() {
        String output;
        if (animeList.size() > 0) {
            Anime topStreamedAnime = null;
            output = "Your current top streamed anime is: " + topStreamedAnime + "\nEstimated watch time: " + topStreamedAnime.getEpisodes() * Anime.EPISODE_LENGTH + " minutes";
        } else {
            output = "No anime is currently being tracked";
        }
        return output;
    }

    /**
     * Calculates the top streamed anime genre, from anime currently stored
     *
     * @return String that containsAnime all the genres of a particular anime
     */
    public String topStreamedGenre() {
        String output;
        if (animeList.size() > 0) {
            for (Anime anime : animeList) {
            }
            String topStreamedGenre = "filler";
            int numberOfEpisodes = 1;

            //printing out to console
            output = "Your top streamed anime genre is: " + topStreamedGenre + "\nEstimated Watch Time: " + numberOfEpisodes * Anime.EPISODE_LENGTH + " minutes";
        } else {
            output = "No anime is currently being tracked!";
        }
        return output;
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

    public String allAnimeTracked() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Anime a : this.animeList) {
            stringBuilder.append("---------------\n").append(a.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Convert the ArrayList to a fixed array and send it off
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
     * @return String containing all studios'
     */
    public Studio[] getStudios() {
        Studio[] studioArray = new Studio[studios.size()];
        studioArray = studios.toArray(studioArray);
        return studioArray;
    }

    public String allStudiosTracked() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Studio s : this.studios) {
            stringBuilder.append("---------------\n").append(s.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Add multiple anime at once
     * @param givenAnimeList ArrayList containing additional anime
     */
    public void addBulkAnime(ArrayList<Anime> givenAnimeList) {
        animeList.addAll(givenAnimeList);
    }
}
