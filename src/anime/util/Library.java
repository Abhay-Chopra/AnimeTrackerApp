package anime.util;
import java.util.*;
import anime.Entity.Anime;

public class Library {
    //The only container in the class, a List of Anime
    private ArrayList<Anime> animeList;

    public Library() {
        animeList = new ArrayList<Anime>();
    }

    //All of our anime accessing code and processing goes here
    public void addAnime(Anime newAnime) {
        animeList.add(newAnime);
    }

    public void removeAnime(Anime removeAnime) {
        animeList.remove(removeAnime);
    }

    /**
     * Display a list of the studios currently in tracking
     * @param studios the list of studios
     * @return a string build from a string builder
     */
    public String getStudios(ArrayList<String> studios)
    {
        if(studios.isEmpty()){return "No anime currently being tracked!";}
        StringBuilder retString = new StringBuilder();
        for(String studio : studios){
            retString.append(studio).append("\n");
        }
        retString.deleteCharAt(retString.length()-1);
        return retString.toString();
    }

    /**
     * Print the contents of the map of anime -> rating (double_
     * @param animeRating the map of anime to rating
     * @return a toString from a StringBuilder
     */
    public String getAnimeRatings(HashMap<String, Double> animeRating)
    {
        if(animeRating.isEmpty()){return "No anime currently being tracked!";}
        StringBuilder retString = new StringBuilder();
        for(String key : animeRating.keySet())
        {
            retString.append(key).append(" -> ").append(animeRating.get(key)).append("\n");
        }
        retString.deleteCharAt(retString.length()-1);
        return retString.toString();
    }

    /**
     * Determines the top streamed anime based on total watch time of each anime
     *
     * @param animeList         Arraylist containing all anime being tracked
     * @param animeEpisodeCount the linked anime/episodeCount Hashmap
     * @return String that contains the top streamed anime from all stored anime
     */
    public String topStreamedAnime(ArrayList<String> animeList, HashMap<String, Integer> animeEpisodeCount) {
        String output;
        if (animeList.size() > 0) {
            String topStreamedAnime = animeList.get(0);
            Integer episodeCount = -1;
            for (String anime : animeList) {
                if (animeEpisodeCount.get(anime) > episodeCount) {
                    topStreamedAnime = anime;
                    episodeCount = animeEpisodeCount.get(anime);
                }
            }
            output = "Your current top streamed anime is: " + topStreamedAnime + "\nEstimated watch time: " + episodeCount * Anime.EPISODE_LENGTH + " minutes";
        } else {
            output = "No anime is currently being tracked";
        }
        return output;
    }

    /**
     * Calculates the top streamed anime genre, from anime currently stored
     * @param animeList         Arraylist containing all anime being tracked
     * @param animeGenre        the linked anime/genre Hashmap
     * @param animeEpisodeCount the linked anime/episodeCount Hashmap
     * @return String that contains all the genres of a particular anime
     */
    public String topStreamedGenre(ArrayList<String> animeList, HashMap<String, ArrayList<String>> animeGenre,
                                   HashMap<String, Integer> animeEpisodeCount) {
        String output;
        // creating intermediate data structure
        HashMap<String, Integer> genreToEpisodeMapping = new HashMap<>();
        if (animeList.size() > 0) {
            for (String anime : animeList) {
                ArrayList<String> genreList = animeGenre.get(anime);
                for (String genre : genreList) {
                    // if  key already exists, its being updated
                    if (genreToEpisodeMapping.containsKey(genre)) {
                        genreToEpisodeMapping.put(genre, genreToEpisodeMapping.get(genre) + animeEpisodeCount.get(anime));
                    }
                    // creating a new key and item pair within hashmap
                    else {
                        genreToEpisodeMapping.put(genre, animeEpisodeCount.get(anime));
                    }
                }
            }
            Set<String> genreKeySet = genreToEpisodeMapping.keySet();
            ArrayList<String> genresList = new ArrayList<>(genreKeySet);
            String topStreamedGenre = genresList.get(0);
            int numberOfEpisodes = 0;
            //looping through all genres
            for (String genre : genresList) {
                if (genreToEpisodeMapping.get(genre) > numberOfEpisodes) {
                    topStreamedGenre = genre;
                    numberOfEpisodes = genreToEpisodeMapping.get(genre);
                }
            }
            //printing out to console
            output = "Your top streamed anime genre is: " + topStreamedGenre + "\nEstimated Watch Time: " + numberOfEpisodes * Anime.EPISODE_LENGTH + " minutes";
        } else {
            output = "No anime is currently being tracked!";
        }
        return output;
    }


}
