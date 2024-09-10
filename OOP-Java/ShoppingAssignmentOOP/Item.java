
/**
 * ciaran gray
 */
public class Item
{
    // instance variables
    private String name;
    private double price;
    private long itemID;

    public Item(String itemName, double price, long id)
    {
        this.name = itemName;
        this.price = price;
        this.itemID = id;
    }
    //some getter and setter methods
    public void setPrice(double price) {
        this.price = price;
    }

    
    public double getPrice()
    {
        return price;
    }
    //string that contains the item details
    @Override
        public String toString() {
        String iDetails = "Item ID: " + itemID + "\t" + name + "\tPrice: " + price;
        return iDetails;
    }
        
}
