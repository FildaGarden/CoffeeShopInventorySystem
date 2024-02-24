import java.util.ArrayList;
import java.util.List;

//Class for Shopping Cart
public class Cart {
    private List<Item> cart;
    private double totalPrice;
    
    //Class constructor
    public Cart(){
        cart = new ArrayList<>();
        totalPrice = 0.0;
    }
        
    //Adds item to the shopping cart
    public void addToCart(int optionNumber, String itemName, double price) {
        Item item = new Item(1,itemName, price);
        totalPrice += price;
        cart.add(item);
    }

    //Getters
    public List<Item> getCart() {
        return cart;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}
