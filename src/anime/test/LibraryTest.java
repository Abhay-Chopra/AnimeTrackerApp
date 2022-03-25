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
 */
class LibraryTest {

    @Test
    void ReaderImport() {
        File file = new File("test.txt");
        ArrayList<Anime> testArray = Reader.Import(file);
        assertFalse(testArray.isEmpty());
        assertEquals("ATTACK ON TITAN", testArray.get(0).getName());
        assertEquals("MONSTER", testArray.get(testArray.size()-1).getName());
    }

    private Library testLibrary;

    private void fillingTestingLibrary() {
        testLibrary = new Library();
        testLibrary.addBulkAnime(Reader.Import(new File("test.txt")));
    }

    private void emptyTestLibrary() {
        testLibrary = new Library();
    }

    @Test
    void getAnimeRatings() {
        fillingTestingLibrary();
        String test =
                """
                        CODE GEASSE -> 8.7
                        MONSTER -> 8.82
                        GINTAMA -> 8.95
                        ATTACK ON TITAN -> 9.4
                        DEMON SLAYER -> 10.0
                        FULL METAL ALCHEMIST: BROTHERHOOD -> 10.0""";
        assertEquals(test, testLibrary.getAnimeRatings());
    }

    @Test
    void topStreamedAnime() {
        fillingTestingLibrary();
        String test = """
                Your Top Streamed Anime Are
                GINTAMA -> Watch Time: 2300 minutes
                FULL METAL ALCHEMIST: BROTHERHOOD -> Watch Time: 1472 minutes
                DEMON SLAYER -> Watch Time: 598 minutes""";
        assertEquals(test, testLibrary.topStreamedAnime());
    }

    @Test
    void topStreamedGenre() {
        fillingTestingLibrary();
        assertEquals("The most watched genre is ACTION with a total watch time of 5520", testLibrary.topStreamedGenre());
    }

    @Test
    void emptyTopStreamedGenre() {
        emptyTestLibrary();
        assertEquals("No currently tracked anime", testLibrary.topStreamedGenre());
    }

    @Test
    void totalWatchTime() {
        fillingTestingLibrary();
        assertEquals("Total Watch Time (estimated): 5750 minutes", testLibrary.totalWatchTime());
    }

    @Test
    void emptyWatchTime() {
        emptyTestLibrary();
        assertEquals("Total Watch Time (estimated): 0 minutes", testLibrary.totalWatchTime());
    }

    @Test
    void getGenreByAnime() {
        fillingTestingLibrary();
        String animeToTest = testLibrary.getAnime()[0].getName();
        assertEquals("The anime ATTACK ON TITAN has genres ACTION, DRAMA ", testLibrary.getGenreByAnime(animeToTest));
    }
}