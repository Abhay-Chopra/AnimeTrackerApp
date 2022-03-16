package src.anime.Entity;

public class SeasonAnime extends Anime {
    private Anime parentAnime;

    public SeasonAnime(Anime parent) {
        super();
        this.parentAnime = parent;
    }
}
