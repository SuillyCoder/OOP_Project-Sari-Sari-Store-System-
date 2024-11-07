package classes;

import java.util.ArrayList;

public class Transaction implements Cloneable {
    private ArrayList<Item> items;
    private String customer;
    private int date;
    private double payment;
    private double worth;

    public Transaction(String customer, int date) {
        this.items = new ArrayList<>();
        this.customer = customer;
        this.date = date;
        this.payment = 0;
        this.worth = 0;
    }

    public Transaction(Customer customer, int date) {
        this.items = new ArrayList<>();
        this.customer = customer.getName();
        this.date = date;
        this.payment = 0;
        this.worth = 0;
    }

    @Override
    public Transaction clone() {
        Transaction reciept = new Transaction(this.customer, this.date);
        reciept.items = new ArrayList<>();
        for (Item i : this.items) {
            reciept.items.add(i.clone());
        }
        reciept.payment = this.payment;
        reciept.worth = this.worth;
        return reciept;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer.getName();
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getPayment() {
        return payment;
    }

    public double getWorth() {
        return worth;
    }

    // potential bug: if the item changes price in the middle of a purchase, then removeItem() will not remove item
    public void addItem(Item item) {
        this.items.add(item.clone());
        this.worth += item.getPrice();
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        this.worth -= item.getPrice();
    }

    // modify if necessary
    public String toString() {
        String output = "Customer: " + customer + "\nDate: " + date + "\nItems:\n";
        for (Item i : items) {
            output += i.toString() + "\n";
        }
        output += "Worth: " + worth + "\n";
        return output;
    }
}