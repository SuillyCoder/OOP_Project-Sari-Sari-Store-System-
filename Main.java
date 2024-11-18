
import classes.group.*;
import classes.indiv.*;
import gui.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Optional;
import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame implements ActionListener{
// GUI components
private JPanel mainPanel, panelOne, panelTwo, panelTwoButtons, panelTwoLogs;
private JButton transaction, inventory, customerDirectory, nextDay;
private JTextArea inventoryList, customerList, dayIndicatorLabel;

private Main mainMenu; 
private JInventory inventoryPage; 

public Main() {
    super("Main Menu");

    initializeUI(); // Set up GUI components

    // Initialize mainMenu and inventoryPage
    mainMenu = this; // Refers to the current Main instance
    inventoryPage = new JInventory(new Stock()); // Pass stock or other dependencies

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setVisible(true);
}

public void initializeUI() {
    // Initialize panels, buttons, etc.
    mainPanel = new JPanel(new BorderLayout());
    panelOne = new JPanel(new GridLayout(4, 1));
    panelTwo = new JPanel(new BorderLayout());
    panelTwoButtons = new JPanel();
    panelTwoLogs = new JPanel(new BorderLayout());

    transaction = new JButton("Transaction");
    inventory = new JButton("Inventory");
    customerDirectory = new JButton("Customer Directory");
    nextDay = new JButton("Next Day");

    inventoryList = new JTextArea();
    customerList = new JTextArea();
    dayIndicatorLabel = new JTextArea();

    // Configure components
    panelOne.add(transaction);
    panelOne.add(inventory);
    panelOne.add(customerDirectory);
    panelOne.add(nextDay);

    panelTwoButtons.add(new JButton("Daily Logs"));
    panelTwoButtons.add(new JButton("Weekly Logs"));
    panelTwoButtons.add(new JButton("Monthly Logs"));

    //Adding Inventory and Customer Data to the Logs
      JScrollPane scrollPane = new JScrollPane(customerList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      JPanel logPanel = new JPanel(new GridLayout(3, 1));

        // Adding the data to the customer window
         customerList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
         customerList.setColumns(50);
         customerList.setText("Empty Default\n".repeat(50));
         panelTwoLogs.add(customerList);

        // Adding the data to the inventory window
        inventoryList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        inventoryList.setColumns(50);
        inventoryList.setText("Empty Default\n".repeat(50));
        panelTwoLogs.add(inventoryList);

        panelTwoLogs.add(dayIndicatorLabel);

        logPanel.add(dayIndicatorLabel);
        logPanel.add(customerList);
        logPanel.add(inventoryList);
  
      scrollPane.setViewportView(logPanel);
      panelTwoLogs.add(scrollPane, BorderLayout.CENTER);

    panelTwo.add(panelTwoButtons, BorderLayout.NORTH);
    panelTwo.add(panelTwoLogs, BorderLayout.CENTER);


    mainPanel.add(panelOne, BorderLayout.WEST);
    mainPanel.add(panelTwo, BorderLayout.CENTER);

    add(mainPanel);

    // Add action listeners
    transaction.addActionListener(this);
    inventory.addActionListener(this);
    customerDirectory.addActionListener(this);
    nextDay.addActionListener(this);
}

 //Updating Data 
 public void updateText(String day, Contacts contacts, Stock stock){
    dayIndicatorLabel.setText(day);
    customerList.setText(contacts.toString());
    inventoryList.setText(stock.toString());
  }


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

        Main mainMenu = new Main();
        mainMenu.updateText(printDay(),contacts,stock);
        mainMenu.setVisible(true);

        //JInventory inventory = new JInventory(stock);
        //inventory.updateText();
        //inventory.setVisible(true);

        //JItemSelector test = new JItemSelector("Add Item");
        //test.updateText(stock);
        //test.setVisible(true);


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
                    //JInventory inventory = new JInventory(stock);
                    //inventory.updateText();
                    //inventory.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == transaction){
        //Transaction button clicked
       }
       else if(e.getSource() == inventory){
        JInventory inventoryPage = new JInventory(stock);
        System.out.println("Test!");
        this.setVisible(false);
        inventoryPage.updateText();
        inventoryPage.setVisible(true);
       }
    }
}
