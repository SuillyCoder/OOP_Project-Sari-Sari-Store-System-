package classes;
public class Item implements Cloneable {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    @Override
    public Item clone() {
        return new Item(this.name, this.price, this.quantity);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void incQuantity(int quantity) {
        this.quantity += quantity;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }
    public void decQuantity(int quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }

    // modify if necessary
    public String toString() {
        return "Name: " + name + "\t\tPrice: " + price + "\t\tQuantity: " + quantity + "\n";
    }

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}