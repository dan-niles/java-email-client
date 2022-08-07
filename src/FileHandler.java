import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {
    private String filePath; // Location of file to read/write to

    public FileHandler(String filePath) {
        this.filePath = filePath;

        // Create file if it does not exist
        File file = new File(filePath);
        if (!Files.exists(Paths.get(filePath))) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

    // Returns a list of each line in the file
    public ArrayList<String> readLineByLine() {
        ArrayList<String> recordList = new ArrayList<String>();
        try {
            File file = new File(filePath);    // Creates a new file instance
            FileReader fr = new FileReader(file);   // Reads the file
            BufferedReader br = new BufferedReader(fr);  // Creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                recordList.add(line);
            }
            fr.close();    // Closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recordList;
    }

    // Appends given line to the file
    public void appendToFile(String line) {
        try (FileWriter f = new FileWriter(filePath, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);) {
            p.println(line);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
