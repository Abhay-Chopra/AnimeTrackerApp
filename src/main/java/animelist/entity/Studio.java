package animelist.entity;

import java.util.ArrayList;
import java.util.Objects;

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

    public void addAnime(Anime anime) {
        animeProduced.add(anime);
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Studio) {
            return Objects.equals(((Studio) other).getName(), this.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
