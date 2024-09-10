import java.awt.*;
import java.util.Random;
import javax.swing.*;
public class Alien extends Sprite2D{
    public Alien (Image i, Image i2) {
        //set the raster image
        super(i, i2);
        xSpeed = 2;
        this.image1 = i;
        this.image2 = i2;
    }
    public void moveEnemy() {
        //to move it along the x-axis only, the same as the spaceship
        x += xSpeed;
    }
    public boolean shouldMoveDown() {
        //if the aliens hit the left or right side of the window, returns true, used in the InvadersApplication move() function
        if (isAlive) {
            if (x <= 0 || x >= InvadersApplication.WindowSize.width - image1.getWidth(null)) {
                return true;
            }
        }
        return false;
    }
    public double getImgWidth() {
        return image1.getWidth(null);
    }
    public double getImgHeight() {
        return image1.getHeight(null);
    }
    public void moveEnemyDown() {
        //if this method is called when the aliens hit the side of the window, the y-coordinates are increased, moving them down the screen, and the direction they're moving on the x-axis is reversed
        y += 50;
        xSpeed *= -1;
    }
        public void paint(Graphics g) {
        //to alternate between the two alien images
            framesDrawn++;
            if (framesDrawn % 100 < 50) {
                g.drawImage(image1, (int)x, (int)y, null);
            } else {
                g.drawImage(image2, (int)x, (int)y, null);
            }
        }

}
