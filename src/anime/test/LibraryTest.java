package anime.test;

import anime.Entity.Anime;
import anime.util.Library;
import anime.util.Reader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void ReaderImport() {
        File file = new File("test.txt");
        ArrayList<Anime> testArray = Reader.Import(file);
        assertFalse(testArray.isEmpty());
        assertEquals("ATTACK ON TITAN", testArray.get(0).getName());
        assertEquals("MONSTER", testArray.get(testArray.size()-1).getName());
    }

    private Library testLibary;

    private void fillingTestingLibrary() {
        testLibary = new Library();
        testLibary.addBulkAnime(Reader.Import(new File("test.txt")));
    }

    private void emptyTestLibrary() {
        testLibary = new Library();
    }

    @Test
    void getAnimeRatings() {
        fillingTestingLibrary();
        String test =
                "CODE GEASSE -> 8.7\n" +
                "MONSTER -> 8.82\n" +
                "GINTAMA -> 8.95\n" +
                "ATTACK ON TITAN -> 9.4\n" +
                "DEMON SLAYER -> 10.0\n" +
                "FULL METAL ALCHEMIST: BROTHERHOOD -> 10.0";
        assertEquals(test, testLibary.getAnimeRatings());
    }

    @Test
    void topStreamedAnime() {
        fillingTestingLibrary();
        String test = "" +
                "Your Top Streamed Anime Are\n" +
                "GINTAMA -> Watch Time: 2300 minutes\n" +
                "FULL METAL ALCHEMIST: BROTHERHOOD -> Watch Time: 1472 minutes\n" +
                "DEMON SLAYER -> Watch Time: 598 minutes";
        assertEquals(test, testLibary.topStreamedAnime());
    }

    @Test
    void topStreamedGenre() {
        fillingTestingLibrary();
        assertEquals("The most watched genre is ACTION with a total watch time of 5520", testLibary.topStreamedGenre());
    }

    @Test
    void emptyTopStreamedGenre() {
        emptyTestLibrary();
        assertEquals("No currently tracked anime", testLibary.topStreamedGenre());
    }

    @Test
    void totalWatchTime() {
        fillingTestingLibrary();
        assertEquals("Total Watch Time (estimated): 5750 minutes", testLibary.totalWatchTime());
    }

    @Test
    void emptyWatchTime() {
        emptyTestLibrary();
        assertEquals("Total Watch Time (estimated): 0 minutes", testLibary.totalWatchTime());
    }

    @Test
    void getGenreByAnime() {
        fillingTestingLibrary();
        String animeToTest = testLibary.getAnime()[0].getName();
        assertEquals("The anime ATTACK ON TITAN has genres ACTION, DRAMA ", testLibary.getGenreByAnime(animeToTest));
    }
}