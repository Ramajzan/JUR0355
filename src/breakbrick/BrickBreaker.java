package breakbrick;

import javax.swing.JFrame;


public class BrickBreaker extends JFrame {
    public int j=0;
    private JFrame pl;
    private String[] level={"Resources/Levels/level1.txt","Resources/Levels/level2.txt"
            ,"Resources/Levels/level3.txt","Resources/Levels/level4.txt","Resources/Levels/level5.txt"};
    public BrickBreaker(int h,JFrame menu) {
        j=h;
        pl=menu;
        initUI();
    }

    private void initUI() {
        setTitle("Brick breaker");


        setSize(Date.WIDTH, Date.HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new Board(level[j],pl));
        pl.setVisible(false);
    }


}

