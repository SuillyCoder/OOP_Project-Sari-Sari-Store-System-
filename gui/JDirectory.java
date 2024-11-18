package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import classes.JCustomFrame;

public class JDirectory extends JCustomFrame implements ActionListener {
        //because I'm quirky and I want color
        Color navyBlue = new Color(0,0,128);
    
        //Panel Declaration 
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel center = new JPanel();
    
        //Label Declaration 
        JLabel intro = new JLabel("Directory");
        JLabel list = new JLabel ("Debtor list");
        JLabel changeLabel = new JLabel("Change Max Debt");

        //Text Field Declaration
        JTextField change = new JTextField(15);

        //Button Declaration
        JButton back = new JButton("Back");

        //Panel for the text field
        JPanel changePanel = new JPanel();


    public JDirectory() {

        super("Directory");
              //beautification 
              Font labelFont = new Font ("Arial", Font.BOLD, 24);
              intro.setFont(labelFont);
              intro.setForeground(Color.white);

              back.setPreferredSize(new Dimension(150, 50));
              
        //Adding the Panels and setting the Layout
        changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
        changePanel.add(changeLabel);
        changePanel.add(change);
            
        bottom.setBackground(navyBlue);
        bottom.setLayout(new FlowLayout());
        bottom.add(changePanel);
        bottom.add(back);

        top.setBackground(Color.blue);
        top.add(intro);

        center.setBackground(Color.cyan);
        center.add(list);

        //Adding panels to the frame 
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        back.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
