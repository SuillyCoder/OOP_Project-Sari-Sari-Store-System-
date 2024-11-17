package gui;
import classes.*;
import classes.group.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//NOTICE: WILL BE DELETING THIS FILE LATER ON ONCE REFACTORING IS COMPLETE
public class JMainMenu extends JCustomFrame implements ActionListener{

    //Panel Declaration
    JPanel mainPanel = new JPanel();
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelTwoButtons = new JPanel();
    JPanel panelTwoLogs = new JPanel();

    //Button Declaration
    JButton transaction = new JButton("Transaction");
    JButton inventory = new JButton("Inventory");
    JButton customerDirectory = new JButton("Customer Directory");
    JButton nextDay = new JButton("Next Day");

    JButton day = new JButton("Daily Logs");
    JButton week = new JButton("Weekly Logs");
    JButton month = new JButton("Monthly Logs");
  
    //Label Declaration (these pieces of text are temporary)
    JTextArea inventoryList = new JTextArea();
    JTextArea customerList = new JTextArea();
    JTextArea dayIndicatorLabel = new JTextArea();

  
    public JMainMenu(){
      //Super Name
      super("Main Menu");
      //Adding to Panels
      panelOne.setLayout(new GridLayout(4, 1));
      panelOne.add(transaction);
      panelOne.add(inventory);
      panelOne.add(customerDirectory);
      panelOne.add(nextDay);

      panelTwo.setLayout(new BorderLayout());
      panelTwo.add(panelTwoLogs, BorderLayout.CENTER);
      panelTwo.add(panelTwoButtons, BorderLayout.NORTH);
      panelTwoButtons.add(day);
      panelTwoButtons.add(week);
      panelTwoButtons.add(month);

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

      mainPanel.setLayout(new BorderLayout());
      mainPanel.add(panelOne, BorderLayout.WEST);
      mainPanel.add(panelTwo, BorderLayout.CENTER);

      //Adding to Frame
      add(mainPanel);

      //Adding action listeners to buttons
      transaction.addActionListener(this);
      inventory.addActionListener(this);
      customerDirectory.addActionListener(this);
      nextDay.addActionListener(this);
      day.addActionListener(this);
      week.addActionListener(this);
      month.addActionListener(this);
    }

    //Updating Data 
    public void updateText(String day, Contacts contacts, Stock stock){
      dayIndicatorLabel.setText(day);
      customerList.setText(contacts.toString());
      inventoryList.setText(stock.toString());
    }

    //Code for setting up action listeners
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == transaction){
        //Transaction button clicked
       }
       else if(e.getSource() == inventory){
        //Inventory button clicked
        //setVisible(false);
        //inventoryPage.updateText();
        //inventoryPage.setVisible(true);
       }
    }
}

