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

    // modify if necessary
    public String toString() {
        return "Name: " + name + "\nOutstanding: " + outstanding + "\nLast Outstanding Date: " + lastOutstandingDate + "\n";
    }
}
