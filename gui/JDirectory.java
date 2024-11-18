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
        JLabel maxDebtLabel = new JLabel("Blank default");

        private JMainMenu parentFrame;
        private Contacts contacts;


    public JDirectory(JMainMenu parentFrame, Contacts contacts) {
        super("Directory");
        this.parentFrame = parentFrame;
        this.contacts = contacts;

        // Contacts list at the center of the window, with current max debt at the bottom
        JPanel center = new JPanel(new BorderLayout());
            // header
            JLabel header = new JLabel ("Debtor list");
                header.setFont(new Font("Arial", Font.BOLD, 20));
                header.setForeground(Color.black);

            // Scrollable contacts list
            JPanel directoryWindow = new JPanel();
            JScrollPane scroll = new JScrollPane(directoryWindow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                directoryList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                directoryList.setColumns(50);
                directoryList.setText("Empty Default\n".repeat(50));
                directoryWindow.add(directoryList);
            
            // Current max debt at the bottom
            JPanel debtInfo = new JPanel(new FlowLayout());
                JLabel debtHeader = new JLabel("Current Max Debt: ");
                    debtHeader.setFont(new Font("Arial", Font.BOLD, 20));
                debtInfo.add(debtHeader);
                debtInfo.add(maxDebtLabel);
            
            center.add(header, BorderLayout.NORTH);
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
        back.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
