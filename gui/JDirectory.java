package gui;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import classes.JCustomFrame;
import classes.group.Contacts;

public class JDirectory extends JCustomFrame implements ActionListener {
        //because I'm quirky and I want color
        Color navyBlue = new Color(0,0,128);
    
        JTextField changeField = new JTextField(15);

        JButton back = new JButton("Back");

        JTextArea directoryList = new JTextArea();
        JLabel customerSize = new JLabel("Blank default");
        JLabel maxDebtLabel = new JLabel("Blank default");

        private JMainMenu parentFrame;
        private Contacts contacts;


    public JDirectory(JMainMenu parentFrame, Contacts contacts) {
        super("Directory");
        this.parentFrame = parentFrame;
        this.contacts = contacts;

        // Contacts list at the center of the window, with current max debt at the bottom
        JPanel center = new JPanel(new BorderLayout());
            // Header for the contacts list
            JPanel headerPanel = new JPanel(new BorderLayout());
                JLabel header = new JLabel ("Debtors list");
                    header.setFont(new Font("Arial", Font.BOLD, 20));
                
                JPanel customerInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel customerHeader = new JLabel("Number of Customers: ");
                    customerInfo.add(customerHeader);
                    customerInfo.add(customerSize);
                
                headerPanel.add(header, BorderLayout.NORTH);
                headerPanel.add(customerInfo, BorderLayout.SOUTH);

            // Scrollable contacts list
            JPanel directoryWindow = new JPanel();
            JScrollPane scroll = new JScrollPane(directoryWindow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                directoryList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                directoryList.setColumns(50);
                directoryList.setText("Empty Default\n".repeat(50));
                directoryList.setEditable(false);
                directoryWindow.add(directoryList);
            
            // Current max debt at the bottom
            JPanel debtInfo = new JPanel(new FlowLayout());
                JLabel debtHeader = new JLabel("Current Max Debt: ");
                    debtHeader.setFont(new Font("Arial", Font.BOLD, 20));
                debtInfo.add(debtHeader);
                debtInfo.add(maxDebtLabel);
            
            center.add(headerPanel, BorderLayout.NORTH);
            center.add(scroll, BorderLayout.CENTER);
            center.add(debtInfo, BorderLayout.SOUTH);
            center.setBackground(Color.cyan);
        
        this.add(center, BorderLayout.CENTER);

        // Bottom panel for changing max debt and exiting
        JPanel bottom = new JPanel();
            JPanel changePanel = new JPanel();
                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                JLabel changeLabel = new JLabel("Change Max Debt");
                changePanel.add(changeLabel);
                changePanel.add(changeField);
            

                back.setPreferredSize(new Dimension(150, 50));

            bottom.add(changePanel);
            bottom.add(back);
            bottom.setBackground(navyBlue);
            bottom.setLayout(new FlowLayout());
      
        this.add(bottom, BorderLayout.SOUTH);

        // Add action listeners
        changeField.addActionListener(this);
        back.addActionListener(this);

        updateText();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeField) {
            try {
                double newDebt = Double.parseDouble(changeField.getText());
                Contacts.setMaxDebt(newDebt);
                updateText();
                JOptionPane.showMessageDialog(this, "Max debt changed to P" + (-1 * Contacts.getMaxDebt()) + " successfully!");

            } catch (NumberFormatException ex) {
                changeField.setText("Invalid input");
            }

        } else if (e.getSource() == back) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true);
        }
    }

    public void updateText() {
        directoryList.setText(contacts.toString());
        customerSize.setText(contacts.size() + "");
        maxDebtLabel.setText("P" + (-1 * Contacts.getMaxDebt()));
    }
}
