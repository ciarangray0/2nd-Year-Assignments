import java.awt.*;
import java.util.Random;
import javax.swing.*;
public class Spaceship extends Sprite2D{

    public Spaceship (Image i) {
        //set the raster image
        super(i);
        xSpeed = 2;
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
    //to displayn the images
    public void paint(Graphics g) {
        g.drawImage(image1, (int) x, (int) y, null);
    }
}
