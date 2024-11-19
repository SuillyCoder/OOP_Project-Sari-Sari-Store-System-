package gui.PoSGUI;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import classes.JCustomFrame;
import classes.group.Stock;
import classes.indiv.Transaction;
import gui.JPoS;

public class JCartSelector extends JCustomFrame implements ActionListener, DocumentListener {
    private static final int DISPLAY_STOCK 1;
    private static final int DISPLAY_CART 2;
    
    protected JTextField itemName = new JTextField(20);
    protected JTextField itemQuantity = new JTextField(20);

    protected JButton confirmButton = new JButton("Confirm");
    protected JButton cancelButton = new JButton("Return");

    protected JTextArea itemList = new JTextArea();

    private Stock stock;
    private Transaction cart;
    private JPoS parentFrame;




}
