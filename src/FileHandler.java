import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private String filePath; // Location of file to read/write to

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Returns a list of each line in the file
    public ArrayList<String> readLineByLine() {
        ArrayList<String> recordList = new ArrayList<String>();
        try {
            File file = new File(filePath);    // creates a new file instance
            FileReader fr = new FileReader(file);   // reads the file
            BufferedReader br = new BufferedReader(fr);  // creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                recordList.add(line);
            }
            fr.close();    // closes the stream and release the resources
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
