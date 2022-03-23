package anime.test;

import anime.Entity.Anime;
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

    @Test
    void containsAnime() {
    }

    @Test
    void addAnime() {
    }

    @Test
    void removeAnime() {
    }

    @Test
    void addStudio() {
    }

    @Test
    void removeStudio() {
    }

    @Test
    void getAnimeRatings() {
    }

    @Test
    void topStreamedAnime() {
    }

    @Test
    void topStreamedGenre() {
    }

    @Test
    void totalWatchTime() {
    }

    @Test
    void getGenreByAnime() {
    }

    @Test
    void allAnimeTracked() {
    }

    @Test
    void getAnime() {
    }

    @Test
    void getStudios() {
    }

    @Test
    void allStudiosTracked() {
    }
}