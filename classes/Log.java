// Class which contains a financial report / summary for a single day
// Used ArrayList for keeping track of multiple reports

package classes;

import java.util.ArrayList;

public class Log implements Cloneable {
    private double totalPayment;
    private double totalWorth;

    public double getTotalPayment() { return this.totalPayment; }
    public double getTotalWorth() { return this.totalWorth; }

    public void addTransaction(Transaction transaction) {
        this.totalPayment += transaction.getPayment();
        this.totalWorth += transaction.getWorth();
    }

    //Creating a clone of the log
    @Override
    public Log clone() {
        Log clonedLog = new Log();
        clonedLog.totalPayment = this.totalPayment;
        clonedLog.totalWorth = this.totalWorth;
        return clonedLog;
    }

    // file read and write operations
    public static void fromFile(ArrayList<Log> logHistory) {
        // implement code here
    }

    public void toFile(ArrayList<Log> logHistory){
        // implement code here
    }
}
