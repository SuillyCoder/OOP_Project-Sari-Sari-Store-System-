package gui;

import classes.NamedMap;
import classes.group.Contacts;
import classes.group.Stock;
import classes.indiv.Customer;
import classes.indiv.Item;
import classes.indiv.Transaction;

import java.util.Optional;
import java.util.Scanner;

public class PointOfSale {
    private static Scanner sc = new Scanner(System.in);

    public static void transactionUI(Optional<Transaction> cart, Contacts contacts, Stock stock, int date) {
        char choice;

        do {
            System.out.println("[1] Add item to cart");
            System.out.println("[2] Show cart"); // temporary
            System.out.println("[F] Finalize transaction");
            System.out.println("[X] Cancel transaction");
            System.out.print(" >> ");

            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
            System.out.println();

            switch (choice) {
                case '1':
                    addToCartUI(cart.get(), stock);
                    break;

                case '2':
                    cartListUI(cart.get());
                    break;

                case 'F':
                    finalizeTransactionUI(cart, contacts, date);
                    break;

                case 'X':
                    System.out.println("Transaction cancelled!\n");
                    cart = Optional.empty();
                    break;
            }
        } while (choice != 'F' && choice != 'X');
    }

    // TUI for adding a singular item to the cart
    private static void addToCartUI(Transaction transaction, Stock stock) {
        NamedMap<Item> cart = transaction.getItems();
        String itemName;
        Item stockedItem;
        Item newItem;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (!stock.containsKey(itemName)) {
            System.out.println(itemName + " not found in stock!\n");
        
        } else {
            stockedItem = stock.get(itemName);                              // original item
            newItem = stockedItem.clone();                                  // item to be added to cart
            boolean isAlreadyInCart = cart.containsKey(itemName);           // if item was in cart already
            
            // if no stock of the item
            if (stockedItem.getQuantity() == 0) {
                System.out.println("Ran out of stock!\n");
                return;
            }

            System.out.print("How much" + (isAlreadyInCart ? " more" : "") + "? (Of " + stockedItem.getQuantity() + ") >> ");
            int quantity = sc.nextInt();
            sc.nextLine();
            System.out.println();

            // if requested amount is too much
            if (quantity > stockedItem.getQuantity()) {
                System.out.println("Amount requested exceeds present in stock!\n");
                return;
            // if requested amount is negative
            } else if (quantity < 0) {
                System.out.println("Invalid quantity!\n");
                return;
            } // otherwise

                stockedItem.decQuantity(quantity);                          // decrement item quantity in stock
                transaction.incWorth(quantity * stockedItem.getPrice());    // increment transaction worth

            if (isAlreadyInCart) {
                cart.get(itemName).incQuantity(quantity);                   // increment quantity of item in cart by desired amount
                System.out.println("Item quantity incremented by " + quantity + ":\n" + newItem);

            } else {
                newItem.setQuantity(quantity);                              // set quantity of item in cart to the desired amount
                transaction.addItem(newItem);                               // add item to cart
                System.out.println("Item added to cart:\n" + newItem);
            }
        }
    }

    // TUI for displaying all items in the cart
    private static void cartListUI(Transaction transaction) {
        System.out.println(transaction.getItems().size() + " Item(s) in cart:");
        for (Item i : transaction.getItems().values()) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println("Total worth: " + transaction.getWorth());
        System.out.println();
    }

    // TUI for customer payment
    private static void finalizeTransactionUI(Optional<Transaction> cart, Contacts contacts, int date) {
        String customerName;
        Customer customer;
        double payment, outstanding, revenue;

        System.out.print("Enter customer name >> ");
        customerName = sc.nextLine();

        // customer from customerName, either existing or new
        if (contacts.containsKey(customerName)) {
            customer = contacts.get(customerName);
            System.out.println(customerName + " has P" + customer.getCredit() + " of outstanding balance!\n"); 
        } else {
            customer = new Customer(customerName);
            contacts.put(customerName, customer);
            System.out.println("Created new customer record! " + customerName + "!\n");
        }

        // payment calculations
        System.out.println("Cost of cart: " + cart.get().getWorth());
        outstanding = customer.getCredit() - cart.get().getWorth();
        if (customer.getCredit() != 0) {
            System.out.println("Pay P" + (-1 * outstanding) + " to clear outstanding balance.");
        }
        System.out.println("Maxmimum allowable debt for the transaction: P" + (-1 * ((-1 * outstanding) + Contacts.getMaxDebt())));
        System.out.println();

        System.out.print("Enter payment amount >> ");
        payment = sc.nextDouble();
        sc.nextLine();
        System.out.println();

        revenue = outstanding + payment;

        // if payment is not enough such that the customer exceeds the maximum allowable debt
        if (Contacts.getMaxDebt() > revenue) {
            System.out.println("Current transaction will exceed maximum allowable debt!");
            System.out.println("Transaction cancelled!\n");
            cart = Optional.empty();
            return;
        }
        // otherwise the transaction is approved

        // if the customer has paid off the debt
        if (revenue == 0) {
            System.out.println("No more outstanding balance!");
            System.out.println("Removing record of " + customerName);
            contacts.remove(customerName);

        // if the customer has some remaining balance (either debt or advance)
        } else {
            System.out.println("Remaining outstanding balance: P" + revenue);
            customer.setCredit(revenue);
            customer.setDate(date);

        }

        cart.get().setCustomer(customerName);
        cart.get().setPayment(payment);
        
        System.out.println("\nTransaction finalized!\n");
    }
}
