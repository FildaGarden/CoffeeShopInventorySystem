import java.util.ArrayList;
import java.util.List;

//Class for Shopping Cart
public class Cart {
    private List<Item> cart;
    private double totalPrice;

    // Class constructor
    public Cart() {
        cart = new ArrayList<>();
        totalPrice = 0.0;
    }

    // Adds item to the shopping cart
    public void addToCart(Item item) {
        totalPrice += item.getPrice();
        cart.add(item);
    }

    // Getters
    public List<Item> getCart() {
        return cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Overriding toString method to print receipt.
    @Override
    public String toString() {
        //New string for the receipt
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("%-5s%-20s%s\n", "ID", "Item", "Price"));
        // For each item in cart -> print item and its values
        for (Item item : cart) {
            receipt.append(String.format("%-5d%-20s%.2f\n", item.getId(), item.getName(), item.getPrice()));
        }
        // Print total price
        receipt.append(String.format("\nTotal Price: %.2f", totalPrice));
        
        //Returns the final string
        return receipt.toString();
    }
}
