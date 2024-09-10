import java.awt.*;
import javax.swing.*;
public class MovingSquaresApplication extends JFrame implements Runnable {

    private static final Dimension WindowSize = new Dimension(600, 6000);
    private static final int NUMGAMEOBJECTS = 30;
    private GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];

    public MovingSquaresApplication() {
        //populating the gameObjectsArray with gameObject objects
        for (int i = 0; i < NUMGAMEOBJECTS; i++ ) {
            GameObjectsArray[i] = new GameObject();
        }
        //template for window from week 1 assignment
        this.setTitle("Week 2 Assignment.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;

        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        //thread object
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        //while(true) so as that the application runs endlessly until it is closed
        while (true) {
            for (GameObject GameObject : GameObjectsArray) {
                //loop calls every square's move method
                GameObject.move();
                //thread runs every 5 milliseconds
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //repaint is called to recall the paint method to repaint the squares after their coordinates have been moved
            this.repaint();
        }
    }

    public void paint (Graphics g) {
        //creates a new blank window so as that the squares actually look like they are moving rather than new squares being drawn beside existing squares
        g.clearRect(0, 0, WindowSize.width, WindowSize.height);

        for(GameObject GameObject : GameObjectsArray) {
            //loop calls each objects paint method to generate a color
            GameObject.paint(g);
        }
    }

    public static void main(String args[]) {
        MovingSquaresApplication x = new MovingSquaresApplication();
    }
}
