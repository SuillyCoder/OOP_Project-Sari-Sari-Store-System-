// Customer information class
// the system will only keep track of customers with credit != 0

package classes;

public class Customer {
    private String name;
    private double credit;  // negative means debt, positive means advance
    private int date;       // date of last payment

    // constructor: supply name only
    public Customer(String name) {
        this.setName(name);
        this.setCredit(0);
        this.setDate(0);
    }

    // constructor used in file reading
    private Customer(String name, double credit, int date) {
        this.setName(name);
        this.setCredit(credit);
        this.setDate(date);
    }

    // accessors and mutators
    public String getName() { return name; }
    public void setName(String name) { this.name = name.strip(); }
    
    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    
    public int getDate() { return date; } 
    public void setDate(int date) { this.date = Math.max(0, date); }


    public String toString() {
        return "Name: " + name + "\t\tCredit: P" + String.format("%.2f", credit) + "\t\tLast Credit Date: " + date;
    }

    // file read and write operations
    public static void fromFile(NamedMap<Customer> customers) {
        // implement code here
    }

    public static void toFile(NamedMap<Customer> customers) {
        // implement code here
    }
}
