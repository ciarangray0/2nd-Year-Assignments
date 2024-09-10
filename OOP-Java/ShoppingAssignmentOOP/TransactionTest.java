
public class TransactionTest
{
        public static void main(String[] args){
        TransactionTest test = new TransactionTest();
        test.scenario1();
        test.scenario2();
    }
    //this is the first scenario
    public void scenario1() {
        System.out.println("Scenario1");
        Customer customer = new Customer("Ciaran", "Gray", "ciarangray1@gmail.com", 22427722);
        
        //making a customer and billing and delivery address objects here
        Address daddress = new Address("Dock Street", "Galway", "W34hx90", "Ireland");
        Address baddress = new Address("Mountrice Cross", "Monasterevin", "W91ry60", "Ireland");
        //the three item objects
        Item item1 = new Item("Milk", 10.00, 453);
        Item item2 = new Item("Eggs", 3.65, 674);
        Item item3 = new Item("Bread", 5.50, 998);
        
        //creating a shopping cart object while passing the customer object as a parameter and adding the three items to the shopping cart
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addToCart(item1);
        cart.addToCart(item2);
        cart.addToCart(item3);
        
        
        cart.displayCart();
        //creaing the order with customer sand shopping cart as the parameters
        Order order = new Order(customer, cart);
        order.addToOrder(cart);
        order.displayOrder();
        //email and payment objects
        Payment payment = new Payment(customer, "Visa", "1234567891234567", "09/10/2023", "Bank Of Ireland");
        Email email = new Email(customer, order, payment, daddress, baddress);
        //if the payment is validated here in the order class, it then gets the email class to send the email with all the info
        order.validateOrder(payment);
        order.confirmOrder(email);
    }
    //second scenario
    public void scenario2() {
        System.out.println("\nScenario2");
        //making a customer and billing and delivery address objects here
        Customer customer = new Customer("Ciaran", "Gray", "ciarangray1@gmail.com", 22427722);
        
        Address daddress = new Address("Dock Street", "Galway", "W34hx90", "Ireland");
        Address baddress = new Address("Mountrice", "Monasterevin", "W91ry60", "Ireland");
        //the three item objects
        Item item1 = new Item("Milk", 10.00, 453);
        Item item2 = new Item("Eggs", 3.65, 674);
        Item item3 = new Item("Bread", 5.50, 998);
        
        //creating a shopping cart object while passing the customer object as a parameter and adding the three items to the shopping cart
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addToCart(item1);
        cart.addToCart(item2);
        cart.addToCart(item3);
        
        cart.displayCart();
        //removing the first item from the cart
        cart.removeFromCart(item1);
        
        cart.displayCart();
        //creaing the order with customer sand shopping cart as the parameters
        Order order = new Order(customer, cart);
        order.addToOrder(cart);
        order.displayOrder();
        //email and payment objects
        Payment payment = new Payment(customer, "Viso", "1234567", "09/10/2023", "Bank Of Ireland");
        Email email = new Email(customer, order, payment, daddress, baddress);
        //the payment won't be validated here as the payment details are incorrect, so therefore an email of regret will be sent instead
        order.validateOrder(payment);
        order.confirmOrder(email);
    }

}
