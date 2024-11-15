package classes.group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import classes.indiv.Log;

public class History extends ArrayList<Log> {
    // file read and write operations
    public void fromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("data/log.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    double totalPayment = Double.parseDouble(parts[0].strip());
                    double totalWorth = Double.parseDouble(parts[1].strip());
                    
                    Log log = new Log(totalPayment, totalWorth);
                    this.add(log);
                }
            }  
            System.out.println("Logs loaded from file!");     
         }catch(IOException e){
             e.printStackTrace(); // Handle exceptions
         }
    }

    public void toFile(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/log.csv"))){
            for(Log log : this){
                bw.write(log.getTotalPayment() + ","+ log.getTotalWorth());
                bw.newLine();
            }
        
            System.out.println("Logs saved to file!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //These are the functions to print out the profits based on different time periods
    public String toString(){
        String res = "";
        int inc = 0;
        double totalRevenue = 0;
        for (int i = this.size() - 1; i >= 0; i--) {
            Log log = this.get(i);
            int logNumber = this.size() - inc;
            res += "Day " + logNumber + ":";
            res += "\tTotal Payment: " + log.getTotalPayment();
            res += "\tTotal Worth: " + log.getTotalWorth();
            res += "\tRevenue: " + log.getRevenue() + "\n";
            totalRevenue += log.getRevenue();
            inc++;
        }
        res += "\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue;
        return res;
    }

    public String weekSummary(){
        String res = "";
        ArrayList<Log> weekLogs = new ArrayList<>();
        double totalRevenue = 0;

        // adds information per week
        for (int i = 0; i < this.size(); i++) {
            if (i % 7 == 0) {
                weekLogs.add(new Log());
            }

            weekLogs.get(weekLogs.size() - 1).incTotalPayment(this.get(i).getTotalPayment());
            weekLogs.get(weekLogs.size() - 1).incTotalWorth(this.get(i).getTotalWorth());
        }

        // displays information per week in reverse order
        for (int i = weekLogs.size() - 1; i >= 0; i--) {
            Log log = weekLogs.get(i);
            res += "Week " + (i + 1) + ":";
            res += "\tTotal Payment: " + log.getTotalPayment();
            res += "\tTotal Worth: " + log.getTotalWorth();
            res += "\tRevenue: " + log.getRevenue() + "\n";
            totalRevenue += log.getRevenue();
        }
        res += "\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue;
        return res;
    }

    public String monthSummary(){
        String res = "";
        ArrayList<Log> monthLogs = new ArrayList<>();
        double totalRevenue = 0;

        // adds information per month
        for (int i = 0; i < this.size(); i++) {
            if (i % 30 == 0) {
                monthLogs.add(new Log());
            }

            monthLogs.get(monthLogs.size() - 1).incTotalPayment(this.get(i).getTotalPayment());
            monthLogs.get(monthLogs.size() - 1).incTotalWorth(this.get(i).getTotalWorth());
        }

        // displays information per month in reverse order
        for (int i = monthLogs.size() - 1; i >= 0; i--) {
            Log log = monthLogs.get(i);
            res += "Month " + (i + 1) + ":";
            res += "\tTotal Payment: " + log.getTotalPayment();
            res += "\tTotal Worth: " + log.getTotalWorth();
            res += "\tRevenue: " + log.getRevenue() + "\n";
            totalRevenue += log.getRevenue();
        }
        res += "\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue;
        return res;
    }
    
}
