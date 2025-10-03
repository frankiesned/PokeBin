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


                for (Card card : cardlist) { //goes through all cards that were from that http request
                    if (ctype == 1) {
                        System.out.println("ID: " + (card.getId() != null ? card.getId() : "No id available"));
                        System.out.println("LocalID: " + (card.getLocalId() != null ? card.getLocalId() : "No localId available"));
                        System.out.println("Name: " + (card.getName() != null ? card.getName() : "No name available"));
                        System.out.println("Image: " + (card.getImage() != null ? card.getImage() : "No image available"));
                        System.out.println("------------------");
                    } else if (ctype == 0) {
                        if(card.getLocalId().equals(cid)){
                            System.out.println("ID: " + (card.getId() != null ? card.getId() : "No id available"));
                            System.out.println("LocalID: " + (card.getLocalId() != null ? card.getLocalId() : "No localId available"));
                            System.out.println("Name: " + (card.getName() != null ? card.getName() : "No name available"));
                            System.out.println("Image: " + (card.getImage() != null ? card.getImage() : "No image available"));
                            System.out.println("------------------");
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



