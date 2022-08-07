import java.io.*;

public class SerializationHandler {

    public static void serializeObj(Object obj, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream("ser/" + fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Serialized.......");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Object deserializeObj() {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        return obj;
    }
}
