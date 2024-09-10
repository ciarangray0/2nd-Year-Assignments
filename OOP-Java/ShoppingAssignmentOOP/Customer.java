
/**
 * ciaran gray
 */
public class Customer
{
    private String fname;
    private String lname;
    private String email;
    private long custID;


    public Customer(String fname, String lname, String email, long custID)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.custID = custID;
    }
    //getter methods for name, iD, and email
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return fname + " " + lname;
    }
    
    public long getID() {
        return custID;
    }
    
}
