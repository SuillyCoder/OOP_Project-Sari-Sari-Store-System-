package classes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stock {
    private NamedMap<Item> items;

    public Stock() {
        this.items = new NamedMap<>();
    }

    public NamedMap<Item> getItems() {
        return this.items;
    }

    public void addItem(String name, Item item) {
        if (!this.items.containsKey(name)) { // if the item does not exist
            this.items.put(name, item);

        } else { // if the item already exists
            this.items.get(name).incQuantity(item.getQuantity());
        }

    }

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

    public void fromFile(String filePath) {
    
        try(BufferedReader br = new BufferedReader(new FileReader("items.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[]parts = line.split(",");
                    if (parts.length == 3){
                        String itemName = parts[0].trim();
                        double itemPrice = Double.parseDouble(parts[1].trim());
                        int itemQuantity = Integer.parseInt(parts[2].trim());

                        Item item = new Item (itemName, itemPrice, itemQuantity);
                        addItem(itemName, item);
                    }
                }
            }catch (IOException e){
                System.err.println("Error reading from file: " + e.getMessage());
            }catch (NumberFormatException e) {
                System.err.println("Error parsing number: " + e.getMessage());
        }
     
    }

    public void toFile(String filePath) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("items.csv"))){
            for (String key : this.items.keySet()) {
                Item item = this.items.get(key);
                String line = String.format("%s,%.2f,%d", item.getName(),item.getPrice(), item.getQuantity() );
                bw.write(line);
                bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            
        }
    }

}
