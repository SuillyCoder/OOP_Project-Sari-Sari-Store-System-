package classes;

import java.util.ArrayList;

public class Transaction implements Cloneable {
    private NamedMap<Item> items;
    private String customer;
    private int date;
    private double payment;
    private double worth;

    public Transaction(String customer, int date) {
        this.items = new NamedMap<>();
        this.customer = customer;
        this.date = date;
        this.payment = 0;
        this.worth = 0;
    }

    public Transaction(Customer customer, int date) {
        this.items = new NamedMap<>();
        this.customer = customer.getName();
        this.date = date;
        this.payment = 0;
        this.worth = 0;
    }

    @Override
    public Transaction clone() {
        Transaction transaction = new Transaction(this.customer, this.date);
        for (Item i : this.items.values()) {
            transaction.addItem(i.clone());
        }
        transaction.payment = this.payment;
        transaction.worth = this.worth;
        return transaction;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer.getName();
    }

    public void setCustomer(String name) {
        this.customer = name;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getCustomer() {
        return customer;
    }

    public int getDate() {
        return date;
    }

    public NamedMap<Item> getItems() {
        return items;
    }

    public double getPayment() {
        return payment;
    }

    public double getWorth() {
        return worth;
    }

    public void incWorth(double worth) {
        this.worth += worth;
    }

    public void decWorth(double worth) {
        this.worth -= worth;
    }

    // potential bug: if the item changes price in the middle of a purchase, then removeItem() will not remove item
    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }

    public void removeItem(Item item) {
        this.items.remove(item.getName());
    }

    // modify if necessary
    public String toString() {
        String output = "Customer: " + customer + "\nDate: " + date + "\nItems:\n";
        for (Item i : items.values()) {
            output += i.toString() + "\n";
        }
        output += "Worth: " + worth + "\n";
        return output;
    }
}