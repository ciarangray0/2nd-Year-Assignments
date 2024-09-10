
/**
 * ciaran gray
 */
public class Address
{
    private String street;
    private String city;
    private String zip;
    private String country;

    public Address(String l1, String l2, String l3, String country)
    {
        this.street = l1;
        this.city = l2;
        this.zip = l3;
        this.country = country;
    }
    //getter and setter methods
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String City) {
        this.city = city;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    // String that contains all the address details together
    public String getAddress()
    {
        return "\n" + street + "\n" + city + "\n" + zip + "\n" + country;
    }
}
