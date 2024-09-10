
/**
 * ciaran gray
 */
public class Payment
{
    private Customer customer;
    private String cardType;
    private String cardNumber;
    private String date;
    private String bankName;

    public Payment(Customer customer, String cardType, String cardNumber, String date, String bankName) 
    {
        this.customer = customer;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.date = date;
        this.bankName = bankName;
    }

    // method for validating the card type, should be Masercard or Visa
    public String validateCardType() {
        if (this.cardType.equals("MasterCard") || cardType.equals("Visa")) {
            return cardType;
        } 
        else
        {
            return "Invalid Card Type";
        }
    }

    //Validating the card number by checking if it contains the right ammount of digits
    public String validateCardNumber() {
        if (this.cardNumber.length() == 16) {
            return cardNumber;
        } else {
            return "Invalid Card Number";
        }
    }

    //getter methods 

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getDate() {
        return date;
    }

    public String getBankName() {
        return bankName;
    }
    //string that contains all the payment details
    public String paymentDetails() {
        return "\n-Payment Details-\nBank Name: " + bankName + "\nCard Details: " + cardType + " " + cardNumber + "\nDate: " + date;
    }
}
