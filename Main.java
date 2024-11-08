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

    private static void generalMenu() {
        System.out.println("=".repeat(20));

        Directory.customerCatalogUI(customers);
        Inventory.inventoryListUI(stock);
        Profit.daySummary(logHistory);

        System.out.println("=".repeat(20));
        System.out.println();

        System.out.println("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth);
        System.out.println("[1] New transaction");      // (PointOfSale.java) make a transaction, either new or existing customer 
        System.out.println("[2] Store inventory");      // (Inventory.java) add, remove, change price of items 
        System.out.println("[3] Show store profit");    // (Profit.java) shows financial logs
        System.out.println("[4] Debtors list");         // (Profit.java) shows list of customers with debts
        System.out.println("[5] End day");              // Proceed to next day
        System.out.println("[X] Exit");
    }

    public static void main(String[] args) {
        char choice;
        
        // Load data from files
        Customer.fromFile(customers);
        stock.fromFile();
        // Log.fromFile(logHistory);
        logHistory.add(new Log()); // A new log at the start of the day 1

        do {
            generalMenu();

            System.out.print(" >> ");
            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
    
            switch (choice) {
                case '1': // Make a new transaction
                    Optional<Transaction> newTransaction = Optional.of(new Transaction("NO_NAME", currentDay));
                    PointOfSale.transactionUI(newTransaction, customers, stock, currentDay);

                    if (newTransaction.isPresent()) {
                        logHistory.get(currentDay-1).addTransaction(newTransaction.get());
                    }

                    break;

                case '2': // Manage inventory
                    Inventory.inventoryManagerUI(stock);
                    break;

                case '3':  // Profit checking 
                    System.out.println("PROFIT LOGS:\n\n");
                    Profit.profitLog(logHistory);
                    break;

                case '4': // Debtors list
                    Directory.customerCatalogUI(customers);
                    break;

                case '5': // proceed to next day
                    nextDay();
                    break;

                case 'x': choice = 'X';
                case 'X': break;
                default : System.out.println("Invalid choice");
            }
        } while (choice != 'X');
    }

    public static void nextDay(){
        currentDay++;
        currentWeek = ((currentDay - 1) / 7) + 1;
        currentMonth = ((currentDay - 1) / 30) + 1; // assuming 30 days in a month
        logHistory.add(new Log());                  // A new log entry at the start of the day
    }
    
    private static String getWeekday(int day) {
        day = day % 7;
        switch (day) {
            case 1:     return "Monday";
            case 2:     return "Tuesday";
            case 3:     return "Wednesday";
            case 4:     return "Thursday";
            case 5:     return "Friday";
            case 6:     return "Saturday";
            case 0:     return "Sunday";
            default:    return "Invalid day";
        }
    }


}
