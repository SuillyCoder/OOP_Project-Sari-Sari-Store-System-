package gui;

import java.util.Scanner;

import classes.group.History;

public class Profit {
    public static void profitLog(History logs) {
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
                System.out.println(logs);
                break;
            case 2:
                System.out.println(logs.weekSummary());
                break;
            case 3: 
                System.out.println(logs.monthSummary());
                break;
        }
    }
}