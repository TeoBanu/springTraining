package base.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    public List<String> read(String filename) {
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
