package org.example;

import java.util.HashMap;

public class PrintAllCards {
    public static void printAllCards(HashMap<String,FullCard> cardMap) {
        for (String i : cardMap.keySet()) {
            System.out.println("key: " + i);
            FullCard card = cardMap.get(i);
            if (card != null) {
                PrintCard.printCard(card);
            } else System.out.println("Card Does Not Exist");
        }
    }
}
