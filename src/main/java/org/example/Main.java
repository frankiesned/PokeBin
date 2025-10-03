package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.tcgdex.sdk.TCGdex;
import net.tcgdex.sdk.models.Card;
import net.tcgdex.sdk.models.CardResume;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //GET https://api.tcgdex.net/v2/en/cards?name=zekrom
        System.out.printf("Hello and welcome!");

        TCGdex tcgdex = new TCGdex("en");

        class Card {
            String id;
            String localId;
            String name;
            String image;
        }

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.tcgdex.net/v2/en/cards?name=zekrom"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();
            Card[] cards = gson.fromJson(json, Card[].class);

            try {

                    for (Card card : cards) {
                        System.out.println("ID: " + (card.id != null ? card.id : "No id available"));
                        System.out.println("LocalID: " + (card.localId != null ? card.localId : "No localId available"));
                        System.out.println("Name: " + (card.name != null ? card.name : "No name available"));
                        System.out.println("Image: " + (card.image != null ? card.image : "No image available"));
                        System.out.println("------------------");
                    }

                System.out.println("TEST");
            }catch(Exception e){
                System.out.println("error");
            }

            /*Gson gson = new Gson();
            Card[] IDList = gson.fromJson(json, Card[].class);
            for (Card card : IDList) {
                if(card != null)
                    System.out.println("ID: " + card.id);
                else
                    System.out.println("ID is null");
            }*/

        } catch(Exception e){
            System.out.println("Can't make request");
        }
    }

}




