package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import classes.JCustomFrame;
import classes.group.*;
import classes.indiv.*;

public class JPoS extends JCustomFrame {
    private JButton addCart = new JButton("Add to cart");
    private JButton remCart = new JButton("Remove item from cart");
    private JButton finalize = new JButton("Finalize transaction");
    private JButton cancel = new JButton("Cancel transaction");

    private Transaction cart;

    private JTextArea cartList = new JTextArea();
    private JLabel priceTicker = new JLabel();

    public JPoS(History history, Contacts contacts, Stock stock){
        super("Transaction");
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
    }
}
