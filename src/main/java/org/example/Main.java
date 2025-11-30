package org.example;

import com.google.gson.*;
import net.tcgdex.sdk.TCGdex;
import org.example.AppStuff.MainFrame;

import java.util.HashMap;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //GET https://api.tcgdex.net/v2/en/cards?name=zekrom
    public static void main(String[] args) {

        new MainFrame();
        //ID will be key and it will find the card class
//        HashMap<String,FullCard> cardMap = new HashMap<>();
//        Scanner sc = new Scanner(System.in);
//
//        while(true) {
//            if (NewCard.inputCard(cardMap, sc)) {
//                System.out.println("New card has been created");
//            } else {
//                System.out.println("New card has not been created");
//            }
//
//            //print all cards in hashmap
//            System.out.println("Print all cards? (1: yes)");
//            String option = sc.nextLine();
//            if (option.equals("1")) {
//                PrintAllCards.printAllCards(cardMap);
//            }
//
//            //add card to hashmap
//            System.out.println("Add another card? (0: exit)");
//            String option2 = sc.nextLine();
//            if (option2.equals("0")) {
//                sc.close();
//                return;
//            }
//        }
    }

}



import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database();

            db.loadUsers("src/users");
            db.loadCards("src/Cards");
            db.loadCollections("src/card holders");

            // Example: get user 1's collection
            List<Database.CollectionItem> items = db.getUserCollection(1);
            for (Database.CollectionItem item : items) {
                System.out.println(item.card.name + " x" + item.numOf);
            }

            // Example: add a card to a user
            db.addOrUpdateCard(1, 2, 5);

             items = db.getUserCollection(1);
            for (Database.CollectionItem item : items) {
                System.out.println(item.card.name + " x" + item.numOf);
            }
            // Save updated collections
            db.saveCollections("src/card holders");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
