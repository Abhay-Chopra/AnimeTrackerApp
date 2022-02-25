import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

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
        oneAnimeGenre = new HashMap<>();
        oneAnimeGenre.put(anime, genres);
    }

    @BeforeEach
    private void setupMultiple(){
        String animeOne = "ATTACK ON TITAN";
        String animeTwo = "DEMON SLAYER";
        int attackOnTitanEpisodes = 72;
        int demonSlayerEpisodes = 44;
        ArrayList<String> oneGenre = new ArrayList<>();
        ArrayList<String> twoGenre = new ArrayList<>();
        oneGenre.add("action");
        oneGenre.add("drama");
        twoGenre.add("action");
        twoGenre.add("horror");
        multipleAnimeList = new ArrayList<>(){};
        multipleAnimeList.add(animeOne);
        multipleAnimeList.add(animeTwo);
        multipleAnimeEpisodes = new HashMap<>();
        multipleAnimeEpisodes.put(animeOne, attackOnTitanEpisodes);
        multipleAnimeEpisodes.put(animeTwo, demonSlayerEpisodes);
        multipleAnimeGenre = new HashMap<>();
        multipleAnimeGenre.put(animeOne, oneGenre);
        multipleAnimeGenre.put(animeTwo, twoGenre);
    }
    //Tests for topStreamedAnime function
    @Test
    public void noTopStreamedAnime()
    {
        assertEquals("No anime is currently being tracked", Main.topStreamedAnime(emptyAnimeList, noAnimeEpisodes));
    }
    @Test void oneStreamedAnime()
    {
        assertEquals("Your current top streamed anime is: ATTACK ON TITAN\nEstimated watch time: 1656 minutes",
                     Main.topStreamedAnime(oneEntryAnimeList, oneAnimeEpisodes));
    }
    @Test void multipleStreamedAnime()
    {
        assertEquals("Your current top streamed anime is: ATTACK ON TITAN\nEstimated watch time: 1656 minutes",
                     Main.topStreamedAnime(multipleAnimeList, multipleAnimeEpisodes));
    }
    //Test Case for when DEMON SLAYER HAS MORE EPISODES STREAMED
    @Test void topStreamedAnime(){
        //Changing number of episodes for one anime
        multipleAnimeEpisodes.put("DEMON SLAYER", 73);
        assertEquals("Your current top streamed anime is: DEMON SLAYER\nEstimated watch time: 1679 minutes",
                     Main.topStreamedAnime(multipleAnimeList, multipleAnimeEpisodes));
    }
}