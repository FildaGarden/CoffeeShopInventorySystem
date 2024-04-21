import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TransactionHandler {

    // Local variables
    private String paymentMethod;
    private double tenderedAmount;
    private double change;
    private String transactionFile = "transactions/transactions.log";

    // Getters and Setters for local variables.
    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setTenderedAmount(double tenderedAmount) {
        this.tenderedAmount = tenderedAmount;
    }

    public double getTenderedAmount() {
        return tenderedAmount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Finishing the order - for CASH payment
    public void proceedToCheckout(Cart cart, String paymentMethod, double tenderedAmount, double change) {
        saveTransaction(cart, paymentMethod, transactionFile, true, tenderedAmount, change);
        Menu.displayOrderSuccessful();
        System.out.println("-------------------------------");
        System.out.printf("%sHere is your change of %.2f%s\n",Menu.PURPLE, getChange(), Menu.RESET);
        System.out.println("-------------------------------");
        Menu.displayReceipt(cart);
    }

    // Finishing the order - for CREDIT CARD payment
    public void proceedToCheckout(Cart cart, String paymentMethod) {
        saveTransaction(cart, paymentMethod, transactionFile, true);
        Menu.clearMenu();
        Menu.displayOrderSuccessful();
        Menu.displayReceipt(cart);
    }

    // Save receipt into a log file - for CREDIT CARD payment
    public void saveTransaction(Cart cart, String paymentMethod, String file, boolean append) {
        Transaction transaction = new Transaction(paymentMethod, cart.getCart(), cart.getTotalPrice());

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, append))) {
            pw.println(transaction.toStringCredit());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            System.err.println(e);
        }
    }

    // Save receipt into a log file - for CASH payments
    public void saveTransaction(Cart cart, String paymentMethod, String file, boolean append, double tenderedAmount,
            double change) {
        Transaction transaction = new Transaction(paymentMethod, cart.getCart(), cart.getTotalPrice(), tenderedAmount,
                change);

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, append))) {
            pw.println(transaction.toStringCash());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            System.err.println(e);
        }
    }

    // Checks what amount has user input and give back a change if needed
    public void handleCash(double tenderedAmount, Cart cart) {
        if (tenderedAmount == cart.getTotalPrice()) {
            setChange(0.0);
        }
        if (tenderedAmount > cart.getTotalPrice()) {
            setChange(tenderedAmount - cart.getTotalPrice());
        }
    }
}
