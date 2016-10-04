package base.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by banut on 03/10/2016.
 */
public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public List<String> read() {
        List<String> allLines = new ArrayList<>();
        String line;
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
            while ((line = br.readLine()) != null) {
                allLines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLines;
    }
}
