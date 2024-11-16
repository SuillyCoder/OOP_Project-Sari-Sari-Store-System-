package classes;

import classes.group.Stock;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JItemSelector extends JCustomFrame implements ActionListener {

    private JTextField itemName = new JTextField(20);
    private JTextField itemCategory = new JTextField(20);
    private JTextField itemQuantity = new JTextField(20);
    private JTextField itemPrice = new JTextField(20);

    protected JButton confirmButton = new JButton("Confirm");
    protected JButton cancelButton = new JButton("Return");

    private JTextArea inventoryList = new JTextArea();

    protected Stock stock;

    public JItemSelector(String description, Stock stock, boolean enableCategory, boolean enableQuantity, boolean enablePrice) {
        super("Inventory List");
        Container con = getContentPane();

        this.stock = stock;

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
            inventoryWindow.add(scroll, BorderLayout.CENTER);
            
        con.add(inventoryWindow, BorderLayout.CENTER);

        // Item selection at the bottom of the window
        JPanel itemSelection = new JPanel(new BorderLayout());

            // Description at the top of the frame
            JLabel descriptionBox = new JLabel(description);
            descriptionBox.setFont(new Font("Arial", Font.PLAIN, 20));

            // Fields at the bottome of the frame
            JPanel fields = new JPanel(new GridLayout(1+ (enableCategory ? 1 : 0) + (enableQuantity ? 1 : 0) + (enablePrice ? 1 : 0), 1));
                // Always able to input item name
                JPanel nameFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel itemLabel = new JLabel("Item Name:");
                            itemLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                        nameFields.add(itemLabel);
                        nameFields.add(itemName);
                    fields.add(nameFields);

                if (enableCategory) {
                    JPanel categoryFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel categoryLabel = new JLabel("Category:");
                            categoryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                        categoryFields.add(categoryLabel);
                        categoryFields.add(itemCategory);
                    fields.add(categoryFields);
                }
                
                if (enableQuantity) {
                    JPanel quantityFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel quantityLabel = new JLabel("Quantity:");
                            quantityLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                        quantityFields.add(quantityLabel);
                        quantityFields.add(itemQuantity);
                    fields.add(quantityFields);
                }

                if (enablePrice) {
                    JPanel priceFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel priceLabel = new JLabel("Price:");
                            priceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                        priceFields.add(priceLabel);
                        priceFields.add(itemPrice);
                    fields.add(priceFields);
                }
            
            // buttons at the right of the frame
            JPanel buttons = new JPanel(new FlowLayout());
                buttons.add(confirmButton);
                buttons.add(cancelButton);

            itemSelection.add(descriptionBox, BorderLayout.NORTH);
            itemSelection.add(fields, BorderLayout.CENTER);
            itemSelection.add(buttons, BorderLayout.EAST);

        con.add(itemSelection, BorderLayout.SOUTH);

        // Add action listeners
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            JOptionPane.showMessageDialog(this, "Confirm button pressed");


        } else if (e.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(this, "Cancel button pressed");
        }
    }

    public void updateText(){
        inventoryList.setText(stock.toString());
    }

    public String getItemName() {
        try {
            return itemName.getText();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getItemCategory() {
        try {
            return itemCategory.getText();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getItemQuantity() {
        try {
            return Integer.parseInt(itemQuantity.getText());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getItemPrice() {
        try {
            return Double.parseDouble(itemPrice.getText());

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}