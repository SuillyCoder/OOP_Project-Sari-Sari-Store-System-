// Submenu for changing the price of an item in the inventory (stock)

package gui.InventoryGUI;

import java.awt.event.*;
import javax.swing.*;

import classes.group.Stock;
import gui.JInventory;

public class JInventoryChange extends JItemSelector {
    private JInventory parentFrame;

    public JInventoryChange(JInventory parentFrame, Stock stock) {
        super("Change price of item", stock, false, false, true);
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // When user confirms of adding the item
        // By pressing enter on any field
        if (e.getSource() == itemName) { confirm();
        } else if (e.getSource() == itemPrice) { confirm();
        
        // By pressing the confirm button
        } else if (e.getSource() == confirmButton) { confirm();

        // Return to Inventory submenu
        } else if (e.getSource() == cancelButton) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true);
        }
    }

    // When user confirms of adding the item
    public void confirm() {
        // Get item details from user
        String itemName = getItemName();
        double itemPrice = getItemPrice();

        // If any field is empty or invalid
        if (itemName.equals("") || itemPrice == 0.0) {
            JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
            return;
        }

        // if item exists, then change price
        if (stock.containsKey(itemName)) {
            stock.get(itemName).setPrice(itemPrice);
        
            this.updateText();
            JOptionPane.showMessageDialog(this, "Price changed: " + itemName + " to " + itemPrice + " successfully!");

        // otherwise notify that item does not exist
        } else {
            JOptionPane.showMessageDialog(this, itemName +  " not found!");
        }
    }
}
