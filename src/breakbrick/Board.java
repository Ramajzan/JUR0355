package breakbrick;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;


public class Board extends JPanel implements Date {
    private JFrame pl;
    private int SCORE;
    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    private boolean inGame = true;
    private String level;

    public Board(String lvl,JFrame menu) {
        this.level=lvl;
        pl=menu;
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        bricks = new Brick[N_OF_BRICKS];
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }


    public void addNotify(){

        super.addNotify();
        gameInit();
    }

    private void gameInit(){
        ball = new Ball();
        paddle = new Paddle();

        Scanner sc=null,scc=null;
        try{
            sc = new Scanner(new File(level));
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            scc = new Scanner(new File("Resources/Score/score.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
        SCORE=scc.nextInt();
        scc.close();
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(j * 40 + 30, i * 10 + 50,sc.nextInt());
                k++;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            
            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics2D g2d) {
        g2d.drawString("Score: "+SCORE, 5, 15);
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < N_OF_BRICKS; i++) {
            if(bricks[i].a==1){
            if (!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }}
        }
    }
    
    private void gameFinished(Graphics2D g2d) {


        File f=new File("Resources/Score/score.txt");
        if(f.exists()){
            f.delete();
        }
        String help;
        help=Integer.toString(SCORE);
        FileWriter out=null;
        try{
            out = new FileWriter(f);
            out.write(help);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }


        pl.setVisible(true);

    }

    private class TAdapter extends KeyAdapter {


        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }


        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {


        public void run() {

            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        }
    }

    private void stopGame() {

        inGame = false;
        timer.cancel();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > Date.BOTTOM_EDGE) {
            SCORE=0;
            stopGame();
        }

        for (int i = 0, j = 0; i < N_OF_BRICKS; i++) {
            
            if (bricks[i].isDestroyed()) {
                j++;
            }
            
            if (j == N_OF_BRICKS) {

                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0; i < N_OF_BRICKS; i++) {
            
            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }
                    SCORE=SCORE+100;
                    bricks[i].setDestroyed(true);
                }
            }
        }
    }
}
