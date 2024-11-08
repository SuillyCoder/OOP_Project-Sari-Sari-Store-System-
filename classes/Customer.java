// Customer information class
// the system will only keep track of customers with credit != 0

package classes;

public class Customer {
    private String name;
    private double credit; // negative means debt, positive means advance
    private int date; // date of last payment

    public Customer(String name, double credit, int date) {
        this.name = name;
        this.credit = credit;
        this.date = date;
    }

    public Customer(String name) {
        this.name = name;
        this.credit = 0;
        this.date = 0;
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public int getDate() {
        return date;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void incDate() {
        this.date += 1;
    }

    public String toString() {
        return "Name: " + name + "\t\tCredit: P" + String.format("%.2f", credit) + "\t\tLast Credit Date: " + date;
    }

    public static void fromFile(NamedMap<Customer> customers) {
        // implement code here
    }

    public static void toFile(NamedMap<Customer> customers) {
        // implement code here
    }
}
