import classes.*;
import ui.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static HashMap<String, Customer> customers = new HashMap<>();
    public static Stock stock = new Stock();
    public static ArrayList<Log> logs = new ArrayList<>();
    public static Customer customer;

    public static void main(String[] args) {
        char choice;
        do {
            System.out.println("[1] New customer"); // (Customer.java)
            System.out.println("[2] New purchase"); // choice if new or existing customer (Purchase.java)
            System.out.println("[3] Store stocks"); // add, remove, change items (Inventory.java)
            System.out.println("[4] Store revenue"); // shows logs (Revenue.java)
            System.out.println("[5] Customer catalog"); // shows customer catalog (Profit.java)

            System.out.println("[X] Exit");
            System.out.print(" >> ");

            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
    
            switch (choice) {
                case '1':
                //Enters customer name
                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();
                //Enters their outstanding balance
                    System.out.print("Enter outstanding balance: "); 
                    double outstanding = sc.nextDouble();
                //Enters days since outstanding balance was 0
                    System.out.print("Enter no. of days since last outstanding: ");
                    int days = sc.nextInt();
                    sc.nextLine(); 
                //Add data within the customer constructor
                    customer = new Customer(customerName, outstanding, days);
                //Add instance of constructor within the hashset
                    customers.put(customerName, customer);
                    System.out.println();
                    break;
                case '2':
                    // implement code here
                    break;
                case '3':
                    Inventory.inventoryManagerUI(stock);
                    break;
                case '4':
                    // implement code here
                    break;
                case '5':
                    Profit.customerCatalogUI(customers);
                    break;
                case 'x':
                    choice = 'X';
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 'X');


    }
}
