import java.util.ArrayList;

public class Studio {
    //Fields
    private String name;
    private ArrayList<Anime> createdAnime = new ArrayList<>();

    //Constructor
    public Studio(String name) {
        this.name = name;
    }

    public ArrayList<Anime> getCreatedAnime() {
        return createdAnime;
    }

    public void setCreatedAnime(ArrayList<Anime> createdAnime) {
        this.createdAnime = createdAnime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
