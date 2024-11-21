// Submenu for ading items to cart

package gui.PoSGUI;

import javax.swing.*;

import classes.NamedMap;
import classes.group.Stock;
import classes.indiv.*;
import gui.JPoS;

public class JPoSAdd extends JCartSelector {
    public JPoSAdd(JPoS parentFrame, Stock stock, Transaction cart) {
        super(parentFrame, stock, cart, JCartSelector.DISPLAY_STOCK);
    }

    // When user confirms of the item
    @Override
    protected void confirm(){
        String itemName = getItemName();
        int itemQuantity = getItemQuantity();

        NamedMap<Item> cartItems = getCart().getItems();
        Item stockedItem;
        Item newItem;

        // Failure cases
            // if any field is empty
            if (itemName.equals("") || itemQuantity == 0) {
                JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
                return;
            }

            // if item does not exist
            if (!getStock().containsKey(itemName)) {
                JOptionPane.showMessageDialog(this, "Item does not exist in stock!");
                return;
            }

            stockedItem = getStock().get(itemName);

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
        getCart().incWorth(itemQuantity * stockedItem.getPrice());   // increment transaction worth

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
}
