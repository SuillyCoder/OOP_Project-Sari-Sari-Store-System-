package classes;
import java.util.HashMap;

public class Stock {
    private HashMap<String, Item> items;

    public Stock() {
        this.items = new HashMap<>();
    }

    // change arguments to something more sensible
    public void addItem(Item item, int quantity) {
        // if (this.items.containsKey(item)) {
        //     this.items.put(item, this.items.get(item) + quantity);
        // } else {
        //     this.items.put(item, quantity);
        // }
    }

    public void removeItem(Item item, int quantity) {
        // if (this.items.containsKey(item)) {
        //     this.items.put(item, this.items.get(item) - quantity);
        // }
    }

    public String toString() {
        String output = "Items:\n";
        for (String item : this.items.keySet()) {
            output += item + " - " + this.items.get(item).getQuantity() + "\n";
        }
        return output;
    }

    public void fromFile() {
        // implement code here
    }

    public void toFile() {
        // implement code here
    }
}
