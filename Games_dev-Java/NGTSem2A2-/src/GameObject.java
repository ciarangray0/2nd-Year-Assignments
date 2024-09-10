import java.awt.*;
import java.awt.Color;
import java.util.Random;
public class GameObject {
    private double x, y;
    private Color c;

    public GameObject()  {
        //random color for each square
        this.c = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        //random x and y coordinate
        this.x = (double)(Math.random()*600);
        this.y = (double)(Math.random()*600);
    }

    public void move() {
        Random random = new Random();
        //random booleans generated for x and y coordinates
        //true means move forward on x/y axis, false means to move back
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
    public void paint(Graphics g) {
        //draws the squares with their random color
        g.setColor(c);
        g.fillRect((int) x, (int) y, 50, 50);
    }

}
