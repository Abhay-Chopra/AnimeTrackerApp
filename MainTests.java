import org.junit.jupiter.api.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06
 */
class MainTests
{
    ArrayList<String> emptyAnimeList;
    HashMap<String, Integer> noAnimeEpisodes;
    HashMap<String, ArrayList<String>> noAnimeGenres;

    ArrayList<String> oneEntryAnimeList;
    HashMap<String, Integer> oneAnimeEpisodes;
    HashMap<String, ArrayList<String>> oneAnimeGenre;

    ArrayList<String> multipleAnimeList;
    HashMap<String, Integer> multipleAnimeEpisodes;
    HashMap<String, ArrayList<String>> multipleAnimeGenre;


    @BeforeEach
    private void setupEmpty(){
        emptyAnimeList = new ArrayList<>(){};
        noAnimeEpisodes = new HashMap<>();
        noAnimeGenres = new HashMap<>();
    }

    @BeforeEach
    private void setupOneEntry(){
        int episodes = 72;
        String anime = "ATTACK ON TITAN";
        ArrayList<String> genres = new ArrayList<>();
        genres.add("action");
        genres.add("drama");
        oneEntryAnimeList = new ArrayList<>(){};
        oneEntryAnimeList.add(anime);
        oneAnimeEpisodes = new HashMap<>();
        oneAnimeEpisodes.put(anime, episodes);
        oneAnimeGenre.put(anime, genres);
    }

    @BeforeEach
    private void setupMultiple(){
        String animeOne = "ATTACK ON TITAN";
        String animeTwo = "DEMON SLAYER";
        int oneEpisodes = 72;
        int twoEpisodes = 44;
        ArrayList<String> oneGenre = new ArrayList<>();
        ArrayList<String> twoGenre = new ArrayList<>();
        oneGenre.add("action");
        oneGenre.add("drama");
        twoGenre.add("action");
        twoGenre.add("horror");
        multipleAnimeList = new ArrayList<>(){};
        multipleAnimeList.add(animeOne);
        multipleAnimeList.add(animeTwo);
        multipleAnimeEpisodes.put(animeOne, oneEpisodes);
        multipleAnimeEpisodes.put(animeTwo, twoEpisodes);
        multipleAnimeGenre.put(animeOne, oneGenre);
        multipleAnimeGenre.put(animeTwo, twoGenre);
    }
    //Tests for topStreamedAnime function
    @Test
    public void noTopStreamedAnime() {
    }
}