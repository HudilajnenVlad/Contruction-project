package project.costruction.code;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static project.costruction.code.Gameplay.*;

class Canvas extends JPanel { // my canvas for painting
    private final Random random = new Random();
    private final int numOfImg = random.nextInt(32)+1;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon img = new ImageIcon("backgroundImages/Фон "+numOfImg+".jpeg"); //full path of image
        Image img2 = img.getImage().getScaledInstance(this.getWidth(), this.getHeight(),1);
        ImageIcon img3 = new ImageIcon(img2);
        g.drawImage(img3.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        for (int x = 0; x < FIELD_WIDTH; x++)
            for (int y = 0; y < Gameplay.FIELD_HEIGHT; y++) {
                if (x < FIELD_WIDTH - 1 && y < FIELD_HEIGHT - 1) {
                    g.setColor(Color.lightGray);
                    g.drawLine((x+1)*BLOCK_SIZE-2, (y+1)*BLOCK_SIZE, (x+1)*BLOCK_SIZE+2, (y+1)*BLOCK_SIZE);
                    g.drawLine((x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE-2, (x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE+2);
                }
                if (mine[y][x] > 0) {
                    g.setColor(new Color(mine[y][x]));
                    g.fill3DRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1, BLOCK_SIZE-1, BLOCK_SIZE-1, true);
                }
            }
        if (gameOver) {
            g.setColor(Color.white);
            for (int y = 0; y < GAME_OVER_MSG.length; y++)
                for (int x = 0; x < GAME_OVER_MSG[y].length; x++)
                    if (GAME_OVER_MSG[y][x] == 1) g.fill3DRect(x*11+18, y*11+160, 10, 10, true);
        } else
            figure.paint(g);
    }
}
