package anime.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import anime.Entity.Anime;
import anime.Entity.Studio;

public class Reader {
    /**
     * Creating an anime list give all info from a file
     * @param file File
     * @return arraylist containing anime
     */
    public static ArrayList<Anime> Import(File file) {
        ArrayList<Anime> returnList = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while(line != null) {
                String[] animeInfo = line.split(",");
                String animeName = animeInfo[0].toUpperCase();
                ArrayList<String> animeGenreList = new ArrayList<>();
                for (String anime: animeInfo[1].split(";")) {
                    animeGenreList.add(anime.toUpperCase());
                }
                ArrayList<String> animeThemeList = new ArrayList<>();
                for (String anime: animeInfo[2].split(";")) {
                    animeThemeList.add(anime.toUpperCase());
                }
                double animeRating = Double.parseDouble(animeInfo[3]);
                int animeEpisodes = Integer.parseInt(animeInfo[4].strip());
                Anime.Status animeStatus = Anime.Status.values()[Integer.parseInt(animeInfo[5].strip())];
                Anime.Season animeSeason = Anime.Season.values()[Integer.parseInt(animeInfo[6].strip())];
                Studio animeStudio = new Studio(animeInfo[7].toUpperCase().strip());

                Anime anime = new Anime(animeName, animeGenreList, animeThemeList, animeEpisodes, animeRating, animeStatus, animeSeason, animeStudio);
                returnList.add(anime);
                line = bufferedReader.readLine();
            }

        }catch (Exception e) {
            System.err.println("Error reading from anime file!");
            System.exit(1);
        }//TODO Add better error handling

        return returnList;
    }
}
