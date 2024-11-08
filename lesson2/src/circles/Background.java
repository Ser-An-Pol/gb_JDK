package circles;

import common.Interactable;
import common.MainCanvas;

import java.awt.*;

public class Background implements Interactable {
    private float time = 0;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE * (1f + (float) Math.sin(2f*time)));
        int green = Math.round(AMPLITUDE * (1f + (float) Math.sin(3f*time)));
        int blue = Math.round(AMPLITUDE * (1f + (float) Math.sin(time)));
        color = new Color(red,green, blue);
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }
}
