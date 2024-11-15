// Class which stores all Customers

package classes.group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import classes.NamedMap;
import classes.indiv.Customer;

public class Contacts extends NamedMap<Customer> {

    // prints the list of all customers
    public String toString() {
        String res = "Customer Catalog: " + this.size() + " customers\n";

        ArrayList<Customer> sortedCustomers = new ArrayList<>(this.values());
        Collections.sort(sortedCustomers);

        for (Customer customer : sortedCustomers) {
            res += customer + "\n";
        }

        return res + "\n";
    }
    
    // file read and write operations
    public void fromFile() {
             try (BufferedReader br = new BufferedReader(new FileReader("data/customer.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].strip();
                    double credit = Double.parseDouble(parts[1].strip());
                    int date = Integer.parseInt(parts[2].strip());
                    
                    // Only add customers with credit != 0
                    if (credit != 0) {
                        this.put(name, new Customer(name, credit, date));
                    }
                }
            }
            System.out.println("Customers loaded from file!");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    public void toFile() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/customer.csv"))) {
            for (Map.Entry<String, Customer> entry : this.entrySet()) {
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
