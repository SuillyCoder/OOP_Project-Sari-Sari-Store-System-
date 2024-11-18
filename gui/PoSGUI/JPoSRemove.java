package gui.PoSGUI;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import classes.JItemSelector;
import classes.NamedMap;
import classes.group.Stock;
import classes.indiv.Item;
import classes.indiv.Transaction;
import gui.JPoS;

public class JPoSRemove extends JItemSelector implements DocumentListener {

    private Transaction cart;
    private JPoS parentFrame;
    
    
    public JPoSRemove(Stock stock, Transaction cart, JPoS parentFrame) {
        super("Remove Item from cart", cart.toStock(), false, true, false);
        this.cart = cart;
        this.stock = stock;
        this.parentFrame = parentFrame;

        this.updateText();
        this.setVisible(true);

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
        Item cartItem;

        // Failure cases
            // if any field is empty
            if (itemName.equals("") || itemQuantity == 0) {
                JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
                return;
            }

            // if item is not in cart
            if (!cartItems.containsKey(itemName)) {
                JOptionPane.showMessageDialog(this, "Item not in cart!");
                return;
            }
        
        // otherwise, remove item from cart
        cartItem = cartItems.get(itemName);

        if (cartItem.getQuantity() < itemQuantity) {
            itemQuantity = cartItem.getQuantity();
        }

        cartItem.setQuantity(cartItem.getQuantity() - itemQuantity);
        if (cartItem.getQuantity() == 0) {
            cartItems.remove(itemName);
        }
        if (stock.containsKey(itemName)) {
            Item stockItem = stock.get(itemName);
            stockItem.setQuantity(stockItem.getQuantity() + itemQuantity);
        } else {
            stock.put(itemName, new Item(itemName, cartItem.getCategory(), cartItem.getPrice(), itemQuantity));
        }

        this.handleTextChange();

        JOptionPane.showMessageDialog(this, "Item removed from cart successfully");
    }

    @Override public void insertUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void changedUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void removeUpdate(DocumentEvent e) { handleTextChange(); }
    private void handleTextChange(){
        String search = itemName.getText();
        inventoryList.setText(cart.toStock().search(search));
    }

    @Override
    public void updateText() {
        inventoryList.setText(cart.toStock().toString());
    }
}
