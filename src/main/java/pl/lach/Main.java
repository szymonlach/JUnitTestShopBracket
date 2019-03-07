package pl.lach;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Item milk = new Item(BigDecimal.valueOf(1.99), "Milk");
        Item appleJuice = new Item(BigDecimal.valueOf(2.99), "Apple Juice");
        Item orange = new Item(BigDecimal.valueOf(0.79), "Orange");
        Item bread = new Item(BigDecimal.valueOf(2.59), "Bread");


        Basket myBasket = new Basket();
        myBasket.addItemToBracket(milk, 2);
        myBasket.addItemToBracket(appleJuice, 3);
        myBasket.addItemToBracket(bread, 1);
        myBasket.addItemToBracket(orange, 6);

        System.out.println(myBasket.showTotalCostOfBracket());
        myBasket.showContentOfBracket();

        myBasket.removeItemFromBracket(milk);
        myBasket.removeItemFromBracket(orange, 4);
        System.out.println(myBasket.showTotalCostOfBracket());
        System.out.println(myBasket.showContentOfBracket());


    }
}
