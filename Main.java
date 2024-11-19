import classes.group.*;
import classes.indiv.Log;
import gui.*;

public class Main{

    public static Contacts contacts = new Contacts();
    public static Stock stock = new Stock();
    public static History history = new History();

    public static void main(String[] args) {   
        // load from files
        contacts.fromFile();            // (Customer.java)
        stock.fromFile();               // (Stock.java)
        history.fromFile();             // (Log.java)
        
        if (history.isEmpty()) {        // If no logs are loaded, add a new log for the first day
            history.add(new Log());
        }

        // Main menu GUI

        JMainMenu mainMenu = new JMainMenu(history, stock, contacts);
        mainMenu.updateText();
        mainMenu.setVisible(true);

        //JItemSelector test = new JItemSelector("Add Item");
        //test.updateText(stock);
        //test.setVisible(true);

        // debug code
        System.out.println(contacts);
        System.out.println(stock);
        System.out.println(history);
        System.out.println(stock.lowInventoryNotifier());

        // // Remove the last log if no transactions were made
        // if (history.get(currentDay-1).getTotalWorth() == 0 && history.get(currentDay-1).getTotalPayment() == 0) {
        //     history.remove(currentDay-1);    // Remove the last log if no transactions were made
        // }

        // // Save data to files
        // stock.toFile();
        // contacts.toFile();
        // history.toFile();
    }


}
