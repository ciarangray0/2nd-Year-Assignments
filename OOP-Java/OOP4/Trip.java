
public class Trip
{
    //class fields
    String busCompany;
    String startLocation;
    String destination;
    String depDate;
    String depTime;
    String arrTime;
    String arrDate;
    double fare;
    int numSeats;
    int tripID;

    public Trip(String company, String sL, String des, String dD, String dT, String aD, String aT, double f, int numseats, int id)
    {
        //class constructors
        this.busCompany = company;
        this.startLocation = sL;
        this.destination = des;
        this.depDate = dD;
        this.depTime = dT;
        this.arrTime = aT;
        this.arrDate = aD;
        this.fare = f;
        this.numSeats = numseats;
        this.tripID = id;
    }
    //getter methods
    public String getCompany() {
        return busCompany;
    }
    
        public String getSLocation (){
        return startLocation;
    }
    
        public String getDestination (){
        return destination;
    }
    
        public String getDDate (){
        return depDate;
    }
    
        public String getDTime (){
        return depTime;
    }
    
        public String getADate (){
        return arrDate;
    }
    
        public String getATime (){
        return arrTime;
    }
    
        public double getFare (){
        return fare;
    }
    
    public int getNumSeats() {
        return numSeats;
    }
    
    public int getID() {
        return tripID;
    }
    //toString method that returns the trip details
    @Override
    public String toString() {
        String tripdetails = "\nTrip: " + busCompany + ": " + tripID + ":\n" + startLocation + " -> " + destination + "\nLeaves On: " + depDate + " -At: " + depTime + " \nArrives on: " + arrDate + " -At: " + arrTime + "\nFare: " + fare + "\nNumber of available seats: " + numSeats;
        return tripdetails;
    }
    
}
