package org.example.AppStuff;
import org.example.FullCard;
import org.example.AppStuff.Scanner.ScanUI;

import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.util.Collection;
import java.util.HashMap;

public class MainFrame extends JFrame{

    private JPanel mpnl;
    LoginUI loginPanel;
    MainMenuUI mainMenuPanel;
    CollectionUI collectionPanel;
    ManualInputUI manualInputPanel;
    ScanUI scanCardPanel;
    private CardLayout clayout;

    //WITH DATABASE THESE SHOULD BE FILLED AFTER LOGIN
    private HashMap<String, Integer> collectionNums = new HashMap<String, Integer>(); //holds the number of a particular type of card
    private HashMap<String, FullCard> collectionCards = new HashMap<String, FullCard>(); //holds the cards

    public MainFrame() {
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
