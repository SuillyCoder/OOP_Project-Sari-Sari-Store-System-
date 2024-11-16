
import classes.*;
import classes.group.*;
import classes.indiv.*;
import gui.*;
import gui.InventoryGUI.JInventory;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Contacts contacts = new Contacts();
    public static Stock stock = new Stock();
    public static History history = new History();

    private static void generalMenu() {
        System.out.println("=".repeat(20));

        System.out.println(contacts);
        System.out.println(stock);
        System.out.println(history);

        System.out.println("=".repeat(20));
        System.out.println();

        Inventory.lowInventoryNotifier(stock);
        System.out.println();

        printDay();
        System.out.println("[1] New transaction");      // (PointOfSale.java) make a transaction, either new or existing customer 
        System.out.println("[2] Store inventory");      // (Inventory.java) add, remove, change price of items 
        System.out.println("[3] Show store profit");    // (Profit.java) shows financial logs
        System.out.println("[4] Debtors list");         // (Directory.java) shows list of customers with debts
        System.out.println("[5] Change maximum debt");  // (Directory.java) change maximum allowable debt
        System.out.println("[6] End day");              // Proceed to next day
        System.out.println("[X] Exit");
    }

    public static void main(String[] args) {   
        char choice;
        int currentDay;
        
        contacts.fromFile();   // (Customer.java)
        stock.fromFile();               // (Stock.java)
        history.fromFile();             // (Log.java)
        
        if (history.isEmpty()) {     // If no logs are loaded, add a new log for the first day
            history.add(new Log());
        }
        //INTERFACE INITIALISATIONS

        // JMainMenu mainMenu = new JMainMenu();
        // mainMenu.updateText(printDay(),contacts,stock);
        // mainMenu.setVisible(true);

        // JInventory inventory = new JInventory();
        // inventory.updateText(stock);
        // inventory.setVisible(true);

        // JItemSelector test = new JItemSelector("Add Item");
        // test.updateText(stock);
        // test.setVisible(true);


        do {
            currentDay = history.size();
            generalMenu();

            System.out.print(" >> ");
            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
    
            switch (choice) {
                case '1': // Make a new transaction
                    Optional<Transaction> newTransaction = Optional.of(new Transaction("NO_NAME", currentDay));
                    PointOfSale.transactionUI(newTransaction, contacts, stock, currentDay);

                    if (newTransaction.isPresent()) {
                        history.get(currentDay-1).addTransaction(newTransaction.get());
                    }

                    break;

                case '2': // Manage inventory
                    JInventory inventory = new JInventory();
                    inventory.setStock(stock);
                    inventory.updateText();
                    inventory.setVisible(true);
                    break;

                case '3':  // Profit checking 
                    System.out.println("PROFIT LOGS:\n\n");
                    Profit.profitLog(history);
                    break;

                case '4': // Debtors list
                    System.out.println(contacts);
                    break;

                case '5': // Change maximum debt
                    Directory.changeMaxDebtUI();
                    break;

                case '6': // proceed to next day
                    nextDay();
                    break;

                case 'x': choice = 'X';
                case 'X': break;
                default : System.out.println("Invalid choice");
            }
        } while (choice != 'X');

        // Remove the last log if no transactions were made
        if (history.get(currentDay-1).getTotalWorth() == 0 && history.get(currentDay-1).getTotalPayment() == 0) {
            history.remove(currentDay-1);    // Remove the last log if no transactions were made
        }

        // Save data to files
        stock.toFile();
        contacts.toFile();
        history.toFile();
    }

    public static void nextDay(){
        history.add(new Log());                  // A new log entry at the start of the day
    }

    public static String printDay(){
        int currentDay = history.size();
        int currentWeek = ((currentDay - 1) / 7) + 1;
        int currentMonth = ((currentDay - 1) / 30) + 1; // assuming 30 days in a month
        String dayLabel = ("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth);
        System.out.println(dayLabel);

        return dayLabel;
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
