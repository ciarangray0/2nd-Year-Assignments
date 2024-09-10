import java.awt.*;
import java.util.Random;
import javax.swing.*;
public class Sprite2D {
    protected double x, y;
    protected double xSpeed = 0;
    protected boolean isAlive = true;
    protected Image image1;
    protected Image image2;
    //booleans that indicate whether the spaceship should move left or right on the x-axis ( xSpeed is -= if left is true and += if right is true)
    protected boolean moveRight;
    protected boolean moveLeft;
    protected int framesDrawn;

    //i used 2 seperate constructors here, 1 that takes 2 images which the alien will use and 1 that takes 1 image which the playership and bullet will use
    public Sprite2D (Image i, Image i2) {
        //set the raster image
        this.image1 = i;
        this.image2 = i2;
        //setting the xSpped here as both Alien and Spaceship are using it now
        this.framesDrawn = 0;
    }
    public Sprite2D(Image i) {
        this.image1 = i;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public boolean collides(Alien alien) {
        //get the coordinates of the bullet/playership
        double objX =  x;
        double objY =  y;

        //get the dimensions of the bullet/playership
        double objWidth = image1.getWidth(null);
        double objHeight = image1.getHeight(null);

        //get the coordinates of the alien
        double alienX = (int) alien.getX();
        double alienY = (int) alien.getY();

        //get the dimensions of the alien
        double alienWidth = alien.getImgWidth();
        double alienHeight = alien.getImgHeight();

        //check if any part of the bullet overlaps with any part of the alien
        boolean xOverlap = objX + objWidth >= alienX && objX <= alienX + alienWidth;
        boolean yOverlap = objY + objHeight >= alienY && objY <= alienY + alienHeight;

        return xOverlap && yOverlap;
    }
    public void setAliveState(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }
    public void setXSpeed(double dx) {
        xSpeed = dx;
    }
    //to display the images
    public void paint(Graphics g) {
        g.drawImage(image1, (int) x, (int) y, null);
    }
}
