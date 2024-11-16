// Main submenu for inventory management

// Functionally cribbed from the TUI interface:
// [1] Add item             // Will also add quantity if already present
// [2] Remove item
// [3] Change price
// [4] Stock an item
// [D] Show all inventory   // Always present at the side of the screen
// [5] Exit

package gui.InventoryGUI;

import classes.*;
import classes.group.Stock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JInventory extends JCustomFrame implements ActionListener {
    private JButton addItem = new JButton("Add Item");
    private JButton remItem = new JButton("Remove Item");
    private JButton changePrice = new JButton("Change Price");
    private JButton restock = new JButton("Restock Item");
    private JButton exit = new JButton("Return to Main Menu");

    private JTextArea inventoryList = new JTextArea();

    private Stock stock;

    // Constructor for the inventory management window
    // Must call setStock after instantiation
    public JInventory(){
        super("Inventory Manager");
        Container con = getContentPane();

        // Add buttons to the left of the window
        JPanel buttons = new JPanel();

            buttons.setLayout(new GridLayout(5, 1));
                buttons.add(addItem);
                buttons.add(remItem);
                buttons.add(changePrice);
                buttons.add(restock);
                buttons.add(exit);
        
        con.add(buttons, BorderLayout.WEST);

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

        // Add action listeners
        addItem.addActionListener(this);
        remItem.addActionListener(this);
        changePrice.addActionListener(this);
        restock.addActionListener(this);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItem) {
            JInventoryAdd submenu = new JInventoryAdd(stock, this);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == remItem) {
            JInventoryRemove submenu = new JInventoryRemove(stock, this);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == changePrice) {
            JInventoryChange submenu = new JInventoryChange(stock, this);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == restock) {
            JInventoryRestock submenu = new JInventoryRestock(stock, this);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == exit) {
            this.dispose();
        }
    }

    public void updateText(){
        inventoryList.setText(stock.toString());
    }

    public void setStock(Stock stock){
        this.stock = stock;
    }
}
