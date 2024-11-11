package Common;

public enum StateOfServer {
    ON("Сервер запущен!"),
    OFF("Сервер остановлен!");

    private final String info;

    StateOfServer(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info + '\n';
    }
}
