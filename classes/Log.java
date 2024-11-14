// Class which contains a financial report / summary for a single day
// Used ArrayList for keeping track of multiple reports

package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    private double totalPayment;
    private double totalWorth;

    public Log(){
        this.setTotalPayment(0);
        this.setTotalWorth(0);
    }

    public Log(double totalPayment, double totalWorth){
        this.setTotalPayment(totalPayment);
        this.setTotalWorth(totalWorth);
    }

    public double getTotalPayment() { return this.totalPayment; }
    public void setTotalPayment(double totalPayment){ this.totalPayment = totalPayment;}
    public void incTotalPayment(double totalPayment){ this.totalPayment = this.totalPayment + totalPayment;}
    
    public double getTotalWorth() { return this.totalWorth; }
    public void setTotalWorth(double totalWorth){ this.totalWorth = Math.max(0, totalWorth);}
    public void incTotalWorth(double totalWorth){ this.totalWorth = Math.max(0, this.totalWorth + totalWorth);}

    public double getRevenue(){ return this.totalPayment - this.totalWorth; }

    public void addTransaction(Transaction transaction) {
        this.totalPayment += transaction.getPayment();
        this.totalWorth += transaction.getWorth();
    }

    // file read and write operations
    public static void fromFile(ArrayList<Log> logHistory) {
        try(BufferedReader br = new BufferedReader(new FileReader("data/log.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    double totalPayment = Double.parseDouble(parts[0].strip());
                    double totalWorth = Double.parseDouble(parts[1].strip());
                    
                    Log log = new Log(totalPayment, totalWorth);
                    logHistory.add(log);
                }
            }  
            System.out.println("Logs loaded from file!");     
         }catch(IOException e){
             e.printStackTrace(); // Handle exceptions
         }
    }

    public static void toFile(ArrayList<Log> logHistory){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/log.csv"))){
            for(Log log : logHistory){
                bw.write(log.getTotalPayment() + ","+ log.getTotalWorth());
                bw.newLine();
            }
        
            System.out.println("Logs saved to file!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
