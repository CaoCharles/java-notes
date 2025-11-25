import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PriceList implements Serializable {
    private LocalDate date;
    private Set<Product> items = new HashSet<>();
    private transient String hash = generateHash();

    public PriceList(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Product> getItems() {
        return items;
    }

    public void setItems(Set<Product> items) {
        this.items = items;
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    private String generateHash() {
        return "hash";
    }

    public String generateHash(Object obj) throws NoSuchAlgorithmException, IOException {
        String hash = null;
        try (ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteArrayStream)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            out.writeObject(obj);
            hash = new BigInteger(1, md.digest(byteArrayStream.toByteArray())).toString(16);
        }
        return hash;
    }
}
