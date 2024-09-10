import java.awt.*;
import java.util.Random;
import javax.swing.*;
public class Sprite2D {
    private double x, y;
    private double xSpeed =0;
    private Image myImage;
    //booleans that indicate whether the spaceship should move left or right on the x-axis ( xSpeed is -= if left is true and += if right is true)
    private boolean moveRight;
    private boolean moveLeft;

    public Sprite2D (Image i) {
        //set the raster image
        this.myImage = i;
    }
    public void moveEnemy() {
        Random random = new Random();
        //to move the enemy, rabndom booleans are generated to indicate whether to move up, down, left, or right
        boolean randomXBoolean = random.nextBoolean();
        boolean randomYBoolean = random.nextBoolean();
        if (randomXBoolean) {
            x += 5;
        }
        else if (!randomXBoolean){
            x -= 5;
        }
        if (randomYBoolean) {
            y += 5;
        }
        else if (!randomYBoolean){
            y -= 5;
        }
    }
    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }
    public void setDirectionRight(boolean right) {
        moveRight = right;
    }
    public void setDirectionLeft(boolean left) {
        moveLeft = left;
    }
    public void movePlayer() {
        //right will move positively on the x-axis
        if (moveRight) {
            x += xSpeed;
        }
        //left will move negatively on the x-axis
        else if (moveLeft) {
            x -= xSpeed;
        }
    }
    public void setXSpeed(double dx) {
        xSpeed = dx;
    }
    //to displayn the images
    public void paint(Graphics g) {
        g.drawImage(myImage, (int) x, (int) y, null);
    }
}
