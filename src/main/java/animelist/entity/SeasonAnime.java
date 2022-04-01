package animelist.entity;

import java.util.ArrayList;

public class SeasonAnime extends Anime {
    private Anime parentAnime;

    public SeasonAnime(Anime parent, String animeTitle, ArrayList<String> addedGenres, ArrayList<String> addedThemes, int episodes, double rating, Status status, Season season,
                       Studio animeStudio) {
        super(animeTitle, addedGenres, addedThemes, episodes, rating, status, season, animeStudio);
        this.parentAnime = parent;
    }
}
