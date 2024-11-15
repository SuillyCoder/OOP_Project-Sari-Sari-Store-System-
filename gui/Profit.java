package gui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.indiv.Log;

public class Profit {
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
        double totalRevenue = 0;
        for (int i = logs.size() - 1; i >= 0; i--) {
            Log log = logs.get(i);
            int logNumber = logs.size() - inc;
            System.out.print("Day " + logNumber + ":");
            System.out.print("\tTotal Payment: " + log.getTotalPayment());
            System.out.print("\tTotal Worth: " + log.getTotalWorth());
            System.out.println("\tRevenue: " + log.getRevenue());
            totalRevenue += log.getRevenue();
            inc++;
        }
        System.out.println("\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue);
    }

    public static void weekSummary(ArrayList<Log> logs){
        ArrayList<Log> weekLogs = new ArrayList<>();
        double totalRevenue = 0;

        // adds information per week
        for (int i = 0; i < logs.size(); i++) {
            if (i % 7 == 0) {
                weekLogs.add(new Log());
            }

            weekLogs.get(weekLogs.size() - 1).incTotalPayment(logs.get(i).getTotalPayment());
            weekLogs.get(weekLogs.size() - 1).incTotalWorth(logs.get(i).getTotalWorth());
        }

        // displays information per week in reverse order
        for (int i = weekLogs.size() - 1; i >= 0; i--) {
            Log log = weekLogs.get(i);
            System.out.print("Week " + (i + 1) + ":");
            System.out.print("\tTotal Payment: " + log.getTotalPayment());
            System.out.print("\tTotal Worth: " + log.getTotalWorth());
            System.out.println("\tRevenue: " + log.getRevenue());
            totalRevenue += log.getRevenue();
        }
        System.out.println("\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue);
    }

    public static void monthSummary(ArrayList<Log> logs){
        ArrayList<Log> monthLogs = new ArrayList<>();
        double totalRevenue = 0;

        // adds information per month
        for (int i = 0; i < logs.size(); i++) {
            if (i % 30 == 0) {
                monthLogs.add(new Log());
            }

            monthLogs.get(monthLogs.size() - 1).incTotalPayment(logs.get(i).getTotalPayment());
            monthLogs.get(monthLogs.size() - 1).incTotalWorth(logs.get(i).getTotalWorth());
        }

        // displays information per month in reverse order
        for (int i = monthLogs.size() - 1; i >= 0; i--) {
            Log log = monthLogs.get(i);
            System.out.print("Month " + (i + 1) + ":");
            System.out.print("\tTotal Payment: " + log.getTotalPayment());
            System.out.print("\tTotal Worth: " + log.getTotalWorth());
            System.out.println("\tRevenue: " + log.getRevenue());
            totalRevenue += log.getRevenue();
        }
        System.out.println("\t\t\t\t\t\t\t\tTotal Revenue: " + totalRevenue);
    }

}