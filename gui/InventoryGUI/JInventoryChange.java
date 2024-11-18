// Submenu for changing the price of an item in the inventory (stock)

package gui.InventoryGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import classes.JItemSelector;
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
        if (e.getSource() == itemName) {
            confirm();
            
        } else if (e.getSource() == itemPrice) {
            confirm();

        } else if (e.getSource() == confirmButton) {
            confirm();

        } else if (e.getSource() == cancelButton) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true); // Make the parent frame visible again
        }
    }

    public void confirm() {
        String itemName = getItemName();
        double itemPrice = getItemPrice();

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
