// Customer information class
// the system will only keep track of customers with credit != 0

package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

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
             try (BufferedReader br = new BufferedReader(new FileReader("customer.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].strip();
                    double credit = Double.parseDouble(parts[1].strip());
                    int date = Integer.parseInt(parts[2].strip());
                    
                    // Only add customers with credit != 0
                    if (credit != 0) {
                        customers.put(name, new Customer(name, credit, date));
                    }
                }
            }
            System.out.println("Items loaded from file");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    public static void toFile(NamedMap<Customer> customers) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("customer.csv"))) {
            for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                Customer customer = entry.getValue();
                // Write only customers with credit != 0
                if (customer.getCredit() != 0) {
                    bw.write(customer.getName() + "," + customer.getCredit() + "," + customer.getDate());
                    bw.newLine();
                }
            }
            System.out.println("Items loaded from file");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    }

