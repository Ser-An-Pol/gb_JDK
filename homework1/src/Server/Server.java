package Server;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Server extends JFrame {
    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_HEIGHT = 500;
    private static final int POSX = 250;
    private static final int POSY = 250;

    private List<Client> listOfClients;

    private JButton btnNewClient;
    private JTextArea chatArea;
    private JButton btnStartServer;
    private JButton btnStopServer;

    private StateOfServer stateOfServer;
    private Storage storage;

    public Server() throws HeadlessException {
        initBackend();
        initFrontend();

    }

    private void initFrontend() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(POSX, POSY);
        setTitle("Chat server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(btnNewClient, BorderLayout.NORTH);
        add(chatArea, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(1,2));
        bottom.add(btnStartServer);
        bottom.add(btnStopServer);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initBtnNewClient() {
        btnNewClient = new JButton("Create new client...");

        btnNewClient.addActionListener(e-> runNewClient());
    }

    private void initBtnStartServer() {
        btnStartServer = new JButton("Start server");

        btnStartServer.addActionListener(e -> {
            if (stateOfServer == StateOfServer.ON) {
                showMessageDialog(this, "Сервер уже запущен!");
            }
            else {
                stateOfServer = StateOfServer.ON;
                chatArea.append(stateOfServer.toString());
                storage = new Storage("history.txt");
            }
        });
    }

    private void initBtnStopServer() {
        btnStopServer = new JButton("Stop server");

        btnStopServer.addActionListener(e -> {
            if (stateOfServer == StateOfServer.OFF) {
                showMessageDialog(this, "Сервер уже остановлен!");
            }
            else {
                stateOfServer = StateOfServer.OFF;
                chatArea.append(stateOfServer.toString());
                storage.deleteHistory();
                for (Client client : listOfClients) {
                    client.disconnect();
                }
            }
        });
    }

    private void initChatArea() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
    }

    private void initBackend() {
        listOfClients = new ArrayList<>();

        initBtnNewClient();
        initChatArea();
        initBtnStartServer();
        initBtnStopServer();

        stateOfServer = StateOfServer.OFF;
    }

    public boolean runNewClient() {
        Client client;

        try {
            client = new Client(this);
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
            return false;
        }

        listOfClients.add(client);
        return true;
    }

    public int getNewID() {
        return listOfClients.size();
    }

    public void closeClient(Client client) {
        listOfClients.remove(client);
    }

    public int getMessageFromClient(String msg) {
        if (stateOfServer == StateOfServer.OFF) return 2;

        chatArea.append(msg);
        storage.addMessage(msg);
        distributeMessage(msg);

        return 1;
    }

    private void distributeMessage(String msg) {
        for (Client client : listOfClients) {
            if (client.isLogin()) {
                client.getMessageFromServer(msg);
            }
        }
    }

    public StateOfServer getStateOfServer() {
        return stateOfServer;
    }

    public String getFileOfHistory() {
        return storage.getFileName();
    }
}
