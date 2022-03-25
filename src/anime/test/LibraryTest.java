package anime.test;

import anime.Entity.Anime;
import anime.util.Library;
import anime.util.Reader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Library.java
 * @author Abhay Chopra, Brandon Greene
 * @version 1.1
 * TA: Amir (T06)
 * March 24th, 2022
 */
class LibraryTest {

    /**
     * Test confirms that everything in test file is being correctly read and stored
     */
    @Test
    void ReaderImport() {
        File file = new File("test.txt");
        ArrayList<Anime> testArray = Reader.Import(file);
        assertFalse(testArray.isEmpty());
        assertEquals("ATTACK ON TITAN", testArray.get(0).getName());
        assertEquals("MONSTER", testArray.get(testArray.size()-1).getName());
    }

    private Library testLibrary;

    /**
     * Setting up testLibrary
     */
    private void fillingTestingLibrary() {
        testLibrary = new Library();
        testLibrary.addBulkAnime(Reader.Import(new File("test.txt")));
    }

    private void emptyTestLibrary() {
        testLibrary = new Library();
    }

    /**
     * Test case to get ratings for all anime stored
     */
    @Test
    void getAnimeRatings() {
        fillingTestingLibrary();
        String expected =
                """
                    CODE GEASSE -> 8.7
                    MONSTER -> 8.82
                    GINTAMA -> 8.95
                    ATTACK ON TITAN -> 9.4
                    DEMON SLAYER -> 10.0
                    FULL METAL ALCHEMIST: BROTHERHOOD -> 10.0""";
        assertEquals(expected, testLibrary.getAnimeRatings());
    }

    /**
     * Test case to get top streamed anime from test file
     */
    @Test
    void topStreamedAnime() {
        fillingTestingLibrary();
        String expected = """
                Your Top Streamed Anime Are
                GINTAMA -> Watch Time: 2300 minutes
                FULL METAL ALCHEMIST: BROTHERHOOD -> Watch Time: 1472 minutes
                DEMON SLAYER -> Watch Time: 598 minutes""";
        assertEquals(expected, testLibrary.topStreamedAnime());
    }

    /**
     * Test case for total watch time given some stored anime
     */
    @Test
    void topStreamedGenre() {
        fillingTestingLibrary();
        assertEquals("The most watched genre is ACTION with a total watch time of 5520", testLibrary.topStreamedGenre());
    }

    /**
     * Test case follows last case, but removes some anime from tracked anime, i.e. watch time should change
     */
    @Test
    void topStreamedGenreAfterRemove(){
        fillingTestingLibrary();
        testLibrary.removeAnime("ATTACK ON TITAN");
        assertEquals("The most watched genre is ACTION with a total watch time of 4945", testLibrary.topStreamedGenre());
    }

    /**
     * Test case when trying to access topStreamedGenre, but no anime being tracked
     */
    @Test
    void emptyTopStreamedGenre() {
        emptyTestLibrary();
        assertEquals("No currently tracked anime", testLibrary.topStreamedGenre());
    }

    /**
     * Test case when preloaded some file, i.e. some watch time should exist
     */
    @Test
    void totalWatchTime() {
        fillingTestingLibrary();
        assertEquals("Total Watch Time (estimated): 5750 minutes", testLibrary.totalWatchTime());
    }

    /**
     * Test case for when no anime tracked
     */
    @Test
    void emptyWatchTime() {
        emptyTestLibrary();
        assertEquals("Total Watch Time (estimated): 0 minutes", testLibrary.totalWatchTime());
    }

    /**
     * Getting genres for the Anime titled "Attack on Titan"
     */
    @Test
    void getGenreByAnime() {
        fillingTestingLibrary();
        String animeToTest = testLibrary.getAnime()[0].getName();
        assertEquals("The anime ATTACK ON TITAN has genres ACTION, DRAMA ", testLibrary.getGenreByAnime(animeToTest));
    }

    /**
     * Getting genres for the Anime titled "Demon Slayer"
     */
    @Test
    void getGenreForDemonSlayer(){
        fillingTestingLibrary();
        String animeToTest = testLibrary.getAnime()[1].getName();
        assertEquals("The anime DEMON SLAYER has genres ACTION, SUPERNATURAL ",testLibrary.getGenreByAnime(animeToTest));
    }

    /**
     * Getting genres for the Anime titled "Full Metal Alchemist"
     */
    @Test
    void getGenreForFullMental(){
        fillingTestingLibrary();
        String animeToTest = testLibrary.getAnime()[2].getName();
        assertEquals("The anime FULL METAL ALCHEMIST: BROTHERHOOD has genres ACTION, DRAMA, FANTASY, ADVENTURE, COMEDY ",testLibrary.getGenreByAnime(animeToTest));
    }

    /**
     * Getting genres for the Anime titled "Code Geasse"
     */
    @Test
    void getGenreForCodeGeasse(){
        fillingTestingLibrary();
        String animeToTest = testLibrary.getAnime()[3].getName();
        assertEquals("The anime CODE GEASSE has genres ACTION, DRAMA, SCIFI ",testLibrary.getGenreByAnime(animeToTest));
    }
}