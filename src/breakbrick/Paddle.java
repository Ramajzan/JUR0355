package breakbrick;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Dedic implements Date {

    private int dx;

    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
    }    

    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - imageWidth) {
            x = WIDTH - imageWidth;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
    }
}
