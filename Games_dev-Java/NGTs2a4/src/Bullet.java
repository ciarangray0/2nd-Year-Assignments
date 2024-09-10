import java.awt.*;
public class Bullet extends Sprite2D{
    private static final int bulletSpeed = 5;
    public Bullet(Image i) {
        super(i);
        xSpeed = 2;
    }

    public void move() {
        y-= bulletSpeed;
    }
    public double getY() {
        return y;
    }

    public void paint(Graphics g) {
        g.drawImage(image1, (int) x, (int) y, null);
    }
}
