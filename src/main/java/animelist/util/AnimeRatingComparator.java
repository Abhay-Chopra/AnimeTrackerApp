package animelist.util;

import animelist.entity.Anime;

import java.util.Comparator;

/**
 * Comparator to be able to sort anime by their rating
 */
public class AnimeRatingComparator implements Comparator<Anime> {
    /**
     * Compares two Anime objects
     *
     * @param o1 the Object that is being compared with, i.e. some Anime object
     * @param o2 the Object that is being compared, some Anime object
     * @return integer < 0 if o2 larger than  o1, >0 if o2 less than o1, and 0 if integers have the same ratings
     */
    @Override
    public int compare(Anime o1, Anime o2) {
        return Double.compare(o1.getRating(), o2.getRating());
    }
}
