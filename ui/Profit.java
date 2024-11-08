package ui;

import classes.Customer;
import classes.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
    if (logs.size() == 0) {
        System.out.println("No logs found!\n");
        return;
    }

    Scanner time = new Scanner(System.in);
    int inc = 0;

    System.out.println("Select Time Period Viewing Mode\n");
    System.out.println("[1] Day\n[2] Week\n[3] Month\n");
    System.out.print(" >> ");
    int choice = time.nextInt();
    System.out.println();

    switch (choice) {
        case 1: 
            for (int i = logs.size() - 1; i >= 0; i--) {
                Log log = logs.get(i);
                int logNumber = logs.size() - inc;
                System.out.print("Day: " + logNumber);
                System.out.print("\tTotal Payment: " + log.getPayment());
                System.out.print("\tTotal Worth: " + log.getWorth());
                System.out.println("\n");
                inc++;
                }
            break;
        case 2:
            for (int i = logs.size() - 1; i >= 0; i-=7) {
                int weekPay = 0, weekWorth = 0;
                Log log = logs.get(i);
                int logNumber = logs.size() - (6+inc);
                System.out.print("Week: " + logNumber);
                for (int j = 0; j < 7; j++) {
                    weekPay += log.getPayment();
                    weekWorth += log.getWorth();
                }
                System.out.print("\tTotal Payment: " + weekPay);
                System.out.print("\tTotal Worth: " + weekWorth);
                System.out.println("\n");
                inc++;
                }
            break;
        case 3: 
            break;
        }
    }
}