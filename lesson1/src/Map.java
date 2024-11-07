import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class Map extends JPanel {
    private int panelWidth;
    private int panelHeight;
    private int cellWidth;
    private int cellHeight;

    private int mode;
    private int fieldSizeX = 3;
    private int fieldSizeY = 3;
    private int countPositions;
    private int maxPositions;
    private int winLength = 3;

    private int[][] field;
    private final int VAL_EMPTY = 0;
    private final int VAL_X = 1;
    private final int VAL_O = 2;
    private boolean turn;
    private final Random random = new Random();

    private boolean isFin = false;

    enum FIN_STATE {
        WIN_X("Win playerX!!!"),
        WIN_Y("Win playerY!!!"),
        DRAW("Win both - DRAW!!!");

        private final String msg;

        FIN_STATE(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }

    FIN_STATE finState;

    public Map() {
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (field == null || isFin) return;
                goHUMAN(e);
            }
        });
    }

    private int[] getXY(MouseEvent e) {
        int[] xy = new int[2];
        xy[0] = e.getX() / cellWidth;
        xy[1] = e.getY() / cellHeight;
        //System.out.printf("Cell: [%d, %d]\n", xy[0], xy[1]);

        return xy;
    }

    private void goHUMAN(MouseEvent e) {
        int[] xy = getXY(e);

        if (field[xy[0]][xy[1]] != VAL_EMPTY) {
            showMessageDialog(this, "Cell is not free!!! Try again...");
        }
        else {
            field[xy[0]][xy[1]] = turn ? VAL_X : VAL_O;
            countPositions++;
            if (checkState(xy)) return;
            turn = !turn;
            if (mode == 1 || mode == 2) goAI();
        }
        repaint();
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.mode = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        maxPositions = fieldSizeX*fieldSizeY;
        countPositions = 0;
        this.winLength = winLength;

        this.field = new int[fieldSizeX][fieldSizeY];
        resetField();

        System.out.printf("Mode = %d\nField: %d X %d\nWin length = %d\n", mode, fieldSizeX, fieldSizeY, winLength);

        turn = true;
        isFin = false;

        repaint();

        if (mode == 1) goAI();
        if (mode == 3)
            while (!isFin) goAI();
    }

    private void goAI() {
        int[] xy = findXY();
        field[xy[0]][xy[1]] = turn ? VAL_X : VAL_O;
        countPositions++;
        repaint();

        if (checkState(xy)) return;
        turn = !turn;
    }

    private boolean checkState(int[] xy) {
        if (isWin(xy)) {
            isFin = true;
            finState = turn ? FIN_STATE.WIN_X : FIN_STATE.WIN_Y;
            repaint();
            return true;
        }
        if (countPositions >= maxPositions) {
            isFin = true;
            finState = FIN_STATE.DRAW;
            repaint();
            return true;
        }
        return false;
    }

    private boolean isWin(int[] xy) {
//             0    1
//          \  |  /
//           \ | /
//        -----*----- 2
//           / | \
//          /  |  \
//                 3

        return ((checkDirection_0(xy, field[xy[0]][xy[1]]) >= winLength) ||
                (checkDirection_1(xy, field[xy[0]][xy[1]]) >= winLength) ||
                (checkDirection_2(xy, field[xy[0]][xy[1]]) >= winLength) ||
                (checkDirection_3(xy, field[xy[0]][xy[1]]) >= winLength));
    }

    private int checkDirection_0(int[] xy, int val) {
        int count = 1;

        for (int i = xy[0] + 1; i < fieldSizeX; i++)
            if (field[i][xy[1]] == val)
                count++;
            else
                break;

        for (int i = xy[0] - 1; i >= 0; i--)
            if (field[i][xy[1]] == val)
                count++;
            else
                break;

        return count;
    }

    private int checkDirection_1(int[] xy, int val) {
        int count = 1;

        for (int i = xy[0] - 1, j = xy[1] + 1; i >= 0 && j < fieldSizeY; i--, j++)
            if (field[i][j] == val)
                count++;
            else
                break;

        for (int i = xy[0] + 1, j = xy[1] - 1; i < fieldSizeX && j >= 0; i++, j--)
            if (field[i][j] == val)
                count++;
            else
                break;

        return count;
    }

    private int checkDirection_2(int[] xy, int val) {
        int count = 1;

        for (int i = xy[1] + 1; i < fieldSizeY; i++)
            if (field[xy[0]][i] == val)
                count++;
            else
                break;

        for (int i = xy[1] - 1; i >= 0; i--)
            if (field[xy[0]][i] == val)
                count++;
            else
                break;

        return count;
    }

    private int checkDirection_3(int[] xy, int val) {
        int count = 1;

        for (int i = xy[0] + 1, j = xy[1] + 1; i < fieldSizeX && j < fieldSizeY; i++, j++)
            if (field[i][j] == val)
                count++;
            else
                break;

        for (int i = xy[0] - 1, j = xy[1] - 1; i >= 0 && j >= 0; i--, j--)
            if (field[i][j] == val)
                count++;
            else
                break;

        return count;
    }

    private int maxLine(int[] xy, int val) {
        int a = checkDirection_0(xy, val);
        int b = checkDirection_1(xy, val);
        a = Math.max(a, b);

        b = checkDirection_2(xy, val);
        a = Math.max(a, b);

        b = checkDirection_3(xy, val);
        return Math.max(a, b);
    }

    private int[] findXY() {
        int[] xy = new int[2];
//        do {
//            xy[0] = random.nextInt(fieldSizeX);
//            xy[1] = random.nextInt(fieldSizeY);
//        } while (field[xy[0]][xy[1]] != VAL_EMPTY);

        int[][] risk_map = new int[maxPositions][2];
        int risk_repeat = 0, risk_max = 1;
        int[][] win_map = new int[maxPositions][2];
        int win_repeat = 0, win_max = 1;

        int myVal, opVal;

        if (turn) {
            myVal = VAL_X;
            opVal = VAL_O;
        }
        else {
            myVal = VAL_O;
            opVal = VAL_X;
        }

        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++)
                if (field[i][j] == VAL_EMPTY)
                {
                    int ml;
                    ml = maxLine(new int[] {i, j}, opVal);
                    if (ml > risk_max)
                    {
                        risk_max = ml;
                        risk_map[0][0] = i;
                        risk_map[0][1] = j;
                        risk_repeat = 1;
                    }
                    else if (ml == risk_max)
                    {
                        risk_map[risk_repeat][0] = i;
                        risk_map[risk_repeat][1] = j;
                        risk_repeat++;
                    }
                }

        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++)
                if (field[i][j] == VAL_EMPTY)
                {
                    int ml;
                    ml = maxLine(new int[] {i, j}, myVal);
                    if (ml > win_max)
                    {
                        win_max = ml;
                        win_map[0][0] = i;
                        win_map[0][1] = j;
                        win_repeat = 1;
                    }
                    else if (ml == win_max)
                    {
                        win_map[win_repeat][0] = i;
                        win_map[win_repeat][1] = j;
                        win_repeat++;
                    }
                }

        if (risk_max > win_max)
        {
            if (risk_repeat == 1) {
                xy[0] = risk_map[0][0];
                xy[1] = risk_map[0][1];
            } else {
                int i = random.nextInt(risk_repeat);
                xy[0] = risk_map[i][0];
                xy[1] = risk_map[i][1];
            }
        }
        else
        {
            if (win_repeat == 1) {
                xy[0] = win_map[0][0];
                xy[1] = win_map[0][1];
            } else {
                int i = random.nextInt(win_repeat);
                xy[0] = win_map[i][0];
                xy[1] = win_map[i][1];
            }
        }

        return xy;
    }

    private void resetField() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = VAL_EMPTY;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        drawField(g);
        if (field != null) drawGameState(g);
        if (isFin) drawGameFin(g);
    }

    private void drawGameFin(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString(finState.toString(), 20, getHeight()/2);
    }

    private void drawField(Graphics g) {
        panelHeight = getHeight();
        panelWidth = getWidth();
        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        g.setColor(Color.WHITE);
        for (int i = 1; i <= fieldSizeX; i++) {
            g.drawLine(i*cellWidth, 0, i*cellWidth, panelHeight);
        }
        for (int i = 1; i <= fieldSizeY; i++) {
            g.drawLine(0, i*cellHeight, panelWidth, i*cellHeight);
        }
    }

    private void drawGameState(Graphics g) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                switch (field[i][j]) {
                    case VAL_X -> drawX(g, i, j);
                    case VAL_O -> drawO(g, i, j);
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y) {
        int figWidth = (int) (0.8*cellWidth);
        int figHeight = (int) (0.8*cellHeight);
        int x0 = (int) ((x+0.1)*cellWidth);
        int y0 = (int) ((y+0.1)*cellHeight);

        g.drawLine(x0, y0, x0 + figWidth, y0 + figHeight);
        g.drawLine(x0, y0+ figHeight, x0 + figWidth, y0 );
    }

    private void drawO(Graphics g, int x, int y) {
        int figWidth = (int) (0.8*cellWidth);
        int figHeight = (int) (0.8*cellHeight);
        int x0 = (int) ((x+0.1)*cellWidth);
        int y0 = (int) ((y+0.1)*cellHeight);

        g.drawOval(x0, y0, figWidth, figHeight);
    }
}
