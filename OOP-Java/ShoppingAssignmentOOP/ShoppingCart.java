import java.util.List;
import java.util.ArrayList;

/**
 * ciaran gray
 */
public class ShoppingCart
{
    public ArrayList<Item> cartItems; //array that holds all the shopping cart items
    private boolean isLocked;
    private long cartID;
    private Customer customer;
    private float totalPrice;

    public ShoppingCart(Customer customer)
    {
        this.cartItems = new ArrayList<>();
        this.isLocked = false;
        this.cartID = customer.getID() + 1; //the cart id is linked to the customer ID
        this.totalPrice = totalPrice;
    }
    //getter and setter methods here
    
    public long getcartID() {
        return cartID;
    }
    
    public float getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice() {
        this.totalPrice = totalPrice;
    }
    //if the cart isn't locked, it adds all the items that are passed through to the cart array and adds up the total price of the cart by accessing the getprice method in the item class
    public void addToCart(Item item) {
        if (isLocked == false) {
            cartItems.add(item);
            totalPrice += item.getPrice();
        }
        else {
            System.out.println("Shopping Cart is Closed.");
        }
    }
    //works the same as the addToCart method but invertedly
        public void removeFromCart(Item item) {
        if (isLocked == false) {
            cartItems.remove(item);
            totalPrice -= item.getPrice();
        } else {
            System.out.println("Shopping Cart is Closed.");
        }
    }
    //locks cart by setting the boolean thats required to be false to perform actions on the cart to true
    public void lockCart() {
        isLocked = true;
    }
    //clears the cart array and sets the total price to 0, also unlocks the cart
    public void clearCart() {
            cartItems.clear();
            totalPrice = 0;
            isLocked = false;
    }
    //prints the contents of the cart by looping through the items in the cart and calling the toString method in the items class for each item in the cart
        public void displayCart() {
        System.out.println("Items in shopping cart ID " + cartID + ": ");
        for (Item item : cartItems) {
            System.out.println(item.toString());
        }
        System.out.println("\nTotal cart price: " + totalPrice);
    }
    
}
