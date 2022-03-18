package anime.util;

import anime.Entity.Anime;
import java.util.Comparator;

public class AnimeWatchTimeComparator implements Comparator<Anime> {

    @Override
    public int compare(Anime o1, Anime o2) {
        return (o1.getEpisodes()*Anime.EPISODE_LENGTH) - (o2.getEpisodes()*Anime.EPISODE_LENGTH);
    }
}
