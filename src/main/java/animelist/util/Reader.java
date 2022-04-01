package animelist.util;

import animelist.entity.Anime;
import animelist.entity.Studio;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that can read data from files and store data to files
 */
public class Reader {
    /**
     * Creating an anime list give all info from a file
     *
     * @param file File
     * @return arraylist containing anime
     * @author Abhay Chopra, Brandon Greene
     */
    public static ArrayList<Anime> Import(File file) {
        //creating initial list that will contain all anime objects
        ArrayList<Anime> returnList = new ArrayList<>();
        //with-resources style of creating buffer and file reader
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            //reading through file
            while (line != null) {
                String[] animeInfo = line.split(",");
                String animeName = animeInfo[0].toUpperCase();
                ArrayList<String> animeGenreList = new ArrayList<>();
                for (String anime : animeInfo[1].split(";")) {
                    animeGenreList.add(anime.toUpperCase());
                }
                ArrayList<String> animeThemeList = new ArrayList<>();
                for (String anime : animeInfo[2].split(";")) {
                    animeThemeList.add(anime.toUpperCase());
                }
                double animeRating = Double.parseDouble(animeInfo[3]);
                int animeEpisodes = Integer.parseInt(animeInfo[4].strip());
                Anime.Status animeStatus = Anime.Status.values()[Integer.parseInt(animeInfo[5].strip())];
                Anime.Season animeSeason = Anime.Season.values()[Integer.parseInt(animeInfo[6].strip())];
                Studio animeStudio = new Studio(animeInfo[7].toUpperCase().strip());
                //Creating anime object using the info from CSV file
                Anime anime = new Anime(animeName, animeGenreList, animeThemeList, animeEpisodes, animeRating, animeStatus, animeSeason, animeStudio);
                returnList.add(anime);
                line = bufferedReader.readLine();
            }

        }
        //Catching when file reading fails
        catch (Exception e) {
            System.err.println("Error reading from anime file!");
            System.exit(1);
        }
        return returnList;
    }

    /**
     * Save our anime to a specified txt file
     *
     * @param anime    Array containing all anime currently stored
     * @param fileName name of the file that will be attempted to be saved to
     */
    public static void save(Anime[] anime, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file " + file);
                System.exit(1);
            }
        }
        if (file.exists() && file.isFile() && file.canWrite()) {
            //with-resources method of creating file and print writer
            try (FileWriter fileWriter = new FileWriter(file);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                for (Anime currentAnime : anime) {
                    StringBuilder genres = new StringBuilder();
                    for (String genre : currentAnime.getGenres()) {
                        genres.append(String.format("%s;", genre.toLowerCase()));
                    }
                    //getting rid of the final ; in string
                    genres.deleteCharAt(genres.length() - 1);
                    StringBuilder themes = new StringBuilder();
                    for (String theme : currentAnime.getThemes()) {
                        themes.append(String.format("%s;", theme.toLowerCase()));
                    }
                    //getting rid of the final ; in string
                    themes.deleteCharAt(themes.length() - 1);
                    //Creating a line to add to external file
                    String animeInfo = String.format("%s,%s,%s,%s,%s,%s,%s,%s", currentAnime.getName().toLowerCase(), genres
                            , themes, currentAnime.getRating(), currentAnime.getEpisodes()
                            , currentAnime.getStatus().ordinal(), currentAnime.getSeason().ordinal()
                            , currentAnime.getStudio().getName());
                    printWriter.println(animeInfo);
                }
            }
            //Catching exceptions when file writing is interrupted
            catch (IOException e) {
                System.err.println("Error writing to file " + file);
                System.exit(1);
            }
        }
    }
}
