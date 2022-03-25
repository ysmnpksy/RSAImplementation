import java.io.Serializable;

public class User implements Serializable {

    String username;
    KeySet keys;

    public User(String username) {
        this.username = username;
        this.keys = new KeySet();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public KeySet getKeys() {
        return keys;
    }

    public void setKeys(KeySet keys) {
        this.keys = keys;
    }
}