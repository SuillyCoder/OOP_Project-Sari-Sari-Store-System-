// A "reciept" class for a single transaction

package classes.indiv;

import classes.NamedMap;

public class Transaction implements Cloneable {
    private NamedMap<Item> items;       // treat like a "cart" during a transaction
    private String customer;
    private int date;
    private double payment;             // Payment made by the customer
    private double worth;               // Worth of the cart

    // constructor: provide customer name and date
    public Transaction(String customer, int date) {
        this.items = new NamedMap<>();  // empty cart
        this.customer = customer;
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

    public NamedMap<Item> getItems() { return items; }
    // adding/removing items from the cart
    public void addItem(Item item) { this.items.put(item.getName(), item); } // potential bug: if the item changes price in the middle
    public void removeItem(Item item) { this.items.remove(item.getName()); } //  of a purchase, then removeItem() will not remove item

    public String getCustomer() { return this.customer; }
    public void setCustomer(String name) { this.customer = name.strip(); }

    public int getDate() { return this.date; }
    public void setDate(int date) { this.date = Math.max(0, date); }

    public double getPayment() { return this.payment; }
    public void setPayment(double payment) { this.payment = payment; }

    public double getWorth() { return this.worth; }
    public void incWorth(double worth) { this.worth += worth; }
    public void decWorth(double worth) { this.worth -= worth; }

    public double getRevenue() { return this.getPayment() - this.getWorth(); }

    public String toString() {
        String output = "Transaction Summary\nCustomer: " + this.getCustomer() + "\tDate: " + date + "\n";
        for (Item i : items.values()) {
            output += i.toString() + "\n";
        }
        output += "Worth: " + worth + "\n";
        return output;
    }
}