import java.io.*;

public class SerializationHandler {

    // Serializes the given object to the given file path
    public static void serializeObj(Object obj, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Deserializes an object from the given file path
    public static Object deserializeObj(String filePath) throws IOException {
        Object obj;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            throw new IOException();
        }

        return obj;
    }
}
