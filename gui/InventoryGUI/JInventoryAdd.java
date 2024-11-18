// Submenu for adding items to the inventory (stock)

package gui.InventoryGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import classes.JItemSelector;
import classes.group.Stock;
import classes.indiv.Item;
import gui.JInventory;

public class JInventoryAdd extends JItemSelector {
    private JInventory parentFrame;

    public JInventoryAdd(JInventory parentFrame, Stock stock) {
        super("Add Item", stock, true, true, true);
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemName) {
            confirm();

        } else if (e.getSource() == itemCategory) {
            confirm();

        } else if (e.getSource() == itemQuantity) {
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
        String itemCategory = getItemCategory();
        int itemQuantity = getItemQuantity();
        double itemPrice = getItemPrice();

        Item newItem;

        if (itemName.equals("") || itemCategory.equals("") || itemQuantity == 0 || itemPrice == 0.0) {
            JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
            return;
        }

        // if item already exists, add quantity
        if (stock.containsKey(itemName)) {
            Item item = stock.get(itemName);
            newItem = new Item(itemName, item.getCategory(), item.getPrice(), itemQuantity + item.getQuantity());
            stock.put(itemName, newItem);
            this.updateText();
            JOptionPane.showMessageDialog(this, "Item restocked successfully");
        
        // otherwise, create new item
        } else {
            newItem = new Item(itemName, itemCategory, itemPrice, itemQuantity);
            stock.put(itemName, newItem);
            this.updateText();
            JOptionPane.showMessageDialog(this, "Item added successfully");
        }
    }
}
