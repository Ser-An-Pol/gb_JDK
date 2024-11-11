package Common;

public interface iServerService {
    void createClientInstance();
    void closeClient(Integer id);
    void registerClient(Integer id);
    void startServer();
    void stopServer();
    int getMessageFromClient(String message);
    StateOfServer getStateOfServer();
}
