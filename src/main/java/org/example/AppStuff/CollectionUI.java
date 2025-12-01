package org.example.AppStuff;
import org.example.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static java.lang.String.valueOf;
//ts crazy frfr, don't touch, will break
public class CollectionUI extends JPanel {

    private SpecialButton back;
    private JLabel totValue;
    private JLabel totCost;
    private JLabel netWorth;
    private JPanel forCards;
    private JPanel Header;
    private MainFrame mfrm;
    private ArrayList<JButton> cardholder = new ArrayList<>();
    private ArrayList<FullCard> cardnumtracker = new ArrayList<>();
    private JDialog popup;
    JScrollPane scrllr;


    CollectionUI(MainFrame mframe) {
        setLayout(new BorderLayout());
        mfrm = mframe;


        //all top panel: back button, analytics
        Header = new JPanel(new GridLayout(1, 4));

        back = new SpecialButton("back", new Color(238, 21, 21), new Color(34, 34, 36));

        totValue = new JLabel("Total Value:", SwingConstants.CENTER);
        totValue.setBackground(new Color(34, 34, 36));
        totValue.setForeground(new Color(240, 240, 240));
        totValue.setOpaque(true);

        totCost = new JLabel("Total Cost:", SwingConstants.CENTER);
        totCost.setBackground(new Color(34, 34, 36));
        totCost.setOpaque(true);
        totCost.setForeground(new Color(240, 240, 240));
        totCost.setVisible(false);

        netWorth = new JLabel("Net Value:", SwingConstants.CENTER);
        netWorth.setBackground(new Color(34, 34, 36));
        netWorth.setOpaque(true);
        netWorth.setForeground(new Color(240, 240, 240));
        netWorth.setVisible(false);


        //panel that holds all the cards
        forCards = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        scrllr = new JScrollPane(forCards);
        scrllr.setBorder(null);
        scrllr.getVerticalScrollBar().setUnitIncrement(16);
        add(scrllr, BorderLayout.CENTER);
        refreshCards();
        System.out.println("stinky2");

        scrllr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //backbutton goes back to main screen
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });

    }

    //refresh cards revalidates and redraws all the card buttons in cardholder, this is done only when changes are made
    public void refreshCards()
    {
        cardnumtracker.clear(); //empties the cardnumtracker, DOES NOT EMPTY WHEN DATABASE
        cardnumtracker.addAll(mfrm.GetFullcards()); //adds all the database cards to cardnumtracker

        double values = 0;


        forCards.removeAll(); //deletes all the buttons and cards
        cardholder.clear();

        Header.add(back);
        Header.add(totCost);
        Header.add(netWorth);

        add(Header, BorderLayout.NORTH);

        //for every card in the database
        for (FullCard fullcard : cardnumtracker) {

            //adds a new JButton with an image of a pokemon at the front for every card in database
            JButton cardButton;
            try
            {
                ImageIcon img = new ImageIcon(new URL(fullcard.getImage() + "/high.png"));
                Image scaled = img.getImage().getScaledInstance(200, 260, Image.SCALE_SMOOTH);
                cardButton = new JButton(new ImageIcon(scaled));
                cardButton.setContentAreaFilled(false);
                cardButton.setBorderPainted(false);
                cardButton.setOpaque(false);

                forCards.add(cardButton);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            popups(cardButton, fullcard);
            FullCard.TCGPlayerPrice tcgplay = fullcard.getPricing().getTcgplayer();

            //adds the value or price of the card to the totValues
            if (tcgplay == null)
            {
                values += 0;
            }
            else
            {
                if (fullcard.getPricing().getTcgplayer().getNormal() != null && fullcard.getVariants().isFirstEdition())
                {
                    values += fullcard.getPricing().getTcgplayer().getNormal().getMarketPrice();
                }
                else if (fullcard.getPricing().getTcgplayer().getHolofoil() != null && fullcard.getVariants().isHolo())
                {
                    values += fullcard.getPricing().getTcgplayer().getHolofoil().getMarketPrice();
                }
                else if (fullcard.getPricing().getTcgplayer().getReverse() != null && fullcard.getVariants().isReverse())
                {
                    values += fullcard.getPricing().getTcgplayer().getReverse().getMarketPrice();
                }
                else if (fullcard.getVariants().isWPromo())
                {
                    values += fullcard.getPricing().getTcgplayer().getNormal().getMarketPrice();
                }
                else if (fullcard.getPricing().getTcgplayer().getNormal() != null && fullcard.getVariants().isNormal())
                {
                    values += fullcard.getPricing().getTcgplayer().getNormal().getMarketPrice();
                }
                else
                {
                    values += 0;
                }
            }
            values *= mfrm.GetCardAmount(fullcard);
        }
        totValue.setText("Total Value: " + values);
        Header.add(totValue);
    }

    //popups take 2 arguments, a card and the button based on the card to output the relevant information about the card in a popup
    private void popups(JButton cardbttn, FullCard card)
    {

            //creates a popup of the card information when the button is pressed
            cardbttn.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    popup = new JDialog(mfrm, "Card Popup");
                    popup.setLayout(new BorderLayout());
                    popup.setSize(500, 300);
                    popup.setVisible(true);


                    SingleCardPanel mainpnl = new SingleCardPanel(card, mfrm.GetCardAmount(card));

                    JPanel bttnpnl = new JPanel(new GridLayout(1, 2, 20, 0));
                    JButton deleteCard = new JButton("Delete Card?");
                    JButton backbttn = new JButton("Back");
                    bttnpnl.add(deleteCard);
                    bttnpnl.add(backbttn);

                    popup.add(mainpnl, BorderLayout.CENTER);
                    popup.add(bttnpnl, BorderLayout.SOUTH);

                    //if card is deleted, the panel revalidates and updates the collection
                    deleteCard.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            mfrm.deleteCard(card);
                            forCards.revalidate();
                            forCards.repaint();
                            popup.dispose();
                            refreshCards();
                        }
                    });

                    //closes popup
                    backbttn.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            popup.dispose();
                        }
                    });

                }
            });
    }

}

