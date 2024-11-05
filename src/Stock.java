import java.util.HashMap;

public class Stock {
    private HashMap<Item, Integer> items;

    public Stock() {
        this.items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (this.items.containsKey(item)) {
            this.items.put(item, this.items.get(item) + quantity);
        } else {
            this.items.put(item, quantity);
        }
    }

    public void removeItem(Item item, int quantity) {
        if (this.items.containsKey(item)) {
            this.items.put(item, this.items.get(item) - quantity);
        }
    }

    public String toString() {
        String output = "Items:\n";
        for (Item i : items.keySet()) {
            output += i.toString() + "Quantity: " + items.get(i) + "\n";
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
