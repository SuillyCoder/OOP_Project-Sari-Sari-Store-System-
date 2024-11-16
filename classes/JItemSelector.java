package classes;

import classes.group.Stock;

import java.awt.*;
import javax.swing.*;

public class JItemSelector extends JCustomFrame {

    private JTextField itemName = new JTextField(20);
    private JTextField itemQuantity = new JTextField(20);

    private JButton addButton = new JButton("Add Item");
    private JButton cancelButton = new JButton("Cancel");

    private JTextArea inventoryList = new JTextArea();

    public JItemSelector(String title) {
        super("Inventory List");
        Container con = getContentPane();

        // Inventory list at the center of the window
        JPanel inventoryWindow = new JPanel(new BorderLayout());

            JLabel header = new JLabel("Current Inventory");
                header.setFont(new Font("Arial", Font.BOLD, 20));

            JPanel itemWindow = new JPanel();
                JScrollPane scroll = new JScrollPane(itemWindow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                inventoryList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                inventoryList.setColumns(50);
                inventoryList.setText("Empty Default\n".repeat(50));

                itemWindow.add(inventoryList);

            inventoryWindow.add(header, BorderLayout.NORTH);
            inventoryWindow.add(itemWindow, BorderLayout.CENTER);
            
        con.add(inventoryWindow, BorderLayout.CENTER);

        // Item selection at the bottom of the window
        JPanel itemSelection = new JPanel(new BorderLayout());

            JLabel description = new JLabel("Add an item to the inventory");
                description.setFont(new Font("Arial", Font.PLAIN, 15));

            JPanel fields = new JPanel(new GridLayout(2, 1));
                JPanel nameFields = new JPanel(new FlowLayout());
                    JLabel itemLabel = new JLabel("Item Name:");
                        itemLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    nameFields.add(itemLabel);
                    nameFields.add(itemName);
                
                JPanel quantityFields = new JPanel(new FlowLayout());
                    JLabel quantityLabel = new JLabel("Quantity:");
                        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    quantityFields.add(quantityLabel);
                    quantityFields.add(itemQuantity);

                fields.add(nameFields);
                fields.add(quantityFields);
            
            JPanel buttons = new JPanel(new FlowLayout());
                buttons.add(addButton);
                buttons.add(cancelButton);

            itemSelection.add(description, BorderLayout.NORTH);
            itemSelection.add(fields, BorderLayout.CENTER);
            itemSelection.add(buttons, BorderLayout.EAST);

        con.add(itemSelection, BorderLayout.SOUTH);
    }

    public void updateText(Stock stock){
        inventoryList.setText(stock.toString());
    }
}