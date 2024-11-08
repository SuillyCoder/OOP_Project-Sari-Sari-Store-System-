// Class which stores all items

package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stock {
    private NamedMap<Item> items;

    // standard constructor
    public Stock() {
        this.items = new NamedMap<>();
    }

    // accessors and mutators
    public NamedMap<Item> getItems() { return this.items; }

    // adding a single item in the stock
    public void addItem(String name, Item item) {
        // if the item does not exist, then add the item to the stock
        if (!this.items.containsKey(name)) { 
            this.items.put(name, item);

        // if the item already exists, then increment only the amount specified
        } else { 
            this.items.get(name).incQuantity(item.getQuantity());
        }
    }

    // removing an item in the stock
    public void removeItem(String name) {
        if (this.items.containsKey(name)) {
            this.items.remove(name);
        }
    }

    public String toString() {
        String output = "";
        for (String key : this.items.keySet()) {
            Item item = this.items.get(key);
            output += item;
        }
        return output;
    }

    // file read and write operations
    public void fromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("data/items.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[]parts = line.split(",");
                    if (parts.length == 4){
                        String itemName = parts[0].trim();
                        String itemCategory = parts[1].trim();
                        double itemPrice = Double.parseDouble(parts[2].trim());
                        int itemQuantity = Integer.parseInt(parts[3].trim());

                        Item item = new Item (itemName, itemCategory, itemPrice, itemQuantity);
                        addItem(itemName, item);
                    }
                }
                System.out.println("Items loaded from file");

            } catch (IOException e){
                System.err.println("Error reading from file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    public void toFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/items.csv"))){
            for (String key : this.items.keySet()) {
                Item item = this.items.get(key);
                String line = String.format("%s,%s,%.2f,%d", item.getName(),item.getCategory(),item.getPrice(), item.getQuantity() );
                bw.write(line);
                bw.newLine();
                }
                System.out.println("Items saved to file");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());  
        }
    }
}
