package files.utils;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <T> void write(String raw, List<T> items){
        try {
            PrintWriter printWriter = new PrintWriter(raw);
            for (Object item: items){
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e){
            throw new IllegalArgumentException(raw + "invalid");
        }
    }
    public static List<String> read(String raw) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(raw);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty())
                lines.add(line);

        }catch (IOException e){
            throw new IllegalArgumentException(raw + "invalid");
        }
        return lines;
    }
}
