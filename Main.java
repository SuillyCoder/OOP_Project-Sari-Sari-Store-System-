import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static Stock stock = new Stock();
    public static ArrayList<Log> logs = new ArrayList<>();

    public static void main(String[] args) {
        char choice;
        do {
            System.out.println("[1] New customer"); // (Customer.java)
            System.out.println("[2] New purchase"); // choice if new or existing customer (Purchase.java)
            System.out.println("[3] Store stocks"); // add, remove, change items (Inventory.java)
            System.out.println("[4] Store revenue"); // shows logs (Revenue.java)

            System.out.println("[X] Exit");
            System.out.print(" >> ");

            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
    
            switch (choice) {
                case '1':
                    break;
                case '2':
                    // implement code here
                    break;
                case '3':
                    // implement code here
                    break;
                case '4':
                    // implement code here
                    break;
                case 5:
                    // implement code here
                    break;
                
                case 'x':
                    choice = 'X';
                case 'X':
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 'X');


    }
}
