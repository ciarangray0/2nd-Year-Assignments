
/**
ciaran gray
 */
public class Email
{
    private Customer customer;
    private String custEmailaddress;
    private String ordersum;
    private Order order;
    private Address daddress;
    private Address baddress;
    private Payment payment;

    //constructors
    public Email(Customer customer, Order order, Payment payment, Address daddress, Address baddress)
    {
        this.customer = customer;
        this.payment = payment;
        this.daddress = daddress;
        this.baddress = baddress;
        this.custEmailaddress = customer.getEmail();//gets the customers email from the email class
        this.ordersum = order.orderSummary(payment);//transfers the order summary from the order class to email class and stores as a string variable
        
    }
    //getter and setters
    public String getOrderSum() {
        return ordersum;
    }
    
    public void setOrderSum() {
        this.ordersum = ordersum;
    }


    public void emailOrderSummary()
    {
        System.out.println("\n" + custEmailaddress + "\nDelivery address:" + daddress.getAddress() + "\nCustomer Name:" + customer.getName() + "\n" + ordersum + "\nBilling address: " + baddress.getAddress()); 
    }
    //both of these emails print out either an order summary or an email of regret depending on which method is called in the order class
    public void emailOfRegret() {
        System.out.println("\n" + custEmailaddress + "\nCustomer Name: " + customer.getName() + "\nOrder could not be completed as payment could not be confirmed/cart is empty");
    }
}
