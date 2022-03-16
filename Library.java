import java.util.ArrayList;

public class Library {
    //The only container in the class, a List of Anime
    private ArrayList<Anime> animeList;

    public Library() {
        animeList = new ArrayList<Anime>();
    }

    //All of our anime accessing code and processing goes here
    public void addAnime(Anime newAnime) {
        animeList.add(newAnime);
    }

    public void removeAnime(Anime removeAnime) {
        animeList.remove(removeAnime);
    }


}
