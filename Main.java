import classes.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import ui.*;


public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static NamedMap<Customer> customers = new NamedMap<>();
    public static Stock stock = new Stock();
    public static ArrayList<Log> logHistory = new ArrayList<>();
    public static int currentDay = 1, currentWeek = 1, currentMonth = 1;
    public static Log log;

    public static void main(String[] args) {
        char choice;
        Log dailyLog = new Log(currentDay);

        do {
            System.out.println("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth);
            System.out.println("[1] New customer"); // (Customer.java)
            System.out.println("[2] New transaction"); // choice if new or existing customer (PointOfSale.java)
            System.out.println("[3] Store stocks"); // add, remove, change items (Inventory.java)
            System.out.println("[4] Store revenue"); // shows logs (Revenue.java)
            System.out.println("[5] Customer catalog"); // shows customer catalog (Profit.java)
            System.out.println("[6] View Profit Logs"); // shows customer catalog (Profit.java)
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
                    Customer customer = new Customer(customerName, outstanding, days);
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
                    Optional<Transaction> newTransaction = Optional.of(new Transaction("NO_NAME", currentDay));
                    PointOfSale.transactionUI(newTransaction, customers, stock, currentDay);

                    if (newTransaction.isPresent()) {
                        dailyLog.addTransaction(newTransaction.get());
                    }
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
                case '6':
                    Profit.profitLog(logHistory);
                break;
                case '7': // proceed to next day
                    Profit.addLog(log.clone(),logHistory);
                    nextDay();
                    dailyLog = new Log(currentDay); // Create a new daily log for next day
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
}
