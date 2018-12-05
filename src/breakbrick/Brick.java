package breakbrick;

import javax.swing.ImageIcon;

public class Brick extends Dedic {

    private boolean destroyed;
    public int a;

    public Brick(int x, int y,int a) {

        initBrick(x, y,a);
    }
    
    private void initBrick(int x, int y,int a) {
        
        this.x = x;
        this.y = y;
        this.a = a;
        if(a==1){
        destroyed = false;}
        else  destroyed = true;

        loadImage();
        getImageDimensions();
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/resources/bric.png");
        image = ii.getImage();        
    }

    public boolean isDestroyed() {
        
        return destroyed;
    }

    public void setDestroyed(boolean val) {
        
        destroyed = val;
    }
}
