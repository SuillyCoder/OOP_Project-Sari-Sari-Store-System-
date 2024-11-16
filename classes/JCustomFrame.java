package classes;

import java.awt.*;
import javax.swing.*;

public class JCustomFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public JCustomFrame(String menuTitle) {
        // Defaults of all frames in the system
        super("Sari-sari Store Management System");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set layout of frame as BorderLayout
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        // Create a label with the menu title at top-center of the frame
        JLabel label = new JLabel(menuTitle);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        con.add(label, BorderLayout.NORTH);     // Add the label to the top of the frame
    }
}