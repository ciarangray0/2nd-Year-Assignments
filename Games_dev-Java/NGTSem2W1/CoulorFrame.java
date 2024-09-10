import java.awt.Color;
import java.awt.*;
import javax.swing.*;
public class CoulorFrame extends JFrame
{
    private static final Dimension WindowSize = new Dimension(600,600);
    
    public CoulorFrame() {
        this.setTitle("Week 1 Assignment.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        int size = 50;
        int border = 15;
        for(int i = 0; i < getWidth(); i += size + border) {
        for(int k = 0; k < getHeight(); k += size + border) {
        g.setColor(getRandomColor());
        g.fillRect((border + i), (border + k), size, size);
    }
    }
    }
    
    public Color getRandomColor() {
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        
        return new Color(red, green, blue);
    }
    
    public static void main(String [ ] args) {
        CoulorFrame w = new CoulorFrame();
}
    
}
