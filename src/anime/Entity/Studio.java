package anime.Entity;
import java.util.ArrayList;

public class Studio {
    //Fields
    private String name;
    private ArrayList<Anime> animeProduced = new ArrayList<>();

    public Studio(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
