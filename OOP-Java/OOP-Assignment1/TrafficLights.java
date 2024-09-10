
/**
 * Write a description of class TrafficLights here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TrafficLights
{
    // instance variables - replace the example below with your own
    private Light green;
    private Light amber;
    private Light red;

    /**
     * Constructor for objects of class TrafficLights
     */
    public TrafficLights()
    {
        // initialise instance variables
        green = new Light("Green");
        amber = new Light("Amber");
        red = new Light("Red");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void go()
    {
        // put your code here
        green.turnOnLight();
        amber.turnOffLight();
        red.turnOffLight();
    }
    public void prepareToStop() {
        green.turnOffLight();
        amber.turnOnLight();
        red.turnOffLight();
    }
    public void stop() {
        green.turnOffLight();
        amber.turnOffLight();
        red.turnOnLight();
    }
    public void printState() {
        green.printOut();
        amber.printOut();
        red.printOut();
        System.out.println("\n");
    }
}
