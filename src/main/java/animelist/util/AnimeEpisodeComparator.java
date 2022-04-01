package animelist.util;

import animelist.entity.Anime;

import java.util.Comparator;

/**
 * Comparator to allow for sorting anime based on the number of episodes
 */
public class AnimeEpisodeComparator implements Comparator<Anime> {
    /**
     * @param o1 the Object that is being compared with, i.e. some Anime object
     * @param o2 the Object that is being compared, some Anime object
     * @return integer < 0 if o2 larger than  o1, >0 if o2 less than o1, and 0 if integers have the same count
     */
    @Override
    public int compare(Anime o1, Anime o2) {
        return o1.getEpisodes() - o2.getEpisodes();
    }
}
