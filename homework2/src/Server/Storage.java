package Server;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String fileName;
    private Path path;

    public Storage(String fileName) {
        this.fileName = fileName;
        try {
            this.path = Path.of(fileName);
            Files.createFile(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMessage(String msg) {
        try {
            Files.writeString(path, msg, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteHistory() {
        try {
            Files.delete(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> readHistory() {
        List<String> history = new ArrayList<>();
        try {
            Path path = Path.of(fileName);
            for (String string : Files.readAllLines(path)) {
                history.add(string + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return history;
    }
}
