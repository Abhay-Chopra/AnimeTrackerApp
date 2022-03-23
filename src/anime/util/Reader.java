package anime.util;

import anime.Entity.Anime;
import anime.Entity.Studio;

import java.io.*;
import java.util.ArrayList;

//TODO Add better error handling for both functions
public class Reader {
    /**
     * Creating an anime list give all info from a file
     * @param file File
     * @return arraylist containing anime
     */
    public static ArrayList<Anime> Import(File file) {
        ArrayList<Anime> returnList = new ArrayList<>();
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
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
        }
        return returnList;
    }

    /**
     *
     * @param anime Array containing all anime currently stored
     * @param fileName name of the file that will be attempted to be saved to
     */
    public static void save(Anime[] anime, String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file " + file);
                System.exit(1);
            }
        }
        if(file.exists() && file.isFile() && file.canWrite()){
            try(FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter)) {
                for (Anime currentAnime : anime) {
                    StringBuilder genres = new StringBuilder();
                    for (String genre: currentAnime.getGenres()) {
                        genres.append(String.format("%s;", genre.toLowerCase()));
                    }
                    genres.deleteCharAt(genres.length() - 1);
                    StringBuilder themes = new StringBuilder();
                    for (String theme: currentAnime.getThemes()) {
                        themes.append(String.format("%s;", theme.toLowerCase()));
                    }
                    themes.deleteCharAt(themes.length() - 1);
                    String animeInfo = String.format("%s,%s,%s,%s,%s,%s,%s,%s", currentAnime.getName().toLowerCase(), genres
                                                                           , themes, currentAnime.getRating(), currentAnime.getEpisodes()
                                                                           , currentAnime.getStatus().ordinal(), currentAnime.getSeason().ordinal()
                                                                           , currentAnime.getStudio());
                    printWriter.println(animeInfo);
                }
            } catch (IOException e) {
                System.err.println("Error writing to file " + file);
                System.exit(1);
            }
        }
    }
}
