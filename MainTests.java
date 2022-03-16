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
}