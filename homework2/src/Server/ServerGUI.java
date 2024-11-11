package Server;

import Common.iServerGUI;
import Common.iServerService;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements iServerGUI {
    private static final int WINDOW_WIDTH = 550;
    private static final int WINDOW_HEIGHT = 500;
    private static final int POSX = 250;
    private static final int POSY = 250;

    private JButton btnNewClient;
    private JTextArea chatArea;
    private JButton btnStartServer;
    private JButton btnStopServer;

    private final iServerService serverService;

    public ServerGUI(iServerService serverService) {
        this.serverService = serverService;

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

    private void initBackend() {
        initBtnNewClient();
        initChatArea();
        initBtnStartServer();
        initBtnStopServer();

    }

    private void initBtnNewClient() {
        btnNewClient = new JButton("Create new client...");

        btnNewClient.addActionListener(e-> serverService.createClientInstance());
    }

    private void initBtnStartServer() {
        btnStartServer = new JButton("Start server");

        btnStartServer.addActionListener(e -> serverService.startServer());
    }

    private void initBtnStopServer() {
        btnStopServer = new JButton("Stop server");

        btnStopServer.addActionListener(e -> serverService.stopServer());
    }

    private void initChatArea() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
    }

    @Override
    public void printInChat(String string) {
        chatArea.append(string);
    }
}
