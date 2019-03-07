package pl.lach;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Item, Integer> orderedItems;

    public Basket(Map<Item, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Basket() {
        this.orderedItems = new HashMap<>();
    }


    public Map<Item, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void addItemToBracket(Item item) {
        orderedItems.put(item, 1);
    }

    public void addItemToBracket(Item item, Integer amount) {
        orderedItems.put(item, amount);
    }

    public void removeItemFromBracket(Item item) {
        orderedItems.remove(item);
    }

    public void removeItemFromBracket(Item item, Integer amount) {
        Integer newAmountOfProduct = orderedItems.get(item) - amount;
        orderedItems.put(item, newAmountOfProduct);
    }

    public BigDecimal showTotalCostOfBracket() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Item item : orderedItems.keySet()) {
            totalCost = totalCost.add(item.getPrice().multiply(new BigDecimal(orderedItems.get(item))));
        }
        return totalCost;
    }

    public void showContentOfBracket() {
        for (Item item : orderedItems.keySet()) {
            System.out.println("Product: " + item.getName() + " Price: " + item.getPrice() + " Amount: " + orderedItems.get(item));
        }
    }
}
