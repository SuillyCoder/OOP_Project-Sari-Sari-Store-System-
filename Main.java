import classes.group.*;
import classes.indiv.Log;
import gui.*;

public class Main{
    // Data declarations and initializations
    public static Contacts contacts = new Contacts();
    public static History history = new History();
    public static Stock stock = new Stock();

    // "boostrapping"
    // functionality is in JMainMenu.java
    public static void main(String[] args) {   
        // load from files
        contacts.fromFile();            // (Contacts.java)
        history.fromFile();             // (History.java)
        stock.fromFile();               // (Stock.java)        
        
        // If no logs were loaded, add a new log for the first day
        if (history.isEmpty()) {        
            history.add(new Log());
        }

        // GUI initialization
        JMainMenu mainMenu = new JMainMenu(history, stock, contacts);
        mainMenu.updateText();
        mainMenu.setVisible(true);

        // debugging code
        System.out.println(contacts);
        System.out.println(stock);
        System.out.println(history);
        System.out.println(stock.lowInventoryNotifier());

        // // Save data to files
        // stock.toFile();
        // contacts.toFile();
        // history.toFile();
    }
}
