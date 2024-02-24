import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TransactionHandler {

    //Local variables
    private String paymentMethod;
    private double tenderedAmount;
    private double change;
    private String transactionFile = "transactions/transactions.log";
    
    //Getters and Setters for local variables.
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

    //Finishing the order - for CASH payment
    public void proceedToCheckout(Cart cart, String paymentMethod, double tenderedAmount, double change) {
        saveTransaction(cart, paymentMethod, transactionFile, true, tenderedAmount, change);
        Menu.displayOrderSuccessful();
    }
    
    //Finishing the order - for CREDIT CARD payment
    public void proceedToCheckout(Cart cart, String paymentMethod) {
        saveTransaction(cart, paymentMethod, transactionFile, true);
        Menu.displayOrderSuccessful();
    }

    //Save receipt into a log file - for CASH payment
    public void saveTransaction(Cart cart, String paymentMethod, String file, Boolean append) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(currentDate);

        try(PrintWriter pw = new PrintWriter(new FileWriter(file, append))) {
            pw.println(currentDateTime);
            pw.println(paymentMethod);
            for(Item item : cart.getCart()) {
                pw.printf("%d,%s,%.2f%n", item.getOptionNumber(), item.getName(), item.getPrice());
            }
            pw.printf("TOTAL PRICE: %.2f\n", cart.getTotalPrice());
            pw.printf("---------------------\n");
            
                }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch(IOException eX) {
            System.out.println("Something went wrong");
            System.err.println(eX);
        }
    }

    //Save receipt into a log file - for CREDIT CARD payments
    public void saveTransaction(Cart cart, String paymentMethod, String file, Boolean append, double tenderedAmount, double change) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(currentDate);

        try(PrintWriter pw = new PrintWriter(new FileWriter(file, append))) {
            pw.println(currentDateTime);
            pw.println(paymentMethod);
            for(Item item : cart.getCart()) {
                pw.printf("%d,%s,%.2f%n", item.getOptionNumber(), item.getName(), item.getPrice());
            }
            pw.printf("TOTAL PRICE: %.2f\n", cart.getTotalPrice());
            pw.printf("TENDERED AMOUNT: %.2f\n", tenderedAmount);
            pw.printf("CHANGE: %.2f\n", change);
            pw.printf("---------------------\n");
            }
            
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch(IOException e) {
            System.out.println("Something went wrong");
            System.err.println(e);
        }
    }

    //Checks what amount has user input and give back a change if needed
    public void handleCash(double tenderedAmount, Cart cart) {
        if(tenderedAmount == cart.getTotalPrice()) {
            setChange(0.0);
        }
        if(tenderedAmount > cart.getTotalPrice()) {
            setChange(tenderedAmount - cart.getTotalPrice());
            System.out.println("-------------------------------");
            System.out.printf("Here is your change of %.2f\n", getChange()); 
        }
    }
}
