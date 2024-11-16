package gui.InventoryGUI;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import classes.JItemSelector;
import classes.group.Stock;
import classes.indiv.Item;

public class JInventoryAdd extends JItemSelector {
    private JInventory parentFrame;

    public JInventoryAdd(Stock stock, JInventory parentFrame) {
        super("Add Item", stock, true, true, true);
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
        String itemCategory = getItemCategory();
        int itemQuantity = getItemQuantity();
        double itemPrice = getItemPrice();

        Item newItem;

        if (itemName.equals("Default") || itemCategory.equals("Default") || itemQuantity == 0 || itemPrice == 0.0) {
            JOptionPane.showMessageDialog(this, "Enter all fields first!");
            return;
        }

        // if item already exists, add quantity
        if (stock.containsKey(itemName)) {
            Item item = stock.get(itemName);
            newItem = new Item(itemName, item.getCategory(), item.getPrice(), itemQuantity + item.getQuantity());
            JOptionPane.showMessageDialog(this, "Item restocked successfully");
        } else {
            // if item does not exist, create new item
            newItem = new Item(itemName, itemCategory, itemPrice, itemQuantity);
            JOptionPane.showMessageDialog(this, "Item added successfully");
        }

        stock.put(itemName, newItem);
        this.updateText();
    }
}
