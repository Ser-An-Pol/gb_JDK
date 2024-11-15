package withForks;

public enum Status {
    THINKING("Думаю"),
    EATING("Кушаю");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
