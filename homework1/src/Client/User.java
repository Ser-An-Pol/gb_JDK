package Client;

public class User {
    private final String IP;
    private final int Port;
    private final String Name;

    public User(String IP, int port, String name) {
        this.IP = IP;
        Port = port;
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
