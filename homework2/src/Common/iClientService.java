package Common;

public interface iClientService {
    void loginToServer(iAccount user);
    void sendMessageToServer(String message);
    void logoutFromServer();
    void closeClient();
    boolean isLogin();
    void printMessage(String message);
    Integer getID();
}
