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

    public static void main(String[] args) {
        char choice;
        //A new log at the start of the day 1
        logHistory.add(new Log());

        do {
            System.out.println("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth);
            //System.out.println("[1] New customer"); // (Customer.java)
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
        
                    
                case '2':
                    Optional<Transaction> newTransaction = Optional.of(new Transaction("NO_NAME", currentDay));
                    PointOfSale.transactionUI(newTransaction, customers, stock, currentDay);

                    if (newTransaction.isPresent()) {
                        logHistory.get(currentDay-1).addTransaction(newTransaction.get());
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
                    System.out.println("PROFIT LOGS:\n\n");
                    Profit.profitLog(logHistory);
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
        logHistory.add(new Log());
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
