// Submenu for restocking items in the inventory (stock)

package gui.InventoryGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import classes.JItemSelector;
import classes.group.Stock;
import gui.JInventory;

public class JInventoryRestock extends JItemSelector {
    private JInventory parentFrame;

    public JInventoryRestock(Stock stock, JInventory parentFrame) {
        super("Change price of item", stock, false, true, false);
        this.parentFrame = parentFrame;
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

    public void confirm() {
        String itemName = getItemName();
        int itemQuantity = getItemQuantity();

        if (itemName.equals("") || itemQuantity == 0) {
            JOptionPane.showMessageDialog(this, "Enter an item name first!");
            return;
        }

        // if item exists, add to quantity
        if (stock.containsKey(itemName)) {
            stock.get(itemName).incQuantity(itemQuantity);

            this.updateText();
            JOptionPane.showMessageDialog(this, "Increased amount of " + itemName + " by " + itemQuantity + " successfully!");

        // otherwise notify that item does not exist
        } else {
            JOptionPane.showMessageDialog(this, itemName +  " not found!");
        }
    }
}
