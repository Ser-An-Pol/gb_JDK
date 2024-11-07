package Client;

import Server.Server;
import Server.StateOfServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;

import static javax.swing.JOptionPane.showMessageDialog;

public class Client extends JFrame {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 550;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 200;

    private final Server server;
    private final int ID;

    private User user;
    private boolean login = false;

    private JTextField txtIP;
    private JTextField txtPort;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JTextArea chatArea;
    private JTextField txtMessage;
    private JButton btnSend;

    public Client(Server server) throws HeadlessException {
        this.server = server;
        this.ID = server.getNewID();

        initBackend();
        initFrontend();
    }

    private void initFrontend() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setTitle("Chat client (ID: " + ID + ")");
        setResizable(false);

        setVisible(true);

        JPanel general_1l = new JPanel(new BorderLayout());
        JPanel head_2l = new JPanel(new GridLayout(1, 2));
        JPanel reg_3l = new JPanel(new GridLayout(2, 2));
        JPanel bottom_2l = new JPanel(new BorderLayout());

        reg_3l.add(txtIP);
        reg_3l.add(txtPort);
        reg_3l.add(txtName);
        reg_3l.add(txtPassword);
        head_2l.add(reg_3l);
        head_2l.add(btnLogin);

        general_1l.add(head_2l, BorderLayout.NORTH);
        general_1l.add(chatArea, BorderLayout.CENTER);

        bottom_2l.add(txtMessage, BorderLayout.CENTER);
        bottom_2l.add(btnSend, BorderLayout.EAST);

        general_1l.add(bottom_2l, BorderLayout.SOUTH);

        this.add(general_1l);

    }

    private void initTxtIP() {
        txtIP = new JTextField("127.0.0.1");
    }

    private void initTxtPort() {
        txtPort = new JTextField("8189");
    }

    private void initTxtPassword() {
        txtPassword = new JPasswordField("111111");
        txtPassword.setEchoChar('-');
    }

    private void initTxtName() {
        txtName = new JTextField();
    }

    private void initChatArea() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
    }

    private void initTxtMessage() {
        txtMessage = new JTextField();

        txtMessage.addActionListener(e -> sendMessage());
    }

    private void initBtnLogin() {
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            if (txtName.getText().trim().isBlank()) {
                showMessageDialog(this, "Enter your name and try again...");
                return;
            }
            if (server.getStateOfServer() == StateOfServer.OFF) {
                chatArea.append("Сервер не доступен. Подключение не удалось.\n");
                return;
            }
            user = new User(txtIP.getText(),
                    Integer.parseInt(txtPort.getText()),
                    txtName.getText());
            login = true;
            chatArea.append("Вы успешно подключились к серверу.\n");
            loadHistory(server.getFileOfHistory());
        });
    }

    private void initBtnSend() {
        btnSend = new JButton("Send");

        btnSend.addActionListener(e -> sendMessage());
    }

    private void sendMessage(){
        if (!isLogin()) {
            showMessageDialog(this, "Вы не можете отправлять сообщения. Сначала подключитесь к серверу!");
            return;
        }

        int res = server.getMessageFromClient(user.getName() + ": " + txtMessage.getText() + "\n");
        if (res == 2) chatArea.append("Сервер в настоящий момент не доступен...\n");
        else if (res == 1) {
            txtMessage.setText("");
        }
    }

    private void initBackend() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                server.closeClient((Client) e.getSource());
            }
        });

        initTxtIP();
        initTxtPort();
        initTxtPassword();
        initTxtName();
        initChatArea();
        initTxtMessage();
        initBtnLogin();
        initBtnSend();
    }

    public boolean isLogin() {
        return login;
    }

    public void disconnect() {
        if (!isLogin()) return;
        chatArea.append("Связь с сервером потеряна. Соединение разорвано.\n");
        login = false;
    }

    public void getMessageFromServer(String msg) {
        chatArea.append(msg);
    }

    public void loadHistory(String fileName) {
        try {
            Path path = Path.of(fileName);
            for (String string : Files.readAllLines(path)) {
                chatArea.append(string + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
