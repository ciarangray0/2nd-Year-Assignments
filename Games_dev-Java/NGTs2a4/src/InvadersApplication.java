import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {
public static final Dimension WindowSize = new Dimension(1000, 800);
private static final int NUMALIENS = 30;
//boolean to indicate whether the game is in the menu or not
private static boolean isGameInProgress = false;
private static int aliensKilled = 0;
//current level
private static int level = 1;
ImageIcon bulletImg = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/bullet.png");
private Alien[] AliensArray = new Alien[NUMALIENS];
private Spaceship PlayerShip;
//score(how many aliens are shot)
private static int score = 0;
ArrayList<Bullet> activeBullets = new ArrayList<>();
//boolean flag to indicate when all the alien objects should be moved down by the moveEnemyDown() method
private boolean moveDown = false;
public InvadersApplication() {
        //images for aliens and the spaceship
        ImageIcon ship = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/player_ship.png");
        ImageIcon alien1 = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/alien_ship_1.png");
        ImageIcon alien2 = new ImageIcon("/Users/ciarangray/Downloads/ct255-images/alien_ship_2.png");
        //creatinong the spaceship object
        PlayerShip = new Spaceship(ship.getImage());
        //creating the aliens objects and storing them in the array
        for (int i = 0; i < NUMALIENS; i++ ) {
        AliensArray[i] = new Alien(alien1.getImage(), alien2.getImage());
        //setting the aliens positions as a 5x6 grid, 30(to set the objects away from the windowframe), i%5 (modulo is used to reset the coordinates back after the grid reaches 5 aliens in length, dividing by 5 keeps the grid 6 aliens long), and *70 to space each alien apart
        AliensArray[i].setPosition(30+(i%5)*70, 55+(i/5)*70);
        }
        this.setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;

        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);

        PlayerShip.setPosition((WindowSize.width / 2) - ship.getIconWidth(), WindowSize.height - ship.getIconHeight());

        addKeyListener(this);
        //thread object
        Thread t = new Thread(this);
        t.start();
        }

        public void run() {
        while (true) {
                //repaint has to set outside the if statement to update the menu/program
                repaint();
                //once the game has started the usual run method starts
                if (isGameInProgress) {
                        //using the thread to move the objects and to "move" the spaceship,(spaceship's move function will only work if a keyboard input is detected)
                        for (Alien Alien : AliensArray) {
                                Alien.moveEnemy();
                                //calling a method in Alien class that checks if the aliens are at the border
                                if (Alien.shouldMoveDown()) {
                                        moveDown = true;
                                        //break because i don't need to check the rest, they all move together as a unit
                                        break;
                                }
                        }
                        if (moveDown) {
                                //calling every alien's method to move down the y-axis, so they all move down together
                                for (Alien alien : AliensArray) {
                                        alien.moveEnemyDown();
                                }
                                //reset the boolean flag so it can be re-used the next time the aliens hit the border
                                moveDown = false;
                        }
                        //the playership's move method hasn't changed since last week
                        PlayerShip.movePlayer();
                        //calling the bullet's move methods
                        for (Bullet bullet : activeBullets) {
                                bullet.move();
                        }
                        //to stop the program crashing when the bullet arraylist gets overloaded, we remove any bullets that leave the screen dimensions
                        Iterator<Bullet> iterator = activeBullets.iterator();
                        while (iterator.hasNext()) {
                                Bullet bullet = iterator.next();
                                if (bullet.getY() < 0 || bullet.getY() > WindowSize.height || bullet.getX() < 0 || bullet.getX() > WindowSize.width) {
                                        iterator.remove();
                                }
                        }
                        //calling the  ethod to check for collisions
                        checkCollisions();
                        //once all the aliens are killed, a new level is started
                        if(aliensKilled == 30) {
                                newLevel();
                        }
                        this.repaint();
                        try {
                                Thread.sleep(15);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                }
        }
        public void gameOver() {
        //once an alien collides with the playership, the game is over and we return to the menu
        isGameInProgress = false;
        score = 0;
        level = 1;
        aliensKilled = 0;
        }
        public void newLevel() {
        //for a new level, we increase the level and reset the aliens , we also increase the speed of the aliens
                level += 1;
                        for (int i = 0; i < NUMALIENS; i++ ) {
                                //setting the aliens positions as a 5x6 grid, 30(to set the objects away from the windowframe), i%5 (modulo is used to reset the coordinates back after the grid reaches 5 aliens in length, dividing by 5 keeps the grid 6 aliens long), and *70 to space each alien apart
                                AliensArray[i].setPosition(30+(i%5)*70, 55+(i/5)*70);
                                AliensArray[i].setAliveState(true);
                                AliensArray[i].setXSpeed(level + 1);
                        }
                        aliensKilled = 0;
        }
        public void checkCollisions() {
        //a new arraylist of the bullets we have to remove from the activeBullets list, when the bullets collide with the aliens
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
                for (Alien alien : AliensArray) {
                        //only alive aliens will be counted
                        if(alien.isAlive) {
        for (Bullet bullet : activeBullets) {
                //calls another sprite2D method that checks if 2 objects have collided
                                if (bullet.collides(alien)) {
                                        bulletsToRemove.add(bullet);
                                        alien.setAliveState(false);
                                        score += 10;
                                        aliensKilled += 1;
                                        break;
                                }
                        }
        //same logic used for the bullet and alien collision, except if these 2 objects collide gameover() is called
        if (PlayerShip.collides(alien)) {
                gameOver();
        }
                }
        }
                //when we finish checking for collisions we can remove all the bullets that have collided
        activeBullets.removeAll(bulletsToRemove);
        }

        public void keyPressed(KeyEvent e) {
        //to start the game, any key must be pressed
                isGameInProgress = true;
                if (isGameInProgress) {
                        //if the input is equal to the left or right arrow, signal is sent to allow player to move left or right and the speed is changed from 0 to 5
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                PlayerShip.setDirectionLeft(true);
                                PlayerShip.setXSpeed(5);
                        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                PlayerShip.setDirectionRight(true);
                                PlayerShip.setXSpeed(5);
                        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                //to shoot bullets
                                shootBullets();
                        }
                }
        }
        public void shootBullets() {
        //create nre bullet object
        Bullet bullet = new Bullet(bulletImg.getImage());
        //set its position to the playership's position
        bullet.setPosition(PlayerShip.getX(), PlayerShip.getY());
        //add to arraylist
        activeBullets.add(bullet);
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
                //creating the back buffer to help with flickering
        Image backBuffer = createImage(WindowSize.width, WindowSize.height);
        Graphics bGraphics = backBuffer.getGraphics();
        //normal paint method for the program will be active when the game is in progress
        if (isGameInProgress) {
                bGraphics.setColor(Color.BLACK);
                bGraphics.fillRect(0, 0, WindowSize.width, WindowSize.height);
                //some text at the top of the screen to show the score and level
                bGraphics.setColor(Color.WHITE);
                Font font = new Font("Arial", Font.BOLD, 20);
                bGraphics.setFont(font);
                String scoreText = "Score: " + score + " | Level: " + level;
                bGraphics.drawString(scoreText, (WindowSize.width/2), 45);

                //draw the alien and playership and bullet  graphics on top of the back buffer
                for (Alien Alien : AliensArray) {
                        if (Alien.isAlive()) {
                                Alien.paint(bGraphics);
                        }
                }
                if (PlayerShip.isAlive()) {
                        PlayerShip.paint(bGraphics);
                }
                for (Bullet bullet : activeBullets) {
                        bullet.paint(bGraphics);
                }
                //swap the back buffer and the front buffer
                g.drawImage(backBuffer, 0, 0, this);
        }
        else {
                //this is the menu screen
                                bGraphics.setColor(Color.BLACK);
                                bGraphics.fillRect(0, 0, getWidth(), getHeight());

                                // Set text color to white
                                bGraphics.setColor(Color.WHITE);

                                // Set font for the text
                                Font font = new Font("Arial", Font.BOLD, 24);
                                bGraphics.setFont(font);

                                // Draw text at the center of the screen
                                String text = "Space Invaders \nPress any button to start";

                                // Split text by newline character to draw each line separately
                                String[] lines = text.split("\n");
                                FontMetrics metrics = bGraphics.getFontMetrics(font);
                                int lineHeight = metrics.getHeight();
                                int y = (WindowSize.height - lines.length * lineHeight) / 2;

                                for (String line : lines) {
                                        int x = (WindowSize.width - metrics.stringWidth(line)) / 2;
                                        bGraphics.drawString(line, x, y);
                                        y += lineHeight;
                                }
                g.drawImage(backBuffer, 0, 0, this);
                        }
        }

        public static void main(String[] args) {
        InvadersApplication game = new InvadersApplication();
        }
        }