// for removal
// move lowInventoryNotifier() to a more appropriate class

package gui;

import classes.group.Stock;
import classes.indiv.Item;

public class Inventory {
    private static final int LOW_INVENTORY_THRESHOLD = 5;

    // should be moved somewhere else
    public static void lowInventoryNotifier(Stock stock){
        for (String key : stock.keySet()) {
            Item item = stock.get(key);
            if (item.getQuantity() < LOW_INVENTORY_THRESHOLD) {
                System.out.println("Low inventory!\t" + item.getName() + " (" + item.getQuantity() + ")");
            }
        }
    }
}