import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Abhay Chopra, Brandon Greene
 * @version 1.0
 * Tutorial: T06
 */
class MainTests {
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
    private void setupEmpty() {
        emptyAnimeList = new ArrayList<>();
        noAnimeEpisodes = new HashMap<>();
        noAnimeGenres = new HashMap<>();
    }

    @BeforeEach
    private void setupOneEntry() {
        int episodes = 72;
        String anime = "ATTACK ON TITAN";
        ArrayList<String> genres = new ArrayList<>();
        genres.add("ACTION");
        genres.add("DRAMA");
        oneEntryAnimeList = new ArrayList<>() {
        };
        oneEntryAnimeList.add(anime);
        oneAnimeEpisodes = new HashMap<>();
        oneAnimeEpisodes.put(anime, episodes);
        oneAnimeGenre = new HashMap<>();
        oneAnimeGenre.put(anime, genres);
    }

    @BeforeEach
    private void setupMultiple() {
        String animeOne = "ATTACK ON TITAN";
        String animeTwo = "DEMON SLAYER";
        int attackOnTitanEpisodes = 72;
        int demonSlayerEpisodes = 44;
        ArrayList<String> oneGenre = new ArrayList<>();
        ArrayList<String> twoGenre = new ArrayList<>();
        oneGenre.add("ACTION");
        oneGenre.add("DRAMA");
        twoGenre.add("ACTION");
        twoGenre.add("HORROR");
        multipleAnimeList = new ArrayList<>() {
        };
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
    void noTopStreamedAnime() {
        assertEquals("No anime is currently being tracked", Main.topStreamedAnime(emptyAnimeList, noAnimeEpisodes));
    }

    @Test
    void oneStreamedAnime() {
        assertEquals("Your current top streamed anime is: ATTACK ON TITAN\nEstimated watch time: 1656 minutes",
                Main.topStreamedAnime(oneEntryAnimeList, oneAnimeEpisodes));
    }

    @Test
    void multipleStreamedAnime() {
        assertEquals("Your current top streamed anime is: ATTACK ON TITAN\nEstimated watch time: 1656 minutes",
                Main.topStreamedAnime(multipleAnimeList, multipleAnimeEpisodes));
    }

    //Test Case for when Demon Slayer has more episodes streamed
    @Test
    void topStreamedAnime() {
        //Changing number of episodes for one anime
        multipleAnimeEpisodes.put("DEMON SLAYER", 73);
        assertEquals("Your current top streamed anime is: DEMON SLAYER\nEstimated watch time: 1679 minutes",
                Main.topStreamedAnime(multipleAnimeList, multipleAnimeEpisodes));
    }

    //Tests for topStreamedGenre function
    @Test
    void noAnimeStreamed() {
        assertEquals("No anime is currently being tracked!", Main.topStreamedGenre(emptyAnimeList, noAnimeGenres, noAnimeEpisodes));
    }

    @Test
    void oneAnimeTracked() {
        assertEquals("Your top streamed anime genre is: DRAMA\nEstimated Watch Time: 1656 minutes",
                Main.topStreamedGenre(oneEntryAnimeList, oneAnimeGenre, oneAnimeEpisodes));
    }

    @Test
    void multipleAnimeTracked() {
        assertEquals("Your top streamed anime genre is: ACTION\nEstimated Watch Time: 2668 minutes",
                Main.topStreamedGenre(multipleAnimeList, multipleAnimeGenre, multipleAnimeEpisodes));
    }

    //Tests for totalWatchTime
    @Test
    void noWatchTime(){
        assertEquals("Total Watch Time (estimated): 0 minutes", Main.totalWatchTime(emptyAnimeList, noAnimeEpisodes));
    }

    @Test
    void oneAnimeWatchTime(){
        assertEquals("Total Watch Time (estimated): 1656 minutes", Main.totalWatchTime(oneEntryAnimeList, oneAnimeEpisodes));
    }

    @Test
    void multipleAnimeWatchTime(){
        assertEquals("Total Watch Time (estimated): 2668 minutes", Main.totalWatchTime(multipleAnimeList, multipleAnimeEpisodes));
    }

    //Tests for getGenreByAnime
    @Test
    void noGenresByAnime(){
        Scanner testScanner = new Scanner("attack on titan");
        assertEquals("That anime is not stored! Try again", Main.getGenreByAnime(testScanner, noAnimeGenres));
    }

    @Test
    void singleGenresByAnime(){
        Scanner testScanner = new Scanner("attack on titan");
        assertEquals("The anime ATTACK ON TITAN has genres [ACTION, DRAMA]", Main.getGenreByAnime(testScanner, oneAnimeGenre));
    }

    @Test
    void multipleGenresByAnime(){
        Scanner testScanner = new Scanner("demon slayer");
        assertEquals("The anime DEMON SLAYER has genres [ACTION, HORROR]", Main.getGenreByAnime(testScanner, multipleAnimeGenre));
    }
}