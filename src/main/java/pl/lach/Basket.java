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
        if (orderedItems.containsKey(item)) {
            orderedItems.put(item, orderedItems.get(item) + 1);
        } else {
            orderedItems.put(item, 1);
        }
    }

    public void addItemToBracket(Item item, Integer amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount of item must be a positive number");
        if (orderedItems.containsKey(item)) {
            amount = orderedItems.get(item) + amount;
        }
        orderedItems.put(item, amount);
    }


    public void removeItemFromBracket(Item item) {
        orderedItems.remove(item);
    }

    public void removeItemFromBracket(Item item, Integer amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount of item must be a positive number");
        Integer newAmountOfProduct = orderedItems.get(item) - amount;
        if (newAmountOfProduct < 0) throw new IllegalStateException("You don't have enough items");
        orderedItems.put(item, newAmountOfProduct);
    }

    public BigDecimal showTotalCostOfBracket() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Item item : orderedItems.keySet()) {
            totalCost = totalCost.add(item.getPrice().multiply(new BigDecimal(orderedItems.get(item))));
        }
        return totalCost;
    }

    public String showContentOfBracket() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : orderedItems.keySet()) {
            stringBuilder.append("Product: " + item.getName() + " Price: " + item.getPrice() + " Amount: " + orderedItems.get(item) + "\n");
        }
        return stringBuilder.toString();
    }
}
