package gui.PoSGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import classes.JCustomFrame;
import classes.group.Contacts;
import classes.group.History;
import classes.indiv.Customer;
import classes.indiv.Transaction;
import gui.JPoS;

public class JPoSFinalize extends JCustomFrame implements ActionListener, DocumentListener {
    private JPoS parentFrame;
    private History history;
    private Contacts contacts;
    private Transaction cart;

    protected JTextField customerName = new JTextField(20);
    protected JTextField payment = new JTextField(20);

    protected JButton confirmButton = new JButton("Checkout");
    protected JButton cancelButton = new JButton("Return");

    protected JTextArea cartList = new JTextArea();
    protected JTextArea customerList = new JTextArea();

    public JPoSFinalize(JPoS parentFrame, History history, Contacts contacts, Transaction cart) {
        super("Finalize Transaction");
        Container con = getContentPane();

        this.parentFrame = parentFrame;
        this.history = history;
        this.contacts = contacts;
        this.cart = cart;

        // dashboard at the center of the window
        JPanel dashboard = new JPanel(new BorderLayout());

            // cart details on the upper half
            JPanel cartDetails = new JPanel(new BorderLayout());
                JLabel cartHeader = new JLabel("Contents of Cart");
                    cartHeader.setFont(new Font("Arial", Font.BOLD, 20));
            
                JPanel cartPane = new JPanel();
                JScrollPane scroll = new JScrollPane(cartPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    cartList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                    cartList.setColumns(50);
                    cartList.setText("Empty Default\n".repeat(50));
                    cartPane.add(cartList);
                
                cartDetails.add(cartHeader, BorderLayout.NORTH);
                cartDetails.add(scroll, BorderLayout.CENTER);
            
            // customer list on the lower half
            JPanel customerDetails = new JPanel(new BorderLayout());
                JLabel customerHeader = new JLabel("Customer List");
                    customerHeader.setFont(new Font("Arial", Font.BOLD, 20));
            
                JPanel customerPane = new JPanel();
                JScrollPane scroll2 = new JScrollPane(customerPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    customerList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                    customerList.setColumns(50);
                    customerList.setText("Empty Default\n".repeat(50));
                    customerPane.add(customerList);
                
                customerDetails.add(customerHeader, BorderLayout.NORTH);
                customerDetails.add(scroll2, BorderLayout.CENTER);
        
            dashboard.add(cartDetails, BorderLayout.NORTH);
            dashboard.add(customerDetails, BorderLayout.CENTER);

        con.add(dashboard, BorderLayout.CENTER);

        // Fields at the bottom of the window
        JPanel fields = new JPanel(new BorderLayout());
            JPanel entryFields = new JPanel(new GridLayout(2, 1));
                JPanel customerField = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel customerNameLabel = new JLabel("Customer Name:");
                    customerNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    customerField.add(customerNameLabel);
                    customerField.add(customerName);

                JPanel paymentField = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel paymentLabel = new JLabel("Payment:");
                    paymentLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    paymentField.add(paymentLabel);
                    paymentField.add(payment);
            
            entryFields.add(customerField);
            entryFields.add(paymentField);
        
            JPanel buttonFields = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonFields.add(confirmButton);
                buttonFields.add(cancelButton);
            
            fields.add(entryFields, BorderLayout.WEST);
            fields.add(buttonFields, BorderLayout.EAST);
            
        con.add(fields, BorderLayout.SOUTH);

        // Add action listeners
        customerName.getDocument().addDocumentListener(this);
        customerName.addActionListener(this);
        payment.addActionListener(this);
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerName) {
            confirm();
        } else if (e.getSource() == payment) {
            confirm();
        } else if (e.getSource() == confirmButton) {
            confirm();
        } else if (e.getSource() == cancelButton) {
            this.dispose();
            parentFrame.updateText();
            parentFrame.setVisible(true); // Make the parent frame visible again
        }
    }

    public void updateText() {
        cartList.setText(cart.toString());
        handleTextChange();
    }

    @Override public void insertUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void changedUpdate(DocumentEvent e) { handleTextChange(); }
    @Override public void removeUpdate(DocumentEvent e) { handleTextChange(); }
    private void handleTextChange(){
        String search = getCustomerName();
        customerList.setText(contacts.search(search));
    }

    public void confirm() {
        String customerName = getCustomerName();
        double payment = getPayment();

        Customer customer;
        double outstanding, revenue;

        // Failure cases
        // if any field is empty
            if (customerName.equals("") || payment == 0.0) {
                JOptionPane.showMessageDialog(this, "Enter all fields properly first!");
                return;
            }
        
        // Otherwise
        // customer from customerName, either existing or new
        if (contacts.containsKey(customerName)) {
            customer = contacts.get(customerName);
        } else {
            customer = new Customer(customerName);
            contacts.put(customerName, customer);
        }

        // payment calculations
        outstanding = customer.getCredit() - cart.getWorth();
        revenue = outstanding + payment;

        // if payment is not enough such that the customer exceeds the maximum allowable debt
        if (Contacts.getMaxDebt() > revenue) {
            JOptionPane.showMessageDialog(this, "Current transaction will exceed maximum allowable debt!\nCannot Finalize transaction!");
            return;
        }

        // otherwise the transaction is approved

        // if the customer has paid off the debt
        if (revenue == 0) {
            contacts.remove(customerName);
        
        // if the customer has some remaining balance (either debt or advance)
        } else {
            customer.setCredit(revenue);
            customer.setDate(cart.getDate());
        }

        cart.setCustomer(customerName);
        cart.setPayment(payment);

        history.addTransaction(cart);

        // return to parent frame
        this.dispose();
        parentFrame.returnToMenu();
        JOptionPane.showMessageDialog(this, "Transaction finalized.");
    }

    public String getCustomerName() {
        try {
            return customerName.getText();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public double getPayment() {
        try {
            return Double.parseDouble(payment.getText());

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
