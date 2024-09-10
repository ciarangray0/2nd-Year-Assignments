import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public clasInvadersApplication extends JFrame implements Runnable, KeyListener {
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMALIENS = 30;
    private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
    private Sprite2D PlayerShip;
    public InvadersApplication() {
        Random random = new Random();
        //images for aliens and the spaceship
        ImageIcon img1 = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/player_ship.png");
        ImageIcon img2 = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/alien_ship_1.png");
        //creating the spaceship object
        PlayerShip = new Sprite2D(img1.getImage());
        //creating the aliens objects and storing them in the array
        for (int i = 0; i < NUMALIENS; i++ ) {
            AliensArray[i] = new Sprite2D(img2.getImage());
            //setting the aliens positions as a random position in the screen, bounds are as such as i found through trial and error these bounds spawn the aliens in the middle of the screen
            AliensArray[i].setPosition(random.nextDouble(60, WindowSize.width/2), random.nextDouble(10, WindowSize.height/2 - 30));
        }
        this.setTitle("Week 3 Assignment.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;

        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);

        PlayerShip.setPosition((WindowSize.width / 2) - img1.getIconWidth(), WindowSize.height - img1.getIconHeight());

        addKeyListener(this);
        //thread object
        Thread t = new Thread(this);
        t.start();
    }
    public void run() {
        while (true) {
            //using the thread to move the objects and to "move" the spaceship,(spaceship's move function will only work if a keyboard input is detected)
            for (Sprite2D Sprite2D : AliensArray) {
                Sprite2D.moveEnemy();
            }
            PlayerShip.movePlayer();
            this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
    public void keyPressed(KeyEvent e) {
        //if the input is equal to the left or right arrow, signal is sent to allow player to move left or right and the speed is changed from 0 to 5
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            PlayerShip.setDirectionLeft(true);
            PlayerShip.setXSpeed(5);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            PlayerShip.setDirectionRight(true);
            PlayerShip.setXSpeed(5);
        }
    }
    //works the same as the method above, but if the left or right arrow key is released, it sets the boolean for left or right movement to false and speed to 0
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            PlayerShip.setDirectionLeft(false);
            PlayerShip.setXSpeed(0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            PlayerShip.setDirectionRight(false);
            PlayerShip.setXSpeed(0);
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void paint(Graphics g) {
        //black background colour
        g.setColor(Color.BLACK);
        //window is repainted and every sprite 2D object repainted with the new coordinates to give the impression they are moving
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);
        for (Sprite2D Sprite2D : AliensArray) {
            Sprite2D.paint(g);
        }
        PlayerShip.paint(g);
    }
    public static void main(String[] args) {
        InvadersApplication game = new InvadersApplication();
    }
}
