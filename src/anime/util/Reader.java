package anime.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import anime.Entity.Anime;

public class Reader {

    public static ArrayList<Anime> Import(String fileName) {
            ArrayList<Anime> returnList = new ArrayList<>();
            File importFile = new File(fileName);

            if(importFile.isFile() & importFile.canRead() & importFile.exists()) {

                try{
                    FileReader reader = new FileReader(importFile);
                    BufferedReader bReader = new BufferedReader(reader);
                    String line = bReader.readLine();
                    String[] contents;

                    while(!line.isEmpty()) {
                        contents = line.split(",");
                        line = bReader.readLine();
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            }

            return returnList;
    }

}
