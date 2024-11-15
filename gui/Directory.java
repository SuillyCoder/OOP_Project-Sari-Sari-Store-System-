package gui;

import java.util.Scanner;

import classes.indiv.Customer;

public class Directory {
    private static Scanner sc = new Scanner(System.in);

    public static void changeMaxDebtUI(){
        double newMaxDebt;
        
        System.out.println("Set the maximum amount a person can borrow to (currently: " + -1 * Customer.getMaxDebt() + ") >> ");

        newMaxDebt = sc.nextDouble();
        sc.nextLine();

        Customer.setMaxDebt(newMaxDebt);
        System.out.println("Maximum debt changed to " + -1 * Customer.getMaxDebt());
        System.out.println();
    }
}
