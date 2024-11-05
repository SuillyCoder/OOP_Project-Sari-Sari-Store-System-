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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // modify if necessary
    public String toString() {
        return "Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\n";
    }

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}