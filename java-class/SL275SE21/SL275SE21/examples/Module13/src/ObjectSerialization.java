import java.io.*;
import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ObjectSerialization {
    private static Logger logger =
            Logger.getLogger(ObjectSerialization.class.getName());

    public static void main(String[] args) {
        PriceList list = new PriceList(LocalDate.now());
        list.addItem(new Drink("Tea", 1.99));
        list.addItem(new Food("Cake", 3.5));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/swap"))) {
            out.writeObject(list);
            list = null;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed write object into a file", e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/swap"))) {
            list = (PriceList) in.readObject();
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read object from file", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Unknown serialised type", e);
        }
    }
}
