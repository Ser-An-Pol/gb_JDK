package Server;

import Client.ClientService;
import Common.*;

import javax.swing.*;
import java.util.HashMap;

import static javax.swing.JOptionPane.showMessageDialog;

public class ServerService implements iServerService {

    private final iServerGUI serverGUI;
    private final HashMap<Integer, iClientService> clients;
    private StateOfServer stateOfServer;
    private Storage storage;

    public ServerService() {
        this.serverGUI = new ServerGUI(this);
        clients = new HashMap<>();
        stateOfServer = StateOfServer.OFF;
    }

    @Override
    public void createClientInstance() {
        int ID = clients.size();

        while (clients.containsKey(ID)) {
            ID++;
        }
        clients.put(ID, new ClientService(this, ID));
    }

    @Override
    public void closeClient(Integer id) {
        clients.remove(id);
    }

    @Override
    public void registerClient(Integer id) {
        sendHistory(id);
    }

    @Override
    public void startServer() {
        if (stateOfServer == StateOfServer.ON) {
            showMessageDialog((JFrame)serverGUI,"Сервер уже запущен!");
        }
        else {
            stateOfServer = StateOfServer.ON;
            serverGUI.printInChat(stateOfServer.toString());
            storage = new Storage("history.txt");
        }
    }

    @Override
    public void stopServer() {
        if (stateOfServer == StateOfServer.OFF) {
            showMessageDialog((JFrame)serverGUI, "Сервер уже остановлен!");
        }
        else {
            stateOfServer = StateOfServer.OFF;
            serverGUI.printInChat(stateOfServer.toString());
            storage.deleteHistory();
            for (Integer id : clients.keySet()) {
                clients.get(id).logoutFromServer();
            }
        }
    }

    @Override
    public int getMessageFromClient(String message) {
        if (stateOfServer == StateOfServer.OFF) return 2;

        serverGUI.printInChat(message);
        storage.addMessage(message);
        distributeMessage(message);

        return 1;
    }

    private void distributeMessage(String message) {
        for (Integer id : clients.keySet()) {
            sendMessageToClient(id, message);
        }
    }

    private void sendMessageToClient(Integer id, String message) {
        if (clients.get(id).isLogin()) {
            clients.get(id).printMessage(message);
        }
    }

    private void sendHistory(Integer id) {
        for (String s : storage.readHistory()) {
            sendMessageToClient(id, s);
        }
    }

    @Override
    public StateOfServer getStateOfServer() {
        return stateOfServer;
    }
}
