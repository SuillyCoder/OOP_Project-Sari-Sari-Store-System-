
import java.util.ArrayList;

public class Log {
    private ArrayList<Reciept> reciepts;
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

    public void addReciept(Reciept reciept) {
        this.reciepts.add((Reciept) reciept.clone());
        this.revenue += reciept.getRevenue();
        this.cashOnHand += reciept.getCashOnHand();
        this.outstanding = this.cashOnHand - this.revenue;
    }

    public void fromFile(){
        // implement code here
    }

    public void toFile(){
        // implement code here
    }
}
