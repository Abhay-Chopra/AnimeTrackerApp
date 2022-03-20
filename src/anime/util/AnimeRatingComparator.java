package anime.util;

import anime.Entity.Anime;

import java.util.Comparator;
import java.util.DuplicateFormatFlagsException;

public class AnimeRatingComparator implements Comparator<Anime> {
    @Override
    public int compare(Anime o1, Anime o2) {
        return Double.compare(o1.getRating(), o2.getRating());
    }
}
