package classes;

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

    public void fromFile() {
        // implement code here
    }

    public void toFile() {
        // implement code here
    }
}
