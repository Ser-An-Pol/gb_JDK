package Client;

import Common.iAccount;
import Common.iClientGUI;
import Common.iClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class ClientGUI extends JFrame implements iClientGUI {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 550;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 200;

    private final iClientService clientService;
    private iAccount user;

    private JTextField txtIP;
    private JTextField txtPort;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JTextArea chatArea;
    private JTextField txtMessage;
    private JButton btnSend;

    public ClientGUI(iClientService clientService) {
        this.clientService = clientService;

        initBackend();
        initFrontend();
    }

    private void initFrontend() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setTitle("Chat client (ID: " + clientService.getID() + ")");
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

    private void initBackend() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                clientService.closeClient();
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

    private void sendMessage() {
        clientService.sendMessageToServer(txtMessage.getText() + "\n");
    }

    private void initBtnLogin() {
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            if (txtName.getText().trim().isBlank()) {
                showMessageDialog(this, "Enter your name and try again...");
                return;
            }
            user = new User();
            user.setIP(txtIP.getText());
            user.setPort(Integer.parseInt(txtPort.getText()));
            user.setName(txtName.getText());
            clientService.loginToServer(user);
        });
    }

    private void initBtnSend() {
        btnSend = new JButton("Send");

        btnSend.addActionListener(e -> sendMessage());
    }

    @Override
    public void printInChat(String string) {
        chatArea.append(string);
    }

    @Override
    public void clearMessage() {
        txtMessage.setText("");
    }
}
