import org.junit.jupiter.api.*;

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
    HashMap<String, Integer> noAnimeHash;
    ArrayList<String> oneEntryAnimeList;
    HashMap<String, Integer> oneAnimeHash;
    ArrayList<String> multipleAnimeList;
    HashMap<String, Integer> multipleAnimeHash;
    @BeforeEach
    private void setupEmpty(){
        emptyAnimeList = new ArrayList<>(){};
        noAnimeHash = new HashMap<>();
    }
    @BeforeEach
    private void setupOneEntry(){
        oneEntryAnimeList = new ArrayList<>(){};
        oneEntryAnimeList.add("ATTACK ON TITAN");
        oneAnimeHash = new HashMap<>();
        oneAnimeHash.put("ATTACK ON TITAN", 12);
    }
    @BeforeEach
    private void setupMultiple(){
        multipleAnimeList = new ArrayList<>(){};
        multipleAnimeList.add("ATTACK ON TITAN");
        multipleAnimeList.add("DEMON SLAYER");
        multipleAnimeHash.put("ATTACK ON TITAN", 12);
        multipleAnimeHash.put("DEMON SLAYER", 12);
    }
    //Tests for topStreamedAnime function
    @Test
    public void noTopStreamedAnime() {
    }
}