import classes.*;
import ui.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static NamedMap<Customer> customers = new NamedMap<>();
    public static Stock stock = new Stock();
    public static ArrayList<Log> logs = new ArrayList<>();
    public static Customer customer;
    public static int currentDay = 1, currentWeek = 1, currentMonth = 1;

    public static void main(String[] args) {
        char choice;
        do {
            System.out.println("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth + " (" + getMonth(currentMonth) + ")");
            System.out.println("[1] New customer"); // (Customer.java)
            System.out.println("[2] New purchase"); // choice if new or existing customer (Purchase.java)
            System.out.println("[3] Store stocks"); // add, remove, change items (Inventory.java)
            System.out.println("[4] Store revenue"); // shows logs (Revenue.java)
            System.out.println("[5] Customer catalog"); // shows customer catalog (Profit.java)
            System.out.println("[6] Update Outstanding Balance"); // shows customer catalog (Profit.java)
            System.out.println("[7] End day"); // shows customer catalog (Profit.java)

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
                    //This is where we'll check and compare for duplicates

                    //Check if the customer does not exist firsthand
                    if (!customers.containsKey(customer.getName())) {
                        if (customer.getCredit() != 0){
                        customers.put(customer.getName(), customer); // Add the customer to the HashMap
                        }
                    } else {
                        // Handle the case where a customer with the same name already exists
                        System.out.println("Customer with name '" + customer.getName() + "' already exists.");
                    }
                    System.out.println();
                    break;

                    //I'LL TEMPORARILY PUT THE CODE FOR THIS RIGHT OVER HERE: 
                    
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
                    
                    //I'LL TEMPORARILY ADD THE CODE FOR THIS OVER HERE. JUST NO MODULARIZATION FOR THE MOMENT. 
                case '6':
                    //Ask for the name of the customer
                    //Check the hashmap
                    //Update the balance of the customter
                    //Run through the entire hashmap and pop off any customers who have 0 outstanding balance. 
                break;
                case '7': // proceed to next day
                    nextDay();
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

    public static void nextDay(){
        currentDay++;
        currentWeek = ((currentDay - 1) / 7) + 1;
        currentMonth = ((currentDay - 1) / 30) + 1; // assuming 30 days in a month
    }

    private static String getWeekday(int day) {
        day = day % 7;
        switch (day) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 0:
                return "Sunday";
            default:
                return "Invalid day";
        }
    }


    private static String getMonth(int month) {
        month = month % 12;
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 0:
                return "December";
            default:
                return "Invalid month";
        }
    }
}
