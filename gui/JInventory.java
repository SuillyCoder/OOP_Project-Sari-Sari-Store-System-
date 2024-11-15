package gui;

import classes.*;
import java.awt.*;
import javax.swing.*;


public class JInventory extends JCustomFrame {
    private JButton addItem = new JButton("Add Item");
    private JButton remItem = new JButton("Remove Item");
    private JButton restock = new JButton("Restock Item");
    private JButton exit = new JButton("Exit");

    private JTextArea itemOut = new JTextArea(20, 30);

    public JInventory(){
        super("Inventory Manager");
        Container con = getContentPane();

        // Add buttons to the left of the window
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 1));
        con.add(buttons, BorderLayout.WEST);
        buttons.add(addItem);
        buttons.add(remItem);
        buttons.add(restock);
        buttons.add(exit);

        // Add the inventory window to the right of buttons
        JPanel inventoryWindow = new JPanel();
        JScrollPane scrollPane = new JScrollPane(inventoryWindow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        con.add(scrollPane, BorderLayout.CENTER);

        // Adding the data to the inventory window
        itemOut.setText("Hello!\n".repeat(50));
        inventoryWindow.add(itemOut);
    }
}
