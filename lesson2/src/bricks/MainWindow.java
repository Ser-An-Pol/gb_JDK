package bricks;

import circles.Background;
import common.CanvasRepaintListener;
import common.Interactable;
import common.MainCanvas;
import common.Sprite;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements CanvasRepaintListener {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int POSX = 400;
    private static final int POSY = 200;

    private final Interactable[] sprites = new Sprite[10];
    private final Interactable background;

    public MainWindow() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POSX, POSY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Bricks");
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Brick();
        }
        background = new Background();

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);

        setVisible(true);
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        background.update(canvas, deltaTime);
        for (Interactable sprite : sprites) {
            sprite.update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        background.render(canvas, g);
        for (Interactable sprite : sprites) {
            sprite.render(canvas, g);
        }
    }
}
