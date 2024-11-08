package ui;

import java.util.HashMap;

import classes.Customer;

public class Directory {
    // prints the list of all customers
    public static void customerCatalogUI(HashMap<String, Customer> customers) {
        System.out.println("Customer Catalog: " + customers.size() + " customers");

        for (String key : customers.keySet()) {
            Customer customer = customers.get(key);
            System.out.println(customer);
        }

        System.out.println();
    }
}
