import java.util.List;

public class Menu {

    // Local variables
    private static List<Item> menuItems = InventoryLoader.readInventory("data/inventory.csv");
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String RESET = "\u001B[0m";
    public static String PURPLE = "\u001B[35m";

    // Display the main menu
    public static void displayMenu() {
        int size = menuItems.size();
        System.out.println("\n\n");
        System.out.println(PURPLE + "WELCOME TO OUR COFFEE SHOP" + RESET);
        System.out.println("Please choose your order: ");

        for (int i = 0; i < size; i++) {
            System.out.println("================================================");
            System.out.printf("%d. %s %.2f \n", menuItems.get(i).getId(), menuItems.get(i).getName(), menuItems.get(i).getPrice());
        }
        System.out.println("================================================");
        System.out.printf("%s%d. %s%s\n", RED, menuItems.size() + 1, "Exit", RESET);
        System.out.println("================================================");
        System.out.printf("%s%d. %s%s\n", GREEN, menuItems.size() + 2, "Proceed to checkout", RESET);
    }

    // Display total price
    public static void displayTotalPrice(double totalPrice) {
        clearMenu();
        System.out.println("-------------------------------");
        System.out.printf("%sYour total is %.2f%s\n",PURPLE, totalPrice, RESET);
        System.out.println("-------------------------------");
    }

    // Display the checkout menu
    public static void displayCheckoutMenu() {
        System.out.println("-------------------------------");
        System.out.println("Choose your payment method: ");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("-------------------------------");
    }

    // Display the credit card menu
    public static void displayCreditCardMenu() {
        clearMenu();
        System.out.println("-------------------------------");
        System.out.println("Choose your credit card type: ");
        System.out.println("1. Visa");
        System.out.println("2. Master Card");
        System.out.println("-------------------------------");
    }

    // Display the cash menu
    public static void displayCashMenu() {
        clearMenu();
        System.out.printf("Enter an amount: \n");
    }

    // Display that order was successful
    public static void displayOrderSuccessful() {
        System.out.println("-------------------------------");
        System.out.println(GREEN + "Order Successful!" + RESET);
        System.out.println("Enjoy your coffee");
        System.out.println("-------------------------------");
    }

    //Display receipt
    public static void displayReceipt(Cart cart){
        System.out.println("===============================");
        System.out.println("Receipt");
        System.out.println("===============================");
        System.out.println(cart.toString());
        System.out.println("===============================");
       }
    // Clears the console 
    public static void clearMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
