package pl.lach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private Basket basket;
    private Basket basketWithItem;
    private Item item1;
    private Item item2;
    private static final Integer AMOUNT_OF_ITEMS = 4;

    @BeforeEach
    void setUp() {
        basket = new Basket();
        item1 = new Item(BigDecimal.ONE, "Item1");
        item2 = new Item(BigDecimal.ONE, "Item2");

        Map<Item, Integer> items = new HashMap<>();
        items.put(item1, AMOUNT_OF_ITEMS);
        items.put(item2, AMOUNT_OF_ITEMS);
        basketWithItem = new Basket(items);
    }

    @Test
    void addItemToBracketTest() {
        for (int i = 1; i < 10; i++) {
            basket.addItemToBracket(item1);
            assertTrue(basket.getOrderedItems().containsKey(item1) && basket.getOrderedItems().get(item1).equals(i) && basket.getOrderedItems().size() == 1);
        }
    }

    @Test
    public void addItemToBracketShouldntAcceptNegativeAmountOfItem() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> basket.addItemToBracket(item1, -1));
        assertEquals("Amount of item must be a positive number", exception.getMessage());
    }

    @Test
    public void addItemToBracketTestMoreAmountOfItem() {
        for (int i = 1; i < 10; i++) {
            basket.addItemToBracket(item1, AMOUNT_OF_ITEMS);
            assertTrue(basket.getOrderedItems().containsKey(item1) && basket.getOrderedItems().get(item1).equals(i * AMOUNT_OF_ITEMS) && basket.getOrderedItems().size() == 1);
        }

    }

    @Test
    void removeItemFromBracketTest() {
        basketWithItem.removeItemFromBracket(item1);
        assertAll(
                () -> assertTrue(basketWithItem.getOrderedItems().containsKey(item2)),
                () -> assertFalse(basketWithItem.getOrderedItems().containsKey(item1))
        );
    }

    @Test
    void removeItemFromBracketTestSomeAmountOfItem() {
        int amountOfItemToRemove = 2;
        basketWithItem.removeItemFromBracket(item1, amountOfItemToRemove);
        assertTrue(basketWithItem.getOrderedItems().containsKey(item1)
                && basketWithItem.getOrderedItems().containsKey(item2)
                && basketWithItem.getOrderedItems().get(item1).equals(AMOUNT_OF_ITEMS - amountOfItemToRemove));
    }

    @Test
    public void removeItemFromBracketShouldntAcceptNegativeAmountOfItem() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> basketWithItem.removeItemFromBracket(item1, -1));
        assertEquals("Amount of item must be a positive number", exception.getMessage());
    }

    @Test
    public void removeItemFromBracketAmountOfItemsShouldntExcessActualAmount() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> basketWithItem.removeItemFromBracket(item1, 5));
        assertEquals("You don't have enough items", exception.getMessage());
    }

    @Test
    void showTotalCostOfBracket() {
        BigDecimal itemPrice = BigDecimal.ONE;
        int totalItemNumber = AMOUNT_OF_ITEMS * basketWithItem.getOrderedItems().size();
        assertEquals(itemPrice.multiply(BigDecimal.valueOf(totalItemNumber)), basketWithItem.showTotalCostOfBracket());
    }

    @Test
    void showContentOfBracket() {
        String expectedResult = "Product: Item1 Price: 1 Amount: 4\n" +
                                "Product: Item2 Price: 1 Amount: 4\n";
        assertEquals(expectedResult, basketWithItem.showContentOfBracket());
    }
}