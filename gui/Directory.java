package gui;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import classes.indiv.Customer;

public class Directory {
    private static Scanner sc = new Scanner(System.in);

    // prints the list of all customers
    public static void customerCatalogUI(HashMap<String, Customer> customers) {
        System.out.println("Customer Catalog: " + customers.size() + " customers");

        ArrayList<Customer> sortedCustomers = new ArrayList<>(customers.values());
        Collections.sort(sortedCustomers);

        for (Customer customer : sortedCustomers) {
            System.out.println(customer);
        }

        System.out.println();
    }

    public static void changeMaxDebtUI(){
        double newMaxDebt;
        
        System.out.println("Set the maximum amount a person can borrow to (currently: " + -1 * Customer.getMaxDebt() + ") >> ");

        newMaxDebt = sc.nextDouble();
        sc.nextLine();

        Customer.setMaxDebt(newMaxDebt);
        System.out.println("Maximum debt changed to " + -1 * Customer.getMaxDebt());
        System.out.println();
    }
}
