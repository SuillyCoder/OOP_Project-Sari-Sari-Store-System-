import classes.group.*;
import classes.indiv.*;
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

        // debug code
        System.out.println(contacts);
        System.out.println(stock);
        System.out.println(history);
        Inventory.lowInventoryNotifier(stock);

        // // Remove the last log if no transactions were made
        // if (history.get(currentDay-1).getTotalWorth() == 0 && history.get(currentDay-1).getTotalPayment() == 0) {
        //     history.remove(currentDay-1);    // Remove the last log if no transactions were made
        // }

        // // Save data to files
        // stock.toFile();
        // contacts.toFile();
        // history.toFile();
    }

    public static void nextDay(){
        history.add(new Log());                  // A new log entry at the start of the day
    }

    public static String printDay(){
        int currentDay = history.size();
        int currentWeek = ((currentDay - 1) / 7) + 1;
        int currentMonth = ((currentDay - 1) / 30) + 1; // assuming 30 days in a month
        //Currently unsure with this formatting
        String dayLabel = ("Day " + currentDay + " (" + getWeekday(currentDay) + ") | Week " + currentWeek + " | Month " + currentMonth);
        System.out.println(dayLabel);

        return dayLabel;
    }

    public static String printWeek(){
        int currentDay = history.size();
        int currentWeek = ((currentDay - 1) / 7) + 1;
        String weekLabel = ("Week " + currentWeek);
        System.out.println(weekLabel);

        return weekLabel;
    }

    public static String printMonth(){
        int currentDay = history.size();
        int currentMonth = ((currentDay - 1) / 30) + 1; // assuming 30 days in a month
        String monthLabel = ("Month " + currentMonth);
        System.out.println(monthLabel);
        return monthLabel;
    }


    private static String getWeekday(int day) {
        day = day % 7;
        switch (day) {
            case 1:     return "Monday";
            case 2:     return "Tuesday";
            case 3:     return "Wednesday";
            case 4:     return "Thursday";
            case 5:     return "Friday";
            case 6:     return "Saturday";
            case 0:     return "Sunday";
            default:    return "Invalid day";
        }
    }
}
