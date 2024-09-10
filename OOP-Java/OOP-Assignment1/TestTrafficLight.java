
/**
 * Write a description of class TestTrafficLight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestTrafficLight
{
    public static void main(String[] args) {
        TrafficLights tLightObject = new TrafficLights();
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("------------\n--Run " + i + "--");
            tLightObject.go();
            tLightObject.printState();
            tLightObject.prepareToStop();
            tLightObject.printState();
            tLightObject.stop();
            tLightObject.printState();
            System.out.println("------------\n");
        }
    }
}
