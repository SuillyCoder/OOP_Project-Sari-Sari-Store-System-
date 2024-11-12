package tui;

import classes.Log;

import java.util.ArrayList;
import java.util.Scanner;

public class Profit {
    public static void addLog(Log log, ArrayList<Log> logHistory) {
        logHistory.add(log.clone()); // Add the cloned log to the logHistory
        if (logHistory.size() == 0) {
            System.out.println("Log Addition Unsuccessful!\n");
        }
        else{
            System.out.println("Log Added Successfully!\n");
        }
    }

    public static void profitLog(ArrayList<Log> logs) {
        if (logs.size() == 0) {
            System.out.println("No logs found!\n");
            return;
        }

        Scanner time = new Scanner(System.in);

        System.out.println("Select Time Period Viewing Mode\n");
        System.out.println("[1] Day\n[2] Week\n[3] Month\n");
        System.out.print(" >> ");
        int choice = time.nextInt();
        System.out.println();

        switch (choice) {
            case 1: 
                daySummary(logs);
                break;
            case 2:
                weekSummary(logs);
                break;
            case 3: 
                monthSummary(logs);
                break;
        }
    }

    //These are the functions to print out the profits based on different time periods
    public static void daySummary(ArrayList<Log> logs){
        int inc = 0;
        for (int i = logs.size() - 1; i >= 0; i--) {
            Log log = logs.get(i);
            int logNumber = logs.size() - inc;
            System.out.print("Day: " + logNumber);
            System.out.print("\tTotal Payment: " + log.getTotalPayment());
            System.out.print("\tTotal Worth: " + log.getTotalWorth());
            System.out.println("\n");
            inc++;
        }
    }

    public static void weekSummary(ArrayList<Log> logs){
        int inc = 0;
        for (int i = logs.size() - 1; i >= 0; i-=7) {
            int weekPay = 0, weekWorth = 0;
            Log log = logs.get(i);
            int logNumber = logs.size() - (6+inc);
            System.out.print("Week: " + logNumber);
            for (int j = 0; j < 7; j++) {
                weekPay += log.getTotalPayment();
                weekWorth += log.getTotalWorth();
            }
            System.out.print("\tTotal Payment: " + weekPay);
            System.out.print("\tTotal Worth: " + weekWorth);
            System.out.println("\n");
            inc++;
            }
    }

    public static void monthSummary(ArrayList<Log> logs){
        int inc = 0;
        for (int i = logs.size() - 1; i >= 0; i-=30) {
            int monthPay = 0, monthWorth = 0;
            Log log = logs.get(i);
            int logNumber = logs.size() - (29+inc);
            System.out.print("Month: " + logNumber);
            for (int j = 0; j < 30; j++) {
                monthPay += log.getTotalPayment();
                monthWorth += log.getTotalWorth();
            }
            System.out.print("\tTotal Payment: " + monthPay);
            System.out.print("\tTotal Worth: " + monthWorth);
            System.out.println("\n");
            inc++;
            }
    }

}