
import java.util.ArrayList;

public class Reciept implements Cloneable {
    private ArrayList<Item> items;
    private String customer;
    private int date;
    private double cashOnHand;
    private double revenue;

    public Reciept(String customer, int date) {
        this.items = new ArrayList<>();
        this.customer = customer;
        this.date = date;
        this.cashOnHand = 0;
        this.revenue = 0;
    }

    public Reciept(Customer customer, int date) {
        this.items = new ArrayList<>();
        this.customer = customer.getName();
        this.date = date;
        this.cashOnHand = 0;
        this.revenue = 0;
    }

    @Override
    public Object clone() {
        Reciept reciept = new Reciept(this.customer, this.date);
        reciept.items = new ArrayList<>();
        for (Item i : this.items) {
            reciept.items.add(i.clone());
        }
        reciept.cashOnHand = this.cashOnHand;
        reciept.revenue = this.revenue;
        return reciept;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer.getName();
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getCashOnHand() {
        return cashOnHand;
    }

    public double getRevenue() {
        return revenue;
    }

    // potential bug: if the item changes price in the middle of a purchase, then removeItem() will not remove item
    public void addItem(Item item) {
        this.items.add(item.clone());
        this.revenue += item.getPrice();
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        this.revenue -= item.getPrice();
    }

    // modify if necessary
    public String toString() {
        String output = "Customer: " + customer + "\nDate: " + date + "\nItems:\n";
        for (Item i : items) {
            output += i.toString() + "\n";
        }
        output += "Revenue: " + revenue + "\n";
        return output;
    }
}