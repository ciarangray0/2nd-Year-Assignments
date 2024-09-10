
/**
 * Write a description of class Light here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Light
{
    // instance variables - replace the example below with your own
    private String colour;
    private boolean isOn = false;

    /**
     * Constructor for objects of class Light
     */
    public Light(String colour)
    {
        // initialise instance variables
        this.colour = colour;
        this.isOn = false;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean getLight() {
        return isOn;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public boolean turnOnLight() {
        this.isOn = true;
        return isOn;
    }
    
    public boolean turnOffLight() {
        this.isOn = false;
        return isOn;
    }
    
    public void printOut()
    {
        // put your code here
        if(isOn) {
            System.out.println("--" + colour + "--");
        }
        else {
            System.out.println("-- OFF --");
        }
    }
}
