import java.io.*;

public class SerializationHandler {

    // Serializes the given object to the given file path
    public static void serializeObj(Object obj, String filePath) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    fileOut.close();
                    out.close();
                } catch (Exception ignored) {}
            }
        }
    }

    // Deserializes an object from the given file path
    public static Object deserializeObj(String filePath) throws IOException {
        Object obj;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(filePath);
            in = new ObjectInputStream(fileIn);
            obj = in.readObject();

        } catch (IOException | ClassNotFoundException i) {
            throw new IOException();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    fileIn.close();
                }
            } catch (Exception ignored) {}
        }

        return obj;
    }
}
