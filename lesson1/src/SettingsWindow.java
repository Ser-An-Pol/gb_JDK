import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 730;
    private static final int WINDOW_WIDTH = 450;

    JButton btnStart = new JButton("Start new game");

    private final JPanel nest;
    private JRadioButton mode0, mode1, mode2, mode3;
    private JSlider setFieldX;
    private JSlider setFieldY;
    private JSlider setWinLength;

    private int gameMode = 3;
    private int fieldSizeX = 3;
    private int fieldSizeY = 3;
    private int winLength = 3;


    public SettingsWindow(GameWindow gameWindow) throws HeadlessException {

        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setTitle("Tune the game...");

        nest = new JPanel(new GridLayout(4, 1));

        initModeChoose();
        initSetFieldSizeX();
        initSetFieldSizeY();
        initSetWinLength();

        add(nest, BorderLayout.NORTH);

        btnStart.addActionListener(e -> {
            gameWindow.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength);
            setVisible(false);
        });

        add(btnStart, BorderLayout.SOUTH);

    }

    private void initModeChoose()
    {
        JPanel modePanel = new JPanel(new GridLayout(2, 1));
        JLabel modeLabel = new JLabel("Choose mode of game...");

        mode0 = new JRadioButton("  Human-Human");
        mode1 = new JRadioButton("  AI-Human");
        mode2 = new JRadioButton("  Human-AI");
        mode3 = new JRadioButton("  AI-AI");

        mode3.setSelected(true);

        mode0.addActionListener(e -> gameMode = 0);
        mode1.addActionListener(e -> gameMode = 1);
        mode2.addActionListener(e -> gameMode = 2);
        mode3.addActionListener(e -> gameMode = 3);

        ButtonGroup rbGroup = new ButtonGroup();
        JPanel radioPanel = new JPanel(new GridLayout(2, 2));

        rbGroup.add(mode0);
        rbGroup.add(mode1);
        rbGroup.add(mode2);
        rbGroup.add(mode3);

        radioPanel.add(mode0);
        radioPanel.add(mode1);
        radioPanel.add(mode2);
        radioPanel.add(mode3);

        modePanel.add(modeLabel);
        modePanel.add(radioPanel);

        modePanel.setBorder(BorderFactory.createEmptyBorder((int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH),
                (int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH)));

        nest.add(modePanel);
    }

    private void initSetFieldSizeX()
    {
        JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
        JLabel fieldLabel = new JLabel("Set sizeX of playing field...");
        JLabel printSizeX = new JLabel("SizeX is " + fieldSizeX);

        setFieldX = new JSlider(SwingConstants.HORIZONTAL, 3, 20, 3);

        setFieldX.addChangeListener(e -> {
            fieldSizeX = setFieldX.getValue();
            printSizeX.setText("SizeX is " + fieldSizeX);
            if (setWinLength != null) setWinLength.setMaximum(minSize());
        });

        fieldPanel.add(fieldLabel);
        fieldPanel.add(setFieldX);
        fieldPanel.add(printSizeX);

        fieldPanel.setBorder(BorderFactory.createEmptyBorder((int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH),
                (int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH)));

        nest.add(fieldPanel);
    }

    private void initSetFieldSizeY()
    {
        JPanel fieldPanel = new JPanel(new GridLayout(3, 1));
        JLabel fieldLabel = new JLabel("Set sizeY of playing field...");
        JLabel printSizeY = new JLabel("SizeY is " + fieldSizeY);

        setFieldY = new JSlider(SwingConstants.HORIZONTAL, 3, 20, 3);

        setFieldY.addChangeListener(e -> {
            fieldSizeY = setFieldY.getValue();
            printSizeY.setText("SizeY is " + fieldSizeY);
            if (setWinLength != null) setWinLength.setMaximum(minSize());
        });

        fieldPanel.add(fieldLabel);
        fieldPanel.add(setFieldY);
        fieldPanel.add(printSizeY);

        fieldPanel.setBorder(BorderFactory.createEmptyBorder((int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH),
                (int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH)));

        nest.add(fieldPanel);
    }

    private int minSize() {
        return Math.min(fieldSizeX, fieldSizeY);
    }

    private void initSetWinLength()
    {
        JPanel winPanel = new JPanel(new GridLayout(3, 1));
        JLabel winLabel = new JLabel("Set length of winning sequence...");
        JLabel printLength = new JLabel("Win length is " + winLength);

        setWinLength = new JSlider(SwingConstants.HORIZONTAL, 3, minSize(), 3);

        setWinLength.addChangeListener(e -> {
            winLength = setWinLength.getValue();
            printLength.setText("Win length is " + winLength);
        });

        winPanel.add(winLabel);
        winPanel.add(setWinLength);
        winPanel.add(printLength);

        winPanel.setBorder(BorderFactory.createEmptyBorder((int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH),
                (int)(.05*WINDOW_HEIGHT), (int)(.1*WINDOW_WIDTH)));

        nest.add(winPanel);
    }

}