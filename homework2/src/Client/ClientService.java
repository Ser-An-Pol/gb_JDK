package Client;

import Common.*;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ClientService implements iClientService {
    private final iServerService serverService;
    private final iClientGUI clientGUI;

    private final int ID;
    private iAccount user;
    private boolean login;

    public ClientService(iServerService serverService, int ID) {
        this.serverService = serverService;
        this.ID = ID;
        clientGUI = new ClientGUI(this);

    }

    @Override
    public void loginToServer(iAccount user) {
        if (login) {
            showMessageDialog((JFrame)clientGUI, "Соединение уже установлено и активно!");
            return;
        }
        if (serverService.getStateOfServer() == StateOfServer.OFF) {
            clientGUI.printInChat("Сервер не доступен. Подключение не удалось.\n");
            return;
        }
        this.user = user;
        login = true;
        clientGUI.printInChat("Вы успешно подключились к серверу.\n");
        serverService.registerClient(ID);
    }

    @Override
    public void sendMessageToServer(String message) {
        if (!isLogin()) {
            showMessageDialog((JFrame)clientGUI, "Вы не можете отправлять сообщения. Сначала подключитесь к серверу!");
            return;
        }
        int res = serverService.getMessageFromClient(user.getName() + ": " + message);
        if (res == 2) printMessage("Сервер в настоящий момент не доступен...\n");
        if (res == 1) clientGUI.clearMessage();
    }

    @Override
    public void logoutFromServer() {
        if (!isLogin()) return;
        clientGUI.printInChat("Связь с сервером потеряна. Соединение разорвано.\n");
        login = false;
    }

    @Override
    public void closeClient() {
        serverService.closeClient(ID);
    }

    @Override
    public boolean isLogin() {
        return login;
    }

    @Override
    public void printMessage(String message) {
        clientGUI.printInChat(message);
    }

    @Override
    public Integer getID() {
        return ID;
    }

}
