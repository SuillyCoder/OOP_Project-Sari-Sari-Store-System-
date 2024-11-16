// Submenu for removing items from the inventory (stock)

package gui.InventoryGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import classes.JItemSelector;
import classes.group.Stock;

public class JInventoryRemove extends JItemSelector {
    private JInventory parentFrame;

    public JInventoryRemove(Stock stock, JInventory parentFrame) {
        super("Remove Item", stock, false, false, false);
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            confirm();

        } else if (e.getSource() == cancelButton) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true); // Make the parent frame visible again
        }
    }

    public void confirm() {
        String itemName = getItemName();

        if (itemName.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter an item name first!");
            return;
        }

        // if item already exists, remove from stock
        if (stock.containsKey(itemName)) {
            stock.remove(itemName);
            
            this.updateText();
            JOptionPane.showMessageDialog(this, itemName + " removed successfully!");

        // otherwise notify that item does not exist
        } else {
            JOptionPane.showMessageDialog(this, itemName +  " not found!");
        }
    }
}
