package anime.util;

import anime.Entity.Anime;

import java.util.Comparator;

/**
 * Comparator used for sorting Anime based on episode count
 */
public class AnimeWatchTimeComparator implements Comparator<Anime> {
    /**
     * Compares two Anime objects
     *
     * @param o1 the Object that is being compared with, i.e. some Anime object
     * @param o2 the Object that is being compared, some Anime object
     * @return integer < 0 if o2 larger than  o1, >0 if o2 less than o1, and 0 if integers have the same episode count
     */
    @Override
    public int compare(Anime o1, Anime o2) {
        return (o1.getEpisodes() * Anime.EPISODE_LENGTH) - (o2.getEpisodes() * Anime.EPISODE_LENGTH);
    }
}
