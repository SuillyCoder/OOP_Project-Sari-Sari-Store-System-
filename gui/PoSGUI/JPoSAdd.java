package gui.PoSGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import classes.JItemSelector;
import classes.NamedMap;
import classes.group.Stock;
import classes.indiv.Item;
import classes.indiv.Transaction;
import gui.JPoS;

public class JPoSAdd extends JItemSelector implements DocumentListener {

    private Transaction cart;
    private JPoS parentFrame;
    
    
    public JPoSAdd(Stock stock, Transaction cart, JPoS parentFrame) {
        super("Add Item to cart", stock, false, true, false);
        this.cart = cart;
        this.stock = stock;
        this.parentFrame = parentFrame;

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
            this.handleTextChange();
            JOptionPane.showMessageDialog(this, "Successfully added " + itemQuantity + " more " + itemName + " to cart.");

        // if item does not exist in cart
        } else {
            newItem = stockedItem.clone();
            newItem.setQuantity(itemQuantity);
            cartItems.put(itemName, newItem);
            this.handleTextChange();
            JOptionPane.showMessageDialog(this, "Successfully added " + itemQuantity + " " + itemName + " to cart.");
        }
    }

    @Override public void insertUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void changedUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void removeUpdate(DocumentEvent e) { handleTextChange(); }
    private void handleTextChange(){
        String search = itemName.getText();
        inventoryList.setText(stock.search(search));
    }
}
