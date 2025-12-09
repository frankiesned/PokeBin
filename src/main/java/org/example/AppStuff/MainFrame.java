package org.example.AppStuff;
import org.example.Database;
import org.example.FullCard;
import org.example.AppStuff.Scanner.ScanUI;
import org.example.NewCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.String;
import java.util.Collection;
import java.util.HashMap;
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

public class MainFrame extends JFrame{

    private JPanel mpnl;
    LoginUI loginPanel;
    MainMenuUI mainMenuPanel;
    CollectionUI collectionPanel;
    ManualInputUI manualInputPanel;
    ScanUI scanCardPanel;
    private CardLayout clayout;
    public Color maincolor = new Color(240, 240, 240);
    public Color coloraccent1 = new Color(238, 21, 21);
    public Color coloraccent2 = new Color(34, 34,36);
    private boolean darkmode = false;
    public Database db = new Database();
    public int userID = 0;

    //WITH DATABASE THESE SHOULD BE FILLED AFTER LOGIN
    private HashMap<String, Integer> collectionNums = new HashMap<String, Integer>(); //holds the number of a particular type of card
    private HashMap<String, FullCard> collectionCards = new HashMap<String, FullCard>(); //holds the cards

    public MainFrame() {


        //database part
        try
        {
            db.loadUsers("src/main/java/org/example/users");
            db.loadCards("src/main/java/org/example/Cards");
            db.loadCollections("src/main/java/org/example/card holders");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //initializes all UIs
        loginPanel = new LoginUI(this);
        mainMenuPanel = new MainMenuUI(this);
        collectionPanel = new CollectionUI(this);
        manualInputPanel = new ManualInputUI(this);
        scanCardPanel = new ScanUI(this);

        clayout = new CardLayout();
        mpnl = new JPanel(clayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        //adds all the panels of each UI to the frame, this loads everything at once, faster button movements
        mpnl.add(loginPanel,"login" );
        mpnl.add(mainMenuPanel, "Main");
        mpnl.add(collectionPanel, "collection");
        mpnl.add(manualInputPanel, "manualinput");
        mpnl.add(scanCardPanel, "scan");

        add(mpnl);
        clayout.show(mpnl, "login");
        setVisible(true);
//BROKEN PMO
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                for(String temp : collectionCards.keySet())
//                {
//                    db.addOrUpdateCard(userID, nametonum.get(collectionCards.get(temp).getName()), collectionNums.get(temp));
//                }
//                try {
//                    db.saveCollections("src/main/java/org/example/card holders");
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                super.windowClosing(e);
//            }
//        });
    }


    //takes the panel name as input, each panel needs to go through the frame object parameter to change, slightly faster
    public void changepanel(String name)
    {
        CardLayout cl = (CardLayout) mpnl.getLayout();
        cl.show(mpnl, String.valueOf(name));

    }



    //adds FullCard argument to CollectionCards arraylist, local storage
    public void addToCollect(FullCard tempcard)
    {
        if(!collectionNums.containsKey(tempcard.getHashcode()))
        {
            collectionNums.put(tempcard.getHashcode(), 1);
            collectionCards.put(tempcard.getHashcode(), tempcard);
        }
        else
        {
            collectionNums.replace(tempcard.getHashcode(), collectionNums.get(tempcard.getHashcode()) + 1);
        }
    }

    public void addToCollectFromDB(String name, String uniqID)
    {
        FullCard tempcard = NewCard.inputCard(name, uniqID);
        if(tempcard == null)
        {
            return;
        }
        else
        {
            collectionCards.put((name + uniqID), tempcard);
        }
    }

    public void addToCollectNumsFromDB(String name, String uniqID, int nm)
    {
        collectionNums.put(name + uniqID, nm);
    }

    //returns all the cards the user has, NEEDS TO CONNECT TO DATABASE
    public Collection<FullCard> GetFullcards()
    {
        return collectionCards.values();
    }

    //gets the amount of a type of card the user has via the tempcard argument, NEEDS TO CONNECT TO DATABASE
    public int GetCardAmount(FullCard tempcard)
    {
        if(collectionNums.get(tempcard.getHashcode()) == null)
        {
            return 0;
        }
        return collectionNums.get(tempcard.getHashcode());
    }

    public void changedarkmode()
    {
        if(!darkmode)
        {
            maincolor = new Color(24, 25, 22);
            coloraccent2 = new Color(230, 156, 23);
            coloraccent1 = new Color(220, 24, 58);
            darkmode = true;
        }
        else
        {
            maincolor = new Color(240, 240, 240);
            coloraccent1 = new Color(238, 21, 21);
            coloraccent2 = new Color(34, 34,36);
            darkmode = false;
        }
    }


    //deletes a card argument from the frame's Arraylist
    public void deleteCard(FullCard tempcard)
    {
        if(collectionNums.get(tempcard.getHashcode()) == 1)
        {
            collectionCards.remove(tempcard.getHashcode());
            collectionNums.remove(tempcard.getHashcode());
        }
        else
        {
            collectionNums.replace(tempcard.getHashcode(), collectionNums.get(tempcard.getHashcode()) - 1);
        }
    }

}
