package ui;

import java.util.Scanner;

import classes.Stock;
import classes.Item;
import classes.NamedMap;

public class Inventory {
    public static Scanner sc = new Scanner(System.in);

    public static void inventoryManagerUI(Stock stock) {
        char choice;

        do {
            System.out.println("[1] Add item"); // Will also add quantity if already present
            System.out.println("[2] Remove item"); 
            System.out.println("[3] Change price");
            System.out.println("[4] Stock an item"); // increments quantity
            System.out.println("[5] Show all inventory");

            System.out.println("[X] Exit");
            System.out.print(" >> ");

            choice = Character.toUpperCase(sc.nextLine().strip().charAt(0));
			System.out.println();
            
            switch (choice) {
                case '1': // Add item
                    addItemUI(stock);
                    break;

                case '2': // Remove item
                    removeItemUI(stock);
                    break;

                case '3': // Change price
                    changePriceUI(stock);
                    break;

                case '4': // Stock an item
                    restockItemUI(stock);
                    break;

                case '5': // Show all inventory
                    inventoryListUI(stock);
                    break;
            }
        } while (choice != 'X');
    }

    // TUI for adding an item
    public static void addItemUI(Stock stock) {
        NamedMap<String, Item> items = stock.getItems();
        String itemName;
        double itemPrice;
        int itemQuantity;
        Item newItem;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (items.containsKey(itemName)) { // if the item already exists
            Item item = items.get(itemName);
            System.out.println("Already existing product: " + itemName + "!");

            // change price
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

    // TUI for removing an item
    public static void removeItemUI(Stock stock) {
        NamedMap<String, Item> items = stock.getItems();
        String itemName;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (items.containsKey(itemName)) {
            stock.removeItem(itemName);
            System.out.println("Item removed: " + itemName);
        } else {
            System.out.println("Item not found: " + itemName);
        }
        System.out.println();
    }

    // TUI for changing the price of an item
    public static void changePriceUI(Stock stock) {
        NamedMap<String, Item> items = stock.getItems();
        String itemName;
        double itemPrice;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (items.containsKey(itemName)) {
            Item item = items.get(itemName);
            System.out.print("Enter new price (Currently " + item.getPrice() + ") >> ");
            itemPrice = sc.nextDouble();
            sc.nextLine();

            item.setPrice(itemPrice);
            System.out.println("Price changed: " + itemName + " to " + itemPrice);
        } else {
            System.out.println("Item not found: " + itemName);
        }
        System.out.println();
    }

    // TUI for restocking an item (changing quantity)
    public static void restockItemUI(Stock stock) {
        NamedMap<String, Item> items = stock.getItems();
        String itemName;
        int itemQuantity;

        System.out.print("Enter item name >> ");
        itemName = sc.nextLine();

        if (items.containsKey(itemName)) {
            Item item = items.get(itemName);
            System.out.print("Enter amount to stock (Currently: " + item.getQuantity() + ") >> ");
            itemQuantity = sc.nextInt();
            sc.nextLine();

            item.incQuantity(itemQuantity);
            System.out.println("Increased amount of " + itemName + " by " + itemQuantity);
        } else {
            System.out.println("Item not found: " + itemName);
        }
        System.out.println();
    }

    // displays all items
    public static void inventoryListUI(Stock stock) {
        System.out.println("Inventory: " + stock.getItems().size() + " SKUs");
        System.out.println(stock);
    }
}