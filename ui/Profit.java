package ui;

import classes.Customer;
import classes.Log;
import java.util.ArrayList;
import java.util.HashMap;

    // implement TUI here
public class Profit {
    // prints the list of all customers
    public static void customerCatalogUI(HashMap<String, Customer> customers) {
        System.out.println("Customer Catalog: " + customers.size() + " customers");

        for (String key : customers.keySet()) {
            Customer customer = customers.get(key);
            System.out.println(customer);
        }

        System.out.println();
    }

    public static void addLog(Log log, ArrayList<Log> logHistory) {
        logHistory.add(log.clone()); // Add the cloned log to the logHistory
        if (logHistory.size() == 0) {
            System.out.println("Log Addition Unsuccessful!\n");
        }
        else{
            System.out.println("Log Added Successfully!\n");
        }
    }
    public static void profitLog(ArrayList<Log> logs) {
    int inc = 0;
    if (logs.size() == 0) {
        System.out.println("No logs found!\n");
        return;
    }

    for (int i = logs.size() - 1; i >= 0; i--) {
        Log log = logs.get(i);
        int logNumber = logs.size() - inc;
        System.out.print("Day: " + logNumber);
        System.out.print("\tTotal Payment: " + log.getPayment());
        System.out.print("\tTotal Worth: " + log.getWorth());
        System.out.println("\n");
        inc++;
        }
    }
}