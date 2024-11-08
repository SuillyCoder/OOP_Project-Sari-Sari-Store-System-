package classes;

import java.util.ArrayList;

public class Log implements Cloneable {
    private double totalPayment;
    private double totalWorth;

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


    public double getPayment() {
        return this.totalPayment;
    }

    public double getWorth() {
        return this.totalWorth;
    }

    public static void fromFile(ArrayList<Log> logHistory) {
        // implement code here
    }

    public void toFile(ArrayList<Log> logHistory){
        // implement code here
    }
}
