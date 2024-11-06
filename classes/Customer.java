package classes;
public class Customer {
    private String name;
    private double outstanding;
    private int lastOutstandingDate;

    public Customer(String name, double outstanding, int lastOutstandingDate) {
        this.name = name;
        this.outstanding = outstanding;
        this.lastOutstandingDate = lastOutstandingDate;
    }

    public String getName() {
        return name;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public int getLastOutstandingDate() {
        return lastOutstandingDate;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public void setLastOutstandingDate(int lastOutstandingDate) {
        this.lastOutstandingDate = lastOutstandingDate;
    }

    public String toString() {
        return "Name: " + name + "\t\tOutstanding: P" + String.format("%.2f", outstanding) + "\t\tLast Outstanding Date: " + lastOutstandingDate;
    }
}
