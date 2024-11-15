// Information for a single item class
// Stock.java keeps track of all items stocked

package classes.indiv;

public class Item implements Cloneable, Comparable<Item> {
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
    
    // comparison based on name (alphabetical order sorting)
    public int compareTo(Item other) {
        return this.name.compareTo(other.name);
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

        return String.format("%-15s %-15s P%-5.2f     %-3d", this.getName(), this.getCategory(), this.getPrice(), this.getQuantity());
        
        // return "Name: " + this.getName() + "\tCategory: " + this.getCategory() +
        //         "\tPrice: " + this.getPrice() + "\tQuantity: " + this.getQuantity();
    }
}