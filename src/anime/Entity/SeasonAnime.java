package anime.Entity;

import java.util.ArrayList;

public class SeasonAnime extends Anime {
    private Anime parentAnime;

    public SeasonAnime(Anime parent, String animeTitle, ArrayList<String> addedGenres, ArrayList<String> addedThemes, int episodes, double rating, Status status, Season season) {
        super(animeTitle, addedGenres, addedThemes, episodes, rating, status, season);
        this.parentAnime = parent;
    }
}
