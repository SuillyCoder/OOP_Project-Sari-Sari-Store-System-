package classes;

import java.util.ArrayList;

public class Log {
    private ArrayList<Transaction> reciepts;
    private int date;
    private double cashOnHand;
    private double revenue;
    private double outstanding;

    public Log(int date) {
        this.reciepts = new ArrayList<>();
        this.date = date;
        this.cashOnHand = 0;
        this.revenue = 0;
        this.outstanding = 0;
    }

    public void addReciept(Transaction reciept) {
        this.reciepts.add((Transaction) reciept.clone());
        this.revenue += reciept.getWorth();
        this.cashOnHand += reciept.getPayment();
        this.outstanding = this.cashOnHand - this.revenue;
    }

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}
