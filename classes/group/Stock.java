// Class which stores all items

package classes.group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import classes.NamedMap;
import classes.indiv.Item;

public class Stock extends NamedMap<Item> {
    // adding a single item in the stock
    public void addItem(String name, Item item) {
        name = name.trim().toLowerCase();
        // if the item does not exist, then add the item to the stock
        if (!this.containsKey(name)) { 
            this.put(name, item);

        // if the item already exists, then increment only the amount specified
        } else { 
            this.get(name).incQuantity(item.getQuantity());
        }
    }

    // removing an item in the stock
    public void removeItem(String name) {
        String normalizedName = name.trim().toLowerCase();
        System.out.println("Trying to remove item: " + normalizedName); // Added for debugging
    
        if (this.containsKey(normalizedName)) {
            this.remove(normalizedName);
            System.out.println("Item removed: " + normalizedName);
        }
        else{
            System.out.println("Item not found: " + normalizedName);
        }
    }

    public String toString() {
        String inventory = "Inventory: " + this.size() + " SKUs\n";
    
        // Group items by category
        Map<String, List<Item>> itemsByCategory = this.values().stream()
                .collect(Collectors.groupingBy(Item::getCategory));
    
        // Sort categories alphabetically
        List<String> sortedCategories = new ArrayList<>(itemsByCategory.keySet());
        Collections.sort(sortedCategories);
    
        // Print items by category, each sorted alphabetically within the category
        for (String category : sortedCategories) {
            inventory += category.toUpperCase() + "\n";
    
            List<Item> itemsInCategory = itemsByCategory.get(category);
            itemsInCategory.sort((item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
    
            for (Item item : itemsInCategory) {
                inventory += "  " + item.toString() + "\n"; // Adjust to control item format if needed
            }
        }
        inventory += "\n";

        return inventory;
    }
    
    public String search(String search) {
        Stock filteredStock = new Stock();

        for (String key : this.keySet()) {
            Item item = this.get(key);
            if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                filteredStock.addItem(key, item);
            }
        }
    
        return filteredStock.toString();
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
            for (String key : this.keySet()) {
                Item item = this.get(key);
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
