package ui;

import java.util.HashMap;
import java.util.Scanner;

import classes.Customer;

public class Directory {
    private static Scanner sc = new Scanner(System.in);

    // prints the list of all customers
    public static void customerCatalogUI(HashMap<String, Customer> customers) {
        System.out.println("Customer Catalog: " + customers.size() + " customers");

        for (String key : customers.keySet()) {
            Customer customer = customers.get(key);
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
