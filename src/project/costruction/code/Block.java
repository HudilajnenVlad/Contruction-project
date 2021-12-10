package project.costruction.code;

import java.awt.*;

public class Block {
    private int x, y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    void paint(Graphics g, int color) {
        g.setColor(new Color(color));
        g.drawRoundRect(x * Gameplay.BLOCK_SIZE + 1, y * Gameplay.BLOCK_SIZE + 1, Gameplay.BLOCK_SIZE - 2, Gameplay.BLOCK_SIZE - 2, Gameplay.ARC_RADIUS, Gameplay.ARC_RADIUS);
    }
}
