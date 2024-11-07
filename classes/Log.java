package classes;

import java.util.ArrayList;

public class Log {
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

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}
