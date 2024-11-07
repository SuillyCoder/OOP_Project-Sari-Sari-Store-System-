package classes;

import java.util.ArrayList;

public class Log implements Cloneable {
    private ArrayList<Transaction> transactions;
    private int date;
    private double totalPayment;
    private double totalWorth;

    public Log(int date) {
        this.transactions = new ArrayList<>();
        this.date = date;
        this.totalPayment = 0;
        this.totalWorth = 0;
    }


    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction.clone());
        this.totalPayment += transaction.getPayment();
        this.totalWorth += transaction.getWorth();
    }

    //Creating a clone of the log
    @Override
    public Log clone() {
        Log clonedLog = new Log(this.date);
        for (Transaction transaction : this.transactions) {
            clonedLog.addTransaction(transaction.clone());
        }
        clonedLog.totalPayment = this.totalPayment;
        clonedLog.totalWorth = this.totalWorth;
        return clonedLog;
    }

    public int getDate() {
        return this.date;
    }

    public double getPayment() {
        return this.totalPayment;
    }

    public double getWorth() {
        return this.totalWorth;
    }

    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}
