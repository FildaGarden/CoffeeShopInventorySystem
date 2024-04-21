import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryLoader {
    
    //Returns ArrayList with items loaded from file
    public static List<Item> readInventory(String file) {
        List<Item> items = new ArrayList<>();

        // Read all data from inventory.csv
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                // Assign them to variables
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2].trim());

                // Create new instance of our Item class and add them to our ArrayList
                Item item = new Item(id, name, price);
                items.add(item);
            }
        }
        // Handle exceptions
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return items;
    }
}
