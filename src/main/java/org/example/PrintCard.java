package org.example;

import java.util.Scanner;

public class PrintCard {
    public static void printCard(FullCard card) {
        System.out.println("FULL CARD INFO");
        System.out.println("Name: " + card.getName());
        System.out.println("ID: " + card.getId());
        System.out.println("Category: " + card.getCategory());
        System.out.println("Illustrator: " + card.getIllustrator());
        System.out.println("Local ID: " + card.getLocalId());
        System.out.println("Image URL: " + card.getImage());
        System.out.println("Rarity: " + card.getRarity());
        System.out.println("Types: " + card.getTypes());
        System.out.println("Stage: " + card.getStage());
        System.out.println("Updated: " + card.getUpdated());
        System.out.println();

        if (card.getSet() != null) {
            System.out.println("SET INFO");
            FullCard.Set set = card.getSet();
            System.out.println("Set Name: " + set.getName());
            System.out.println("Set ID: " + set.getId());
            System.out.println("Set Symbol: " + set.getSymbol());
            System.out.println("Set Logo: " + set.getLogo());
            if (set.getCardCount() != null) {
                System.out.println("Cards in Set: " + set.getCardCount().getOfficial() +
                        " official, " + set.getCardCount().getTotal() + " total");
            }
            System.out.println();
        }

        if (card.getVariants() != null) {
            System.out.println("VARIANTS");
            FullCard.Variants v = card.getVariants();
            System.out.println("First Edition: " + v.isFirstEdition());
            System.out.println("Holo: " + v.isHolo());
            System.out.println("Normal: " + v.isNormal());
            System.out.println("Reverse: " + v.isReverse());
            System.out.println("WPromo: " + v.isWPromo());
            System.out.println();
        }

        if (card.getPricing() != null) {
            System.out.println("PRICING");
            FullCard.Pricing p = card.getPricing();

            if (p.getTcgplayer() != null) {
                System.out.println("TCGPlayer Prices:");
                FullCard.TCGPlayerPrice t = p.getTcgplayer();
                System.out.println("Updated: " + t.getUpdated());
                System.out.println("Unit: " + t.getUnit());
                if (t.getNormal() != null) {
                    FullCard.PriceType n = t.getNormal();
                    System.out.println("Normal - Low: " + n.getLowPrice() +
                            ", Mid: " + n.getMidPrice() +
                            ", High: " + n.getHighPrice() +
                            ", Market: " + n.getMarketPrice());
                }
                if (t.getReverse() != null) {
                    FullCard.PriceType r = t.getReverse();
                    System.out.println("Reverse - Low: " + r.getLowPrice() +
                            ", Mid: " + r.getMidPrice() +
                            ", High: " + r.getHighPrice() +
                            ", Market: " + r.getMarketPrice());
                }
                if (t.getHolofoil() != null) {
                    FullCard.PriceType h = t.getHolofoil();
                    System.out.println("Holo - Low: " + h.getLowPrice() +
                            ", Mid: " + h.getMidPrice() +
                            ", High: " + h.getHighPrice() +
                            ", Market: " + h.getMarketPrice());
                }
                System.out.println();
            }
        }
    }
}
