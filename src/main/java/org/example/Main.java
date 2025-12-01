package org.example;

import com.google.gson.*;
import net.tcgdex.sdk.TCGdex;
import org.example.AppStuff.MainFrame;
import org.opencv.core.Core;

import java.util.HashMap;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //GET https://api.tcgdex.net/v2/en/cards?name=zekrom
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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



