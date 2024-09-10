
public class Booking
{
    //the fields for the booking class, similar to trip but with bookedSeats and bookingPrice
    int bookedSeats;
    String company;
    int tripNumSeats;
    int bookingTripID;
    double bookingPrice;
    String bstartLocation;
    String bdestination;
    String bdepDate;
    String bdepTime;
    String barrTime;
    String barrDate;
    public Booking(Trip selectedTrip, int selectedSeats)
    {
        //class constructors
        this.bookingTripID = selectedTrip.tripID;
        this.bookedSeats = selectedSeats;
        this.bookingPrice = selectedTrip.fare * bookedSeats;
        this.tripNumSeats = selectedTrip.numSeats;
        this.bstartLocation = selectedTrip.startLocation;
        this.bdestination = selectedTrip.destination;
        this.bdepDate = selectedTrip.depDate;
        this.bdepTime = selectedTrip.depTime;
        this.barrTime = selectedTrip.arrTime;
        this.barrDate = selectedTrip.arrDate;
        this.company = selectedTrip.busCompany;
    }
    //getter methods for fields
    public String getCompany() {
        return company;
    }
    
        public String getSLocation (){
        return bstartLocation;
    }
    
        public String getDestination (){
        return bdestination;
    }
    
        public String getDDate (){
        return bdepDate;
    }
    
        public String getDTime (){
        return bdepTime;
    }
    
        public String getADate (){
        return barrDate;
    }
    
        public String getATime (){
        return barrTime;
    }
    
    public int getBookingTripId() {
        return bookingTripID;
    }
    
    public int getNumBookedSeats() {
        return bookedSeats;
    }
    
    public int getNumTripSeats() {
        return tripNumSeats;
    }
    
    public double getPrice() {
        return bookingPrice;
    }
    //setter method for price
    public void setPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }
    //toString method that returns all the booking details
    @Override
    public String toString() {
        String bookingdetails = "\n*****Booking Confirmed! " + "*****\nTrip: " + company + ": " + bookingTripID + "\nNumber of passengers: " + bookedSeats + "\nTrip route: " + bstartLocation + " -> " + bdestination + "\nLeaves on: " + bdepDate + " - At: " + bdepTime + "\nArrives on: " + barrDate + " -At: " + barrTime + "\nBooking price: " + bookingPrice;
        return bookingdetails;
    }
}
