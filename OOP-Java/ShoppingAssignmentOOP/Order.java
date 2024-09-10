import java.util.ArrayList;
import java.util.List;
/**
 * ciaran gray
 */
public class Order
{
    // instance variables - replace the example below with your own
    private List<Item> orderItems;
    private float orderTotal;
    private Customer customer;
    private Payment payment;
    private ShoppingCart cart;
    private Email email;
    private boolean paymentConfirmed = false;
    private long orderNum;

    /**
     * Constructor for objects of class Order
     */
    public Order(Customer customer, ShoppingCart cart)
    {
        this.customer = customer;
        this.cart = cart;
        this.orderItems = new ArrayList<>();
        this.payment = payment;
        this.email = email;
        this.orderTotal = orderTotal;
        this.paymentConfirmed = false;
        this.orderNum = (customer.getID() / 10);
    }
    // loops through each item in the shopping cart array and adds the items individually into the order items array and also adds up th total price from the cart to a new variable for the order total, and calls the clear cart function at the end
    public void addToOrder(ShoppingCart cart)
    {
        for (Item item : cart.cartItems) {
            orderItems.add(item);
            orderTotal = cart.getTotalPrice();
        }
        cart.clearCart();
    }

    //displays the order by using the customer class for the customer's name and id and by looping through the items in the order and calling the toString method in the items class for each item in the order
        public void displayOrder() {
        System.out.println("Items in order for customer " + customer.getName() + " ID: " +customer.getID() + ": ");
        for (Item item : orderItems) {
            System.out.println(item.toString());
        }
        System.out.println("\nTotal price = " + orderTotal);
    }
    //summary containing all the details of the order including the order, payment, order number and order total price
    public String orderSummary(Payment payment) {
        return "\nOrder " + orderNum + "\n" + orderItems + "\nTotal Price:" + orderTotal + payment.paymentDetails();
    }
    // method that checks if both the card type and card number are correct, if they are then it sets a boolean to true which allows the email of the order summary to be set
    public void validateOrder(Payment payment) {
        if(payment.validateCardType() != "Invalid Card Type" && payment.validateCardNumber() != "Invalid Card Number") {
            paymentConfirmed = true;
        }
        else {
            paymentConfirmed = false;
        }
    }
    //if the order has been validated and the order actually contains at least 1 item, the oder summary email is sent, or else an email of regret is sent
    public void confirmOrder(Email email) {
        if(paymentConfirmed == true && orderItems.size() != 0) {
            email.emailOrderSummary();
        }
        else {
            email.emailOfRegret();
        }
    }
}
