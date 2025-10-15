package org.example;

import com.google.gson.*;
import net.tcgdex.sdk.TCGdex;
import net.tcgdex.sdk.models.Card;
import net.tcgdex.sdk.models.CardResume;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        //GET https://api.tcgdex.net/v2/en/cards?name=zekrom
        System.out.println("Hello and welcome!");
        System.out.println("Enter the name of your card");
        Scanner sc = new Scanner(System.in);
        String cname = sc.nextLine(); //card name
        System.out.println("Enter the ID of your card");
        String cid = sc.nextLine(); //card id


        //not used yet, but an alternative to call cards
        TCGdex tcgdex = new TCGdex("en");

        try {
            //Api calls are made through http requests, this sets the client up to be able to make a request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.tcgdex.net/v2/en/cards?name=" + cname)) //right now it pulls all the cards with the name entered above
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body(); //puts the response into a json file
            System.out.println("Response status: " + response.statusCode()); //used if any errors are given

            try {
                Card[] cardlist = new Gson().fromJson(json, Card[].class); //gson is used to turn json files into classes

                if (cardlist == null) { //dont want to print if there is nothing found
                    System.out.println("Card list is null");
                    return;
                }

                System.out.println("Number of cards found: " + cardlist.length); //shows amount of cards with that name

                System.out.println("Want to see all cards (type 1) or the specific card (type 0)"); //used for testing, if you want to see all cards
                int ctype = sc.nextInt(); //card type if you want to specific card or all cards with that name

                String cardid = "";
                boolean found = false;
                for (Card card : cardlist) { //goes through all cards that were from that http request
                    if (ctype == 1) {
                        System.out.println("ID: " + (card.getId() != null ? card.getId() : "No id available"));
                        System.out.println("LocalID: " + (card.getLocalId() != null ? card.getLocalId() : "No localId available"));
                        System.out.println("Name: " + (card.getName() != null ? card.getName() : "No name available"));
                        System.out.println("Image: " + (card.getImage() != null ? card.getImage() : "No image available"));
                        System.out.println("------------------");
                    } else if (ctype == 0) {
                        if(card.getLocalId().equals(cid)){
                            cardid = card.getId();
                            found = true;
                            System.out.println("ID: " + (card.getId() != null ? card.getId() : "No id available"));
                            System.out.println("LocalID: " + (card.getLocalId() != null ? card.getLocalId() : "No localId available"));
                            System.out.println("Name: " + (card.getName() != null ? card.getName() : "No name available"));
                            System.out.println("Image: " + (card.getImage() != null ? card.getImage() : "No image available"));
                            System.out.println("------------------");
                        }
                    }
                }
                if(found) {
                    request = HttpRequest.newBuilder()
                            .uri(URI.create("https://api.tcgdex.net/v2/en/cards/" + cardid)) //right now it pulls all the cards with the name entered above
                            .GET()
                            .build();

                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json2 = response.body(); //puts the response into a json file
                    System.out.println("Response status: " + response.statusCode()); //used if any errors are given
                    //FullCard cardinfo = new Gson().fromJson(json2, FullCard.class);
                    Gson gson2 = new Gson();
                    FullCard card = gson2.fromJson(json2, FullCard.class);

                    if (card == null) { //dont want to print if there is nothing found
                        System.out.println("Card info is null");
                        return;
                    }

                    //FullCard card = new FullCard();
                        System.out.println("=== FULL CARD INFORMATION ===");
                        System.out.println("Name: " + card.getName());
                        System.out.println("ID: " + card.getId());
                        System.out.println("Category: " + card.getCategory());
                        System.out.println("Illustrator: " + card.getIllustrator());
                        System.out.println("Local ID: " + card.getLocalId());
                        System.out.println("Image URL: " + card.getImage());
                        System.out.println("Rarity: " + card.getRarity());
                        System.out.println("HP: " + card.getHp());
                        System.out.println("Types: " + card.getTypes());
                        System.out.println("Stage: " + card.getStage());
                        System.out.println("Evolve From: " + card.getEvolveFrom());
                        System.out.println("Description: " + card.getDescription());
                        System.out.println("Retreat: " + card.getRetreat());
                        System.out.println("Regulation Mark: " + card.getRegulationMark());
                        System.out.println("Updated: " + card.getUpdated());
                        System.out.println();

                        // ====== SET INFO ======
                        if (card.getSet() != null) {
                            System.out.println("--- Set Info ---");
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

                        // ====== VARIANTS ======
                        if (card.getVariants() != null) {
                            System.out.println("--- Variants ---");
                            FullCard.Variants v = card.getVariants();
                            System.out.println("First Edition: " + v.isFirstEdition());
                            System.out.println("Holo: " + v.isHolo());
                            System.out.println("Normal: " + v.isNormal());
                            System.out.println("Reverse: " + v.isReverse());
                            System.out.println("WPromo: " + v.isWPromo());
                            System.out.println();
                        }

                        // ====== ATTACKS ======
                        if (card.getAttacks() != null) {
                            System.out.println("--- Attacks ---");
                            for (FullCard.Attack attack : card.getAttacks()) {
                                System.out.println("Name: " + attack.getName());
                                System.out.println("Cost: " + attack.getCost());
                                System.out.println("Damage: " + attack.getDamage());
                                System.out.println("Effect: " + attack.getEffect());
                                System.out.println();
                            }
                        }

                        // ====== WEAKNESSES ======
                        if (card.getWeaknesses() != null) {
                            System.out.println("--- Weaknesses ---");
                            for (FullCard.Weakness w : card.getWeaknesses()) {
                                System.out.println("Type: " + w.getType() + ", Value: " + w.getValue());
                            }
                            System.out.println();
                        }

                        // ====== LEGALITY ======
                        if (card.getLegal() != null) {
                            System.out.println("--- Legal ---");
                            System.out.println("Standard: " + card.getLegal().isStandard());
                            System.out.println("Expanded: " + card.getLegal().isExpanded());
                            System.out.println();
                        }

                        // ====== PRICING ======
                        if (card.getPricing() != null) {
                            System.out.println("--- Pricing ---");
                            FullCard.Pricing p = card.getPricing();

                            if (p.getCardmarket() != null) {
                                System.out.println("Cardmarket Prices:");
                                FullCard.PriceData cm = p.getCardmarket();
                                System.out.println("Updated: " + cm.getUpdated());
                                System.out.println("Unit: " + cm.getUnit());
                                System.out.println("Average: " + cm.getAvg());
                                System.out.println("Low: " + cm.getLow());
                                System.out.println("Trend: " + cm.getTrend());
                                System.out.println();
                            }

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
                                System.out.println();
                            }
                        }

                    }




            }catch(JsonSyntaxException e) { //error checking for when i was setting up json file parser gson
                System.out.println("JSON parsing error: " + e.getMessage());
                e.printStackTrace();

             } catch(Exception e){ //more error checking
                  System.out.println("Error in processing cards: " + e.getMessage());
                 e.printStackTrace();
            }
            sc.close();
        } catch(Exception e){ //if http client didn't work
            System.out.println("Can't make request");
        }
    }

}



