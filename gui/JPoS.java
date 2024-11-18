package gui;

import classes.JCustomFrame;
import classes.group.*;
import classes.indiv.*;
import gui.PoSGUI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPoS extends JCustomFrame implements ActionListener {
    private JButton addCart = new JButton("Add to cart");
    private JButton remCart = new JButton("Remove item from cart");
    private JButton finalize = new JButton("Finalize transaction");
    private JButton cancel = new JButton("Cancel transaction");

    private Transaction cart;
    private Stock stock;
    private Contacts contacts;
    private History history;

    private JTextArea cartList = new JTextArea();
    private JLabel priceTicker = new JLabel();

    public JPoS(History history, Contacts contacts, Stock stock){
        super("Transaction");
        this.stock = stock;
        this.contacts = contacts;
        this.history = history;
        this.cart = new Transaction("NO_NAME", history.size());
        Container con = getContentPane();

        // Add buttons to the left of the window
        JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(4, 1));
                buttons.add(addCart);
                buttons.add(remCart);
                buttons.add(finalize);
                buttons.add(cancel);

        con.add(buttons, BorderLayout.WEST);

        // Add cart details to the center of the window
        JPanel cartWindow = new JPanel(new BorderLayout());

            // Header for the cart list
            JLabel header = new JLabel("Current Cart: ");
                header.setFont(new Font("Arial", Font.BOLD, 20));
            
            // Scrollable cart list
            JPanel cartPane = new JPanel();
            JScrollPane scroll = new JScrollPane(cartPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                cartPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                cartList.setColumns(50);
                cartList.setText("Empty Default\n".repeat(50));

                cartPane.add(cartList);

            // Total price at the bottom
            JPanel subtotalPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel subtotalText = new JLabel("Subtotal: ");
                subtotalText.setFont(new Font("Arial", Font.PLAIN, 15));
                priceTicker.setFont(new Font("Arial", Font.PLAIN, 15));

                subtotalPane.add(subtotalText);
                subtotalPane.add(priceTicker);
        
            cartWindow.add(header, BorderLayout.NORTH);
            cartWindow.add(scroll, BorderLayout.CENTER);
            cartWindow.add(subtotalPane, BorderLayout.SOUTH);
        
        con.add(cartWindow, BorderLayout.CENTER);

        // Add action listeners
        addCart.addActionListener(this);
        remCart.addActionListener(this);
        finalize.addActionListener(this);
        cancel.addActionListener(this);

        this.updateText();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addCart){
            // Add item to cart
            JPoSAdd submenu = new JPoSAdd(this, stock, cart);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if(e.getSource() == remCart){
            // Remove item from cart
            JPoSRemove submenu = new JPoSRemove(this, stock, cart);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if(e.getSource() == finalize){
            // Finalize transaction
            JPoSFinalize submenu = new JPoSFinalize(this, history, contacts, cart);
            submenu.updateText();
            submenu.setVisible(true);
            this.setVisible(false);

        } else if(e.getSource() == cancel){
            JMainMenu mainMenu = new JMainMenu(history, stock, contacts);
            // Cancel transaction
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this transaction?", "Cancel Transaction", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                for (Item item : cart.getItems().values()) {
                    stock.get(item.getName()).incQuantity(item.getQuantity());
                }
                returnToMenu();
                JOptionPane.showMessageDialog(this, "Transaction cancelled!");
                mainMenu.setVisible(true);
                
            }
        }
    }

    public void updateText(){
        cartList.setText(cart.toString());
        priceTicker.setText("P" + cart.getWorth());
    }

    public void returnToMenu(){
        this.dispose();
        this.setVisible(false);
    }
}
