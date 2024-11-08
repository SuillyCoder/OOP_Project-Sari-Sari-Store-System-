// Information for a single item class
// Stock.java keeps track of all items stocked

package classes;

public class Item implements Cloneable {
    private String name;
    private String category;
    private double price;
    private int quantity;   // for Transaction: amount sold to customer
                            // for Stock: total amount available to be sold

    // standard constructor
    public Item(String name, String category, double price, int quantity) {
        this.setName(name);
        this.setCategory(category);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    // clone method used in Transaction.java
    @Override
    public Item clone() {
        return new Item(this.name, this.category, this.price, this.quantity);
    }

    // accessors and mutators
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name.strip(); }

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category.strip(); }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = Math.max(0, price); }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = Math.max(0, quantity); }
    public void incQuantity(int quantity) { this.quantity = Math.max(0, this.quantity + quantity); }
    public void decQuantity(int quantity) { this.quantity = Math.max(0, this.quantity - quantity); }

    public String toString() {
        return "Name: " + this.getName() + "\t\tCategory: " + this.getCategory() +
                "\t\tPrice: " + this.getPrice() + "\t\tQuantity: " + this.getQuantity() + "\n";
    }
}