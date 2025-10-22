package org.example;

import com.google.gson.Gson;
import net.tcgdex.sdk.models.Card;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Scanner;

public class NewCard {
    public static boolean inputCard(HashMap<String, FullCard> cardMap, Scanner sc) {
        System.out.println("Enter the name of your card");
        String cname = sc.nextLine(); //card name
        String link = "https://api.tcgdex.net/v2/en/cards?name=" + cname;
        System.out.println("Enter the ID of your card");
        String cid = sc.nextLine(); //card id
        String json = getJson(link);


        Card[] card;
        String fullID = "";
        boolean found = false;
        if(json != null){
            card = new Gson().fromJson(json, Card[].class);
            for(Card card1 : card){
                if(card1.getLocalId().equals(cid)){
                    fullID = card1.getId();
                    found = true;
                }
            }
        } else{
            return false;
        }


        FullCard fullcard = null;
        if (found){
            link = "https://api.tcgdex.net/v2/en/cards/" + fullID;
            json = getJson(link);
            Gson gson2 = new Gson();
            fullcard = gson2.fromJson(json, FullCard.class);
            if (fullcard == null) { //dont want to print if there is nothing found
                System.out.println("Card info is null");
                return false;
            }

        }

        System.out.println("Would you like to add this card to your collection? (0: no / 1: yes)");
        String addcard = sc.nextLine();
        if (addcard.equals("1")){
            cardMap.put(fullID, fullcard);
            return true;
        }
        return false;
    }


    public static String getJson(String link) {
        try {
            //Api calls are made through http requests, this sets the client up to be able to make a request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(link)) //right now it pulls all the cards with the name entered above
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body(); //puts the response into a json file
            if(response.statusCode() == 200){
                return json;
            } else {
                return null;
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }
}