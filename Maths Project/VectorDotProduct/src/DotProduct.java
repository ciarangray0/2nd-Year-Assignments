import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DotProduct extends JPanel implements KeyListener {

    //constants for screen size, player and enemy size, and speed
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 20;
    private static final int ENEMY_SIZE = 20;
    private static final int PLAYER_SPEED = 3;
    private static final int ENEMY_SPEED = 2;

    //field of view in radians (60 degrees)
    private static final double FOV = Math.PI / 3;


    //player and enemy positions stored as vectors
    private Vector2D playerPosition = new Vector2D(WIDTH / 2, HEIGHT / 2);
    private Vector2D enemyPosition = new Vector2D(100, 100);

    //constructor for the game panel
    public DotProduct() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.white);
        setFocusable(true);
        addKeyListener(this);

        //timer that updates game logic and updates graphics(repaints)
        Timer timer = new Timer(1000 / 60, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    //method to update game state
    private void update() {
        //move the player based on keyboard input
        if (KeyInput.left) playerPosition.setX(playerPosition.getX() - PLAYER_SPEED);
        if (KeyInput.right) playerPosition.setX(playerPosition.getX() + PLAYER_SPEED);
        if (KeyInput.up) playerPosition.setY(playerPosition.getY() - PLAYER_SPEED);
        if (KeyInput.down) playerPosition.setY(playerPosition.getY() + PLAYER_SPEED);

        //calculate vector from enemy to player
        Vector2D playerToEnemy = playerPosition.subtract(enemyPosition);

        // clculate dot product between playerToEnemy vector and enemy's forward direction vector
        double dotProduct = playerToEnemy.normalize().dotProduct(new Vector2D(1, 0)); // Assuming enemy's forward direction is along x-axis

        //check if dot product of enemy's forward vector and player-to-enemy vector is bigger than the enemy's field of view
        if (Math.abs(dotProduct) >= Math.cos(FOV / 2)) {
            //here the enemy moves towards the  player if the conditions are met
            Vector2D enemyDirection = playerToEnemy.normalize();
            enemyPosition = enemyPosition.add(enemyDirection.scale(ENEMY_SPEED));
        }
    }

    //paint method to draw the squares
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw player
        g.setColor(Color.blue);
        g.fillRect((int) playerPosition.getX() - PLAYER_SIZE / 2, (int) playerPosition.getY() - PLAYER_SIZE / 2, PLAYER_SIZE, PLAYER_SIZE);

        // Draw enemy
        g.setColor(Color.red);
        g.fillRect((int) enemyPosition.getX() - ENEMY_SIZE / 2, (int) enemyPosition.getY() - ENEMY_SIZE / 2, ENEMY_SIZE, ENEMY_SIZE);
    }

    //methods for handling keyboard input
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) KeyInput.left = true;
        if (key == KeyEvent.VK_RIGHT) KeyInput.right = true;
        if (key == KeyEvent.VK_UP) KeyInput.up = true;
        if (key == KeyEvent.VK_DOWN) KeyInput.down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) KeyInput.left = false;
        if (key == KeyEvent.VK_RIGHT) KeyInput.right = false;
        if (key == KeyEvent.VK_UP) KeyInput.up = false;
        if (key == KeyEvent.VK_DOWN) KeyInput.down = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    //main method to start the demonstration
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Stealth Game");
                DotProduct game = new DotProduct();
                frame.add(game);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

// Class to handle keyboard input
class KeyInput {
    static boolean left = false;
    static boolean right = false;
    static boolean up = false;
    static boolean down = false;
}
