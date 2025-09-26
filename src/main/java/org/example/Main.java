package org.example;

import net.tcgdex.sdk.TCGdex;
import net.tcgdex.sdk.models.Card;
import net.tcgdex.sdk.models.CardResume;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //GET https://api.tcgdex.net/v2/en/cards?name=zekrom
        System.out.printf("Hello and welcome!");

        TCGdex tcgdex = new TCGdex("en");




        System.out.println(tcgdex.fetchSets());

    }
}