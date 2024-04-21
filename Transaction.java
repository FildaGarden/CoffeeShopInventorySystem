import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {

    // Local variables
    private String date;
    private String paymentMethod;
    private List<Item> items;
    private double totalPrice;
    private double tenderedAmount;
    private double change;

    // Transaction method constructor for paying by CREDIT CARD
    public Transaction(String paymentMethod, List<Item> items, double totalPrice) {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Transaction method constructor for paying by CASH
    public Transaction(String paymentMethod, List<Item> items, double totalPrice, double tenderedAmount,
            double change) {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.totalPrice = totalPrice;
        this.tenderedAmount = tenderedAmount;
        this.change = change;
    }

    // Prints object as a String - CASH => No need to override toString method
    public String toStringCash() {
        return "Transaction [\n" +
                "  date=" + date + ",\n" +
                "  paymentMethod=" + paymentMethod + ",\n" +
                "  items=" + items + ",\n" +
                "  totalPrice=" + totalPrice + ",\n" +
                "  tenderedAmount=" + tenderedAmount + ",\n" +
                "  change=" + change + "\n" +
                "]";
    }

    // Prints object as a String - CREDIT CARD => No need to override toString method
    public String toStringCredit() {
        return "Transaction [\n" +
                "  date=" + date + ",\n" +
                "  paymentMethod=" + paymentMethod + ",\n" +
                "  items=" + items + ",\n" +
                "  totalPrice=" + totalPrice + ",\n" +
                "]";
    }
}