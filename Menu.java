import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu{

    //Local variables
    public static List<Item> menuItems = readInventory("data/inventory.csv");
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String RESET = "\u001B[0m";

    //ArrayList for our items in inventory
    private static List<Item> readInventory(String file){
        List<Item> items = new ArrayList<>();
        
        //Read all data from inventory.csv
        try(Scanner scanner = new Scanner(new File(file))){
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                
                //Assign them to variables
                int optionNumber = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2].trim());

                //Create new instance of our Item class and add them to our ArrayList
                Item item = new Item(optionNumber,name, price);
                items.add(item);
            }
        }
        //Handle exceptions
        catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return items;
    }

    //Display the main menu
    public static void displayMenu() {
        int size = menuItems.size();
        System.out.println("\n\n");
        System.out.println("WELCOME TO OUR COFFEE SHOP");
        System.out.println("Please choose your order: ");
        
        for(int i = 0; i<size; i++) {
            System.out.println("================================================");
            System.out.printf("%d. %s %.2f \n", menuItems.get(i).getOptionNumber(), menuItems.get(i).getName(), menuItems.get(i).getPrice());
        }
        System.out.println("================================================");
        System.out.printf("%d. %s\n", menuItems.size()+1, "Exit");
        System.out.println("================================================");
        System.out.printf("%d. %s\n", menuItems.size()+2, "Proceed to checkout");
    }

    //Display the checkout menu
    public static void displayCheckoutMenu() {
        System.out.println("Choose your payment method: ");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("-------------------------------");
    }

    //Display the credit card menu
    public static void displayCreditCardMenu() {
        System.out.println("Choose your credit card type: ");
        System.out.println("1. Visa");
        System.out.println("2. Master Card");
    }

    //Display the cash menu
    public static void displayCashMenu() {
        System.out.printf("Enter an amount: ");
    }

    //Display that order was successful
    public static void displayOrderSuccessful() {
        System.out.println("-------------------------------");
        System.out.println(GREEN + "Order Successful!" + RESET);
        System.out.println("Enjoy your coffee");
        System.out.println("-------------------------------");
    }

    //Clears the console
    public static void clearMenu() {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
    }
    }
