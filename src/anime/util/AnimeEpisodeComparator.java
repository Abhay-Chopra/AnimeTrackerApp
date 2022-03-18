package anime.util;
import anime.Entity.Anime;

import java.util.Comparator;

public class AnimeEpisodeComparator implements Comparator<Anime> {

    @Override
    public int compare(Anime o1, Anime o2) {
        return o1.getEpisodes() - o2.getEpisodes();
    }
}
