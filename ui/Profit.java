package ui;

import classes.Customer;
import classes.Log;
import classes.Transaction;
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
    }
    public static void profitLog(ArrayList<Log> logs) {
    if (logs.size() == 0) {
        System.out.println("No logs found!\n");
        return;
    }

    for (Log log : logs) {
        System.out.println("Log for Date: " + log.getDate());
        for (Transaction transaction : log.getTransactions()) {
            System.out.println(transaction);
        }
        System.out.println("Total Payment: " + log.getPayment());
        System.out.println("Total Worth: " + log.getWorth());
        System.out.println();
    }
}
}
