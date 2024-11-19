package gui;

import java.awt.*;
import javax.swing.*;

//class for constant values for the GUI's in the project
public class Theme {
    
    public static final Font HEADER_FONT = new Font("Arial", Font.BOLD,24 );

    public static final Dimension BUTTON_SIZE = new Dimension(150, 50);

    public static final Color BUTTON_RED = new Color(255, 0,0);
    public static final Color BUTTON_GREEN = new Color(0, 128,0);
    
    public static final Color NAVY_BLUE = new Color(0,0,128);


    //BACKGROUND_COLOR
    //public static final Color BACKGROUND_COLOR = new Color(0, 0, 0); (To be determined color)
    //TEXTFIELD_SIZE
    //public static final Dimension TEXTFIELD_SIZE = new Dimension (200,30)

    //SetToolTipText (not sure since idk the functions of all buttons so far, will add later on sa GUI)
    public static void setToolTipText(JComponent component, String text) {
        component.setToolTipText(text);
    }
}
