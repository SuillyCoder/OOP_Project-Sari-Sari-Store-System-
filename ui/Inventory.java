package ui;

import java.util.HashMap;

import java.util.Scanner;

import classes.Stock;
import classes.Item;

public class Inventory {

    public static Scanner sc = new Scanner(System.in);

    public static void inventoryManagerUI(Stock stock) {
        char choice;


        do {
            System.out.println("[1] Add item"); // Will also add quantity if already present
            System.out.println("[2] Remove item"); // 
            System.out.println("[3] Change price"); //
            System.out.println("[4] Stock an item"); // only increments quantity
            System.out.println("[5] Show all inventory");

            System.out.println("[X] Exit");
            System.out.print(" >> ");

            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
            
            switch (choice) {
                case '1': // Add item
                    addItemUI(stock);
                    break;
                case '2':
                    // Remove item
                    // System.out.print("Enter item name: ");
                    // itemName = sc.nextLine();
                    // items.remove(itemName);
                    // System.out.println();
                    break;
                case '3':
                    // Change price
                    // System.out.print("Enter item name: ");
                    // itemName = sc.nextLine();
                    // System.out.print("Enter new price: ");
                    // itemPrice = sc.nextDouble();
                    // sc.nextLine();
                    // item = items.get(itemName);
                    // item.setPrice(itemPrice);
                    // System.out.println();
                    break;
                case '4':
                    // Stock an item
                    // System.out.print("Enter item name: ");
                    // itemName = sc.nextLine();
                    // System.out.print("Enter quantity: ");
                    // int quantity = sc.nextInt();
                    // sc.nextLine();
                    // item = items.get(itemName);
                    // item.setQuantity(item.getQuantity() + quantity);
                    // System.out.println();
                    break;
                case '5': // Show all inventory
                    inventoryListUI(stock);
                    break;
            }
        } while (choice != 'X');
    }

    public static void inventoryListUI(Stock stock) {
        System.out.println("Inventory: " + stock.getItems().size() + " SKUs");
        System.out.println(stock);
    }

    public static void addItemUI(Stock stock) {
        HashMap<String, Item> items = stock.getItems();
        String itemName;
        double itemPrice;
        int itemQuantity;
        Item newItem;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (items.containsKey(itemName.toUpperCase().strip())) { // if the item already exists
            Item item = items.get(itemName.toUpperCase().strip());
            System.out.println("Already existing product: " + itemName + "!");

            System.out.print("Restock by (Current: " + item.getQuantity() + ") >> ");
            itemQuantity = sc.nextInt();
            sc.nextLine();

            newItem = new Item(itemName, item.getPrice(), itemQuantity);

        } else { // determines that the item is new
            System.out.println("New product " + itemName + "!");

            System.out.print("Enter item price >> ");
            itemPrice = sc.nextDouble();
            System.out.print("Enter item quantity >> ");
            itemQuantity = sc.nextInt();
            sc.nextLine();

            newItem = new Item(itemName, itemPrice, itemQuantity);

        }

        stock.addItem(itemName, newItem);
        System.out.println();
    }
}