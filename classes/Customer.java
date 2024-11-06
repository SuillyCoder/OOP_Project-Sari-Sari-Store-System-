package classes;
public class Customer {
    private String name;
    private double outstanding;
    private int lastDays;

    public Customer(String name, double outstanding, int lastDays) {
        this.name = name;
        this.outstanding = outstanding;
        this.lastDays = lastDays;
    }

    public String getName() {
        return name;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public int getLastDays() {
        return lastDays;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public void setLastDays(int lastDays) {
        this.lastDays = lastDays;
    }

    public void incLastDays() {
        this.lastDays += 1;
    }

    public String toString() {
        return "Name: " + name + "\t\tOutstanding: P" + String.format("%.2f", outstanding) + "\t\tLast Outstanding Date: " + lastDays;
    }
}
