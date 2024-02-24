//Class for an Item
public class Item {
    
    //Local variables
    private int optionNumber;
    private String name;
    private double price;

    //Class constructor
    public Item(int optionNumber, String name, double price) {
       this.optionNumber = optionNumber;
       this.name = name;
       this.price = price;
    }
    
    //Getters and Setters for local variables
    public int getOptionNumber() {
        return optionNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //Converts Object to String
    //Used for debugging purposes
    @Override
    public String toString() {
        return "Item{" +
                "optionNumber=" + optionNumber +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
