package gui.PoSGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import classes.JCustomFrame;
import classes.NamedMap;
import classes.group.Stock;
import classes.indiv.Item;
import classes.indiv.Transaction;
import gui.JPoS;
import gui.InventoryGUI.JItemSelector;

public class JPoSAdd extends JCustomFrame implements ActionListener, DocumentListener {

    protected JTextField itemName = new JTextField(20);
    protected JTextField itemQuantity = new JTextField(20);

    protected JButton confirmButton = new JButton("Confirm");
    protected JButton cancelButton = new JButton("Return");

    protected JTextArea inventoryList = new JTextArea();

    private Stock stock;
    private Transaction cart;
    private JPoS parentFrame;
    
    
    public JPoSAdd(JPoS parentFrame, Stock stock, Transaction cart) {
        super("Point of Sale");
        Container con = getContentPane();
        this.parentFrame = parentFrame;
        this.stock = stock;
        this.cart = cart;

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
            JLabel descriptionBox = new JLabel("Remove item from cart");
            descriptionBox.setFont(new Font("Arial", Font.PLAIN, 20));

            // Fields at the bottome of the frame
            JPanel fields = new JPanel(new GridLayout(2, 1));
                // Always able to input item name
                JPanel nameFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel itemLabel = new JLabel("Item Name:");
                            itemLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                        nameFields.add(itemLabel);
                        nameFields.add(itemName);
                    fields.add(nameFields);

                JPanel quantityFields = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel quantityLabel = new JLabel("Quantity:");
                        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    quantityFields.add(quantityLabel);
                    quantityFields.add(itemQuantity);
                fields.add(quantityFields);
            
            // buttons at the right of the frame
            JPanel buttons = new JPanel(new FlowLayout());
                buttons.add(confirmButton);
                buttons.add(cancelButton);

            itemSelection.add(descriptionBox, BorderLayout.NORTH);
            itemSelection.add(fields, BorderLayout.CENTER);
            itemSelection.add(buttons, BorderLayout.EAST);

        con.add(itemSelection, BorderLayout.SOUTH);

        // Add action listeners
        itemName.addActionListener(this);
        itemQuantity.addActionListener(this);
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        itemName.getDocument().addDocumentListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemName) {
            confirm();
        } else if (e.getSource() == itemQuantity) {
            confirm();
        } else if (e.getSource() == confirmButton) {
            confirm();
        } else if (e.getSource() == cancelButton) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true); // Make the parent frame visible again
        }
    }

    private void confirm(){
        String itemName = getItemName();
        int itemQuantity = getItemQuantity();

        NamedMap<Item> cartItems = cart.getItems();
        Item stockedItem;
        Item newItem;

        // Failure cases
            // if any field is empty
            if (itemName.equals("") || itemQuantity == 0) {
                JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
                return;
            }

            // if item does not exist
            if (!stock.containsKey(itemName)) {
                JOptionPane.showMessageDialog(this, "Item does not exist in stock!");
                return;
            }

            stockedItem = stock.get(itemName);

            // if no stock left
            if (stockedItem.getQuantity() == 0) {
                JOptionPane.showMessageDialog(this, "No stock left for " + itemName + "!");
                return;
            }

            // if requested quantity is more than stock
            if (itemQuantity > stockedItem.getQuantity()) {
                JOptionPane.showMessageDialog(this, "Not enough stock for " + itemName + "!");
                return;
            }

            // if requested quantity is negative
            if (itemQuantity < 0) {
                JOptionPane.showMessageDialog(this, "Invalid quantity!");
                return;
            }

        stockedItem.decQuantity(itemQuantity);                  // decrement item quantity in stock
        cart.incWorth(itemQuantity * stockedItem.getPrice());   // increment transaction worth

        // if item already exists in cart
        if (cartItems.containsKey(itemName)) {
            cartItems.get(itemName).incQuantity(itemQuantity);
            this.updateText();
            JOptionPane.showMessageDialog(this, "Successfully added " + itemQuantity + " more " + itemName + " to cart.");

        // if item does not exist in cart
        } else {
            newItem = stockedItem.clone();
            newItem.setQuantity(itemQuantity);
            cartItems.put(itemName, newItem);
            this.updateText();
            JOptionPane.showMessageDialog(this, "Successfully added " + itemQuantity + " " + itemName + " to cart.");
        }
    }

    @Override public void insertUpdate(DocumentEvent e) { updateText(); }
    @Override public void changedUpdate(DocumentEvent e) { updateText(); }
    @Override public void removeUpdate(DocumentEvent e) { updateText(); }
    public void updateText(){
        String search = itemName.getText();
        inventoryList.setText(stock.search(search));
    }

    public String getItemName() {
        try {
            return itemName.getText();

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
}
