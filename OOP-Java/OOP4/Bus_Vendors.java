import java.util.ArrayList;
//Bus_Vendors is an abstract class that contains all the methods for the other subclasses
public abstract class Bus_Vendors
{   //array trips that each subclass inherits to store their trips
    
    public ArrayList<Trip> trips = new ArrayList<>();
    
    //addTrip() method to add the trip objects to the trips array
    public void addTrip(Trip trip) {
        trips.add(trip);
    }
    //getTrip() gets a hold of a specific trip object by comparing the trip id passed in to the tripID of all the trips in the array, and returns the desired trip
    public Trip getTrip(int id) {
        int selectedTripID = id;
        for (Trip trip : trips) {
            if(trip.tripID == selectedTripID) {
                return trip;
            }
    }
    //if trip doesn't exist error message is printed and null is returned
    System.out.println("\nTrip " + selectedTripID + " doesn't exist.");
    return null;
    }
    //to print all the available trips a bus has, loops through each trip object in the array and calls that object's toString() method in the trip class
    public void getAllAvailableTrips() {
        for (Trip trips : trips) {
            System.out.println(trips.toString());
        }
    }
    
    //makeBooking() returns a boolean value based on if the booking is made or not,by checking if the requested number of seats to book exceeds the ammount of available seats and if the requested trip exists
    public boolean makeBooking(Booking booking) {
        if(booking.bookedSeats > booking.tripNumSeats) {
            System.out.println("\nThe ammount of seats you have chosen for trip " + booking.bookingTripID + " exceeds the ammount of available seats");
            return false;
        }
        else {
            Trip requestedTrip = getTrip(booking.bookingTripID);
            if(requestedTrip == null) {
                return false;
            }
            //if the booking is eligable, the method returns true and updates the new number of available seats for the trip
            requestedTrip.numSeats = requestedTrip.numSeats - booking.bookedSeats;
            return true;
        }
    }
}
