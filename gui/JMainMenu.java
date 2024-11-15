package gui;
import classes.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JMainMenu extends JCustomFrame implements ActionListener{
  
  public JMainMenu(){
    //Super Name
    super("Main Menu");
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
    JLabel title = new JLabel("Log Entries");
    JLabel text1 = new JLabel("Log 1");
    JLabel text2 = new JLabel("Log 2");
    JLabel text3 = new JLabel("Log 3");

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

    panelTwoLogs.setBackground(Color.cyan);
    panelTwoLogs.setLayout(new GridLayout(3, 1));
    panelTwoLogs.add(title);
    panelTwoLogs.add(text1);
    panelTwoLogs.add(text2);
    panelTwoLogs.add(text3);

    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(panelOne, BorderLayout.WEST);
    mainPanel.add(panelTwo, BorderLayout.CENTER);

    //Adding to Frame
    add(mainPanel);
  }

  //Code for setting up action listeners

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
}
}

