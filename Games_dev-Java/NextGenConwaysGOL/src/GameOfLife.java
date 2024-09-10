import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;

public class GameOfLife extends JFrame implements Runnable, MouseListener, MouseMotionListener {
    private boolean[][][] cells = new boolean[width / size][height / size][2];
    private static int size = 20;
    //game state boolean
    private static boolean gameInProgress = false;
    //start and random buttons
    private static Rectangle start = new Rectangle(30, 30, 80, 40);
    private static Rectangle random = new Rectangle( 120, 30, 80, 40);
    private static Rectangle load = new Rectangle(600, 30, 80, 40);
    private static Rectangle save = new Rectangle( 690, 30, 80, 40);
    private static int width = 800;
    private static int height = 800;
    //buffer objects
    private BufferStrategy strategy;
    private Graphics offscreenBuffer;

    public GameOfLife() {
        //setting the window
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screensize.width, screensize.height, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setVisible(true);

        //mouse listener
        addMouseListener(this);
        addMouseMotionListener(this);

        //initialize game state
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 40; y++) {
            cells[x][y][0]=false; }
        }
        //start the thread
        Thread thread = new Thread(this);
        thread.start();
    }
    //method to instaniate the buffer objects, was encountering errors when instansiating in the constructor and this method fixed it
    public void addNotify() {
        super.addNotify();
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenBuffer = strategy.getDrawGraphics();
    }

    public void run() {
        while (true) {
            repaint(); //call another repaint();
            if(gameInProgress) {
                //loop through each cell
                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j < cells[i].length; j++) {
                        //if the cell is dead, apply the game rules to it
                        if (!cells[i][j][0]) { //here in checking cells[i][j][0], the [0] is the part of the 3D array that will be printing to the screen
                            int liveNeighbours = checkAliveRules(cells, i, j); //method that checks alive neighbours
                            if (liveNeighbours == 3) {
                                cells[i][j][1] = true; // i'm altering the [1] part of the 3D array to avoid errors as i am simultaniously painting the [0] part
                            } else {
                                cells[i][j][1] = false;
                            }
                        }
                        if (cells[i][j][0]) { //same thing here, if the cell is alive we apply the game rules to it here
                            int liveNeighbours = checkAliveRules(cells, i, j);
                            if (liveNeighbours < 2 || liveNeighbours > 3) {
                                cells[i][j][1] = false;
                            } else {
                                cells[i][j][1] = true;
                            }
                        }
                    }
                }
                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j < cells[i].length; j++) {
                        cells[i][j][0] =cells[i][j][1]; //update the cells array[0] with the part[1], which we applied conways rules to
                    }
                }
                repaint();
            }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){

        int x = e.getX() / size;
        int y = e.getY() / size;
        //when the cell is clicked, we reverse it's true or false state
        setSquareState(x, y);
    }
    //reverses the cell's boolean value
    public void setSquareState(int x, int y) {
        if (cells[x][y][0] == false) {
            cells[x][y][0] = true;
        } else if (cells[x][y][0] == true) {
            cells[x][y][0] = false;
        }
    }

    public int checkAliveRules(boolean[][][] cells, int x, int y) {
        int alivecells = 0; //count the alive cells
        int border = cells.length - 1; //"border", that just stands for the cells at the right edge of the screen, index 39 of the x-axis
        //this loop here will check every cell at x-1, x, x+1, y-1, y, y+1, essentially the 8 cells surrounding our chosen cell
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                int wrappedX = (i + border + 1) % (border + 1);//if i goes beyond the border of the grid, modulo operator is used to wrap it around to the other side
                int wrappedY = (j + border + 1) % (border + 1); //if j goes beyond the border of the grid, modulo operator is used to wrap it around to the other side
                if ((i != x || j != y) && cells[wrappedX][wrappedY][0]) { //the first part of the check ensures that we are not checking the current cell itself, the second part checks if the neighbouring cell is alive or not
                    alivecells++;//we update this if the neighbouring cell is alive AND if we're not checking the main cell
                }
            }
        }
        return alivecells;
    }

    public void randomSquares() {
        Random random = new Random();
        //iterate through the cells and assign it a random true or false boolean value
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                    cells[i][j][0] = random.nextBoolean();
                }
            }
    }
    //function that converts the current true or false state of the cells to an output string containing 1 or 0
    public String writeGame() {
        String gameState = "";
        for(int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(cells[i][j][0]) {
                    gameState+=1;
                }
                if(!cells[i][j][0]) {
                    gameState+=0;
                }
            }
            }
        return gameState;
    }
    public void saveGame() {
        //saves the game state to a txt file
        String savedGame = writeGame();
        String filename = "/Users/ciarangray/Desktop/Games_dev-Java/NextGenConwaysGOL/GameOfLifeLoad.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(savedGame);
            writer.close();
        }
        catch (IOException e) { }
    }
    public void loadGame() {
        String line = "";
        //get tye txt file where the game is saved
        String filename = "/Users/ciarangray/Desktop/Games_dev-Java/NextGenConwaysGOL/GameOfLifeLoad.txt";
                try {
                    //reader object
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    //reads a line until there are no lines left
                    while ((line = reader.readLine()) != null) {
                        for (int k = 0; k < line.length() - 1; k++) {
                            //if the char is 1, it sets the current cell to true
                            if (line.charAt(k) == '1') {
                                cells[k / cells.length][k % cells[0].length][1] = true;
                            }
                            //if the char is 0, it sets the current cell to false
                            if (line.charAt(k) == '0') {
                                cells[k / cells.length][k % cells[0].length][1] = false;
                            }
                        }
                    }
                    reader.close();
                }catch (IOException e) { }
                //change the cells we are painting to the cells that we changed their state
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j][0] = cells[i][j][1];
            }
            }
    }
    public void mousePressed(MouseEvent e) {
        //these are the x and y coordinates that will be used to check if the buttons are clicked
        int clickedX = e.getX();
        int clickedY = e.getY();
        //if the coordinated clicked are within the start buttons dimensions, the game starts
        if(!gameInProgress) {
            if (clickedX >= start.x && clickedX <= (start.x + start.width) && clickedY >= start.y && clickedY <= (start.y + start.height)) {
                gameInProgress = true;
                return;
            }
        }
        //this is the stop button to stop the rules
        if(gameInProgress) {
            if(clickedX >= start.x && clickedX <= (start.x + start.width) && clickedY >= start.y && clickedY <= (start.y + start.height)) {
                gameInProgress = false;
                return;
            }
        }
        //if the coordinated clicked are within the random buttons dimensions, the cells are randomized using the randomSquares() method
        if(clickedX >= random.x && clickedX <= (random.x + random.width) && clickedY >= random.y && clickedY <= (random.y + random.height)) {
            randomSquares();
            return;
        }
        //to load a previously saved state
        if(clickedX >= load.x && clickedX <= (load.x + load.width) && clickedY >= load.y && clickedY <= (load.y + load.height)) {
            loadGame();
            return;
        }
        //to save a state
        if(clickedX >= start.x && clickedX <= (save.x + save.width) && clickedY >= save.y && clickedY <= (save.y + save.height)) {
            saveGame();
            return;
        }
        //these are the x and y coordinates for setting the cells to true
        //when the mouse is clicked, the coordinates are retreved by the getX() and getY() methods, then the coordinates are divided by 20 to get the cell that was clicked
            int x = e.getX() / size;
            int y = e.getY() / size;
            //when the cell is clicked, we reverse it's true or false state
        setSquareState(x, y);

    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void paint(Graphics g) {
        //creating an off-screen image buffer
        offscreenBuffer = strategy.getDrawGraphics();
        //set the background to black
        offscreenBuffer.setColor(Color.BLACK);
        offscreenBuffer.fillRect(0, 0, getWidth(), getHeight());
        //before the game is in progress, we are paintoing the buttons
            //draw start button
            offscreenBuffer.setColor(Color.GREEN);
            offscreenBuffer.fillRect(start.x, start.y, start.width, start.height);
            offscreenBuffer.setColor(Color.WHITE);
            int startTextX = start.x + (start.width - offscreenBuffer.getFontMetrics().stringWidth("Start")) / 2; //setting the starting x-coordinate of the text
            int startTextY = start.y + (start.height + offscreenBuffer.getFontMetrics().getHeight()) / 2; //setting the starting y-coordinate of the text
            if(!gameInProgress) {
                offscreenBuffer.drawString("Start", startTextX, startTextY);
            }
            //draw stop button if game is already in progress
            if(gameInProgress) {
                offscreenBuffer.drawString("Stop", startTextX, startTextY);
            }

            // Draw random button
            offscreenBuffer.setColor(Color.GREEN);
            offscreenBuffer.fillRect(random.x, random.y, random.width, random.height);
            offscreenBuffer.setColor(Color.WHITE);
            int randomTextX = random.x + (random.width - offscreenBuffer.getFontMetrics().stringWidth("Random")) / 2; //setting the starting x-coordinate of the text
            int randomTextY = random.y + (random.height + offscreenBuffer.getFontMetrics().getHeight()) / 2; //setting the starting y-coordinate of the text
            offscreenBuffer.drawString("Random", randomTextX, randomTextY);

            //draw load button
            offscreenBuffer.setColor(Color.GREEN);
            offscreenBuffer.fillRect(load.x, load.y, load.width, load.height);
            offscreenBuffer.setColor(Color.WHITE);
            int loadTextX = load.x + (load.width - offscreenBuffer.getFontMetrics().stringWidth("Load")) / 2; //setting the starting x-coordinate of the text
            int loadTextY = load.y + (load.height + offscreenBuffer.getFontMetrics().getHeight()) / 2; //setting the starting y-coordinate of the text
            offscreenBuffer.drawString("Load", loadTextX, loadTextY);

            //draw save button
            offscreenBuffer.setColor(Color.GREEN);
            offscreenBuffer.fillRect(save.x, save.y, save.width, save.height);
            offscreenBuffer.setColor(Color.WHITE);
            int saveTextX = save.x + (save.width - offscreenBuffer.getFontMetrics().stringWidth("Save")) / 2; //setting the starting x-coordinate of the text
            int saveTextY = save.y + (save.height + offscreenBuffer.getFontMetrics().getHeight()) / 2; //setting the starting y-coordinate of the text
            offscreenBuffer.drawString("Save", saveTextX, saveTextY);

        //create a loop that iterates through each cell and draw the cells that are set to true
        offscreenBuffer.setColor(Color.WHITE);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j][0]) {
                    offscreenBuffer.fillRect(i * size, j * size, size, size);
                }
            }
        }

        //swap the back buffer with the front buffer
        strategy.show();
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
    }
}