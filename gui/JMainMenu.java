package gui;
import classes.*;
import classes.group.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JMainMenu extends JCustomFrame implements ActionListener{

    // GUI components
    private JPanel mainPanel, panelOne, panelTwo, panelTwoButtons, panelTwoLogs;
    private JButton transaction, inventory, customerDirectory, nextDay, dailyLogs, weeklyLogs, monthlyLogs;
    private JTextArea inventoryList, customerList, dayIndicatorLabel;

    // GUI pages
    public Contacts contacts;
    public Stock stock;
    public History history;

public JMainMenu(History history, Stock stock, Contacts contacts) {
    super("Main Menu");
    this.history = history;
    this.stock = stock;
    this.contacts = contacts;

    initializeUI(); // Set up GUI components
    dailyLogs.setEnabled(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setVisible(true);
}

public void initializeUI() {
    // Initialize panels, buttons, etc.
    mainPanel = new JPanel(new BorderLayout());
    panelOne = new JPanel(new GridLayout(4, 1));
    panelTwo = new JPanel(new BorderLayout());
    panelTwoButtons = new JPanel(new GridLayout(1,3));
    panelTwoLogs = new JPanel(new BorderLayout());

    transaction = new JButton("Transaction");
    inventory = new JButton("Inventory");
    customerDirectory = new JButton("Customer Directory");
    nextDay = new JButton("Next Day");

    dailyLogs = new JButton("Daily Logs");
    weeklyLogs = new JButton("Weekly Logs");
    monthlyLogs = new JButton("Monthly Logs");

    inventoryList = new JTextArea();
    customerList = new JTextArea();
    dayIndicatorLabel = new JTextArea();

    // Configure components
    panelOne.add(transaction);
    panelOne.add(inventory);
    panelOne.add(customerDirectory);
    panelOne.add(nextDay);

    panelTwoButtons.add(dailyLogs);
    panelTwoButtons.add(weeklyLogs);
    panelTwoButtons.add(monthlyLogs);

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
    dailyLogs.addActionListener(this);
    weeklyLogs.addActionListener(this);
    monthlyLogs.addActionListener(this);
}

 //Updating Data 
 public void updateText(){
    dayIndicatorLabel.setText(history.toString());
    customerList.setText(contacts.toString());
    inventoryList.setText(stock.toString());
  }


    //Code for setting up action listeners
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == transaction){
        JPoS transactionPage = new JPoS(this, contacts, history, stock);
        this.setVisible(false);
        transactionPage.setVisible(true);
       }
       else if(e.getSource() == inventory){
        JInventory inventoryPage = new JInventory(this, stock);
        this.setVisible(false);
        inventoryPage.updateText();
        inventoryPage.setVisible(true);
       } else if (e.getSource() == customerDirectory) {
        JDirectory customerDirectoryPage = new JDirectory(this, contacts);
        this.setVisible(false);
        // customerDirectoryPage.updateText();
        customerDirectoryPage.setVisible(true);
       } else if (e.getSource() == nextDay) {
        // Main.nextDay();
        // this.updateText();
       }

       //Log Displays
       else if(e.getSource() == dailyLogs){
        dayIndicatorLabel.setText(history.toString());
        dailyLogs.setEnabled(false);
        weeklyLogs.setEnabled(true);
        monthlyLogs.setEnabled(true);
        this.updateText();
       }
       else if(e.getSource() == weeklyLogs){
        dailyLogs.setEnabled(true);
        weeklyLogs.setEnabled(false);
        monthlyLogs.setEnabled(true);
        this.updateText();
        dayIndicatorLabel.setText(history.weekSummary().toString());
       }
       else if(e.getSource() == monthlyLogs){
        dailyLogs.setEnabled(true);
        weeklyLogs.setEnabled(true);
        monthlyLogs.setEnabled(false);
        this.updateText();
        dayIndicatorLabel.setText(history.monthSummary().toString());
       }
    }
}

