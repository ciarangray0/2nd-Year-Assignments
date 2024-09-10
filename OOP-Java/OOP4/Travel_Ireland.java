
public class Travel_Ireland
{
    public static void main(String[] args)
    {
        //testing class
        //create 3 new bus objects
        BusEireann be = new BusEireann();
        CityLink cl = new CityLink();
        GoBus gb = new GoBus();
        
        //create 3 new trips for each bus objects
        Trip be1 = new Trip("Bus Eireann", "Cork", "Limerick", "01-12-2023", "13:00", "01-12-2023", "15:05", 5.50, 30, 1);
        Trip cl1 = new Trip("City Link", "Dublin", "Kildare", "22-11-2023", "11:00", "22-11-2023", "11:45", 4.40, 50, 2);
        Trip gb1 = new Trip("Go Bus", "London", "Dublin", "19-12-2023", "18:00", "20-12-2023", "5:00", 13.50, 10, 3);
        Trip be2 = new Trip("Bus Eireann", "Donegal", "Galway", "05-12-2023", "15:00", "05-12-2023", "20:30", 8.30, 25, 4);
        Trip cl2 = new Trip("City Link", "Tipperary", "Waterford", "04-01-2024", "21:05", "04-01-2024", "23:40", 3.50, 40, 5);
        Trip gb2 = new Trip("Go Bus", "Kildare Town", "Naas", "20-11-2023", "9:00", "20-11-2023", "9:45", 2, 20, 6);
        
        //add the trips to the busses trips arrays using the assTrip() method
        be.addTrip(be1);
        cl.addTrip(cl1);
        gb.addTrip(gb1);
        be.addTrip(be2);
        cl.addTrip(cl2);
        gb.addTrip(gb2);
        
        //display all the availablke trips for each bus vendor
        be.getAllAvailableTrips();
        cl.getAllAvailableTrips();
        gb.getAllAvailableTrips();
        
        //specify which trip is being requested to be booked by making a new trip object by using the getTrip() method which searches for the trips that has the matching trip ID 
        Trip selectedTrip = be.getTrip(1);
        Trip selectedTrip2 = cl.getTrip(2);
        Trip selectedTrip3 = gb.getTrip(3);
        
        //create 3 booking objects for each trip using the selectedTrip objects and the ammount of seats wanted as parameters
        Booking booking = new Booking(selectedTrip, 10);
        Booking booking2 = new Booking(selectedTrip2, 1);
        Booking booking3 = new Booking(selectedTrip3, 15);
        
        //the bookings are passed to the makeBooking() method and if theryre eligable they will set the success variables as true
        boolean success = be.makeBooking(booking);
        boolean success2 = cl.makeBooking(booking2);
        boolean success3 = gb.makeBooking(booking3);
        
        //if true, the booking details will print
        if(success) {
            System.out.println(booking);
        }
        else if(!success) {
            System.out.println("\nError, could not confirm booking for trip " + booking.bookingTripID);
        }
        
        if(success2) {
            System.out.println(booking2);
        }
        else if(!success2) {
            System.out.println("\nError, could not confirm booking for trip " + booking2.bookingTripID);
        }
        
        if(success3) {
            System.out.println(booking3);
        }
        else if(!success3) {
            System.out.println("\nError, could not confirm booking for trip " + booking3.bookingTripID);
        }
        
        //printing the newly updated available trips
        be.getAllAvailableTrips();
        cl.getAllAvailableTrips();
        gb.getAllAvailableTrips();
        
    }
}
