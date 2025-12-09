package org.example.AppStuff;
import org.example.FullCard;
import org.example.NewCard;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import java.util.Scanner;

public class ManualInputUI extends JPanel{

    private final SpecialButton back;
    private final JTextField cardID;
    private final JTextField cardName;
    private final SpecialButton addcard;
    private JDialog popup;
    private final JLabel successtext = new JLabel("Card successfully added to Collection");
    private String cid = "Card ID";
    private String cname = "Card Name";
    private FullCard tempcard;

    ManualInputUI(MainFrame mfrm)
    {
        setLayout(new GridBagLayout());

        //adds all user components to the inputpanel: add card, back, CARDID text input, CARDNAME text input
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        back = new SpecialButton("back", new Color(238, 21, 21), new Color(34, 34,36), mfrm.maincolor);
        cardID = new JTextField(cid);
        cardName = new JTextField(cname);
        addcard = new SpecialButton("Add Card", new Color(34, 34,36), new Color(238, 21, 21), mfrm.maincolor);

        inputPanel.add(back);
        inputPanel.add(cardName);
        inputPanel.add(cardID);
        inputPanel.add(addcard);
        GridBagConstraints c = new GridBagConstraints();

        //adds input panel to the main panel
        add(inputPanel);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_START;

        //adds hidden success text for when card is added to database, LAZY
        add(successtext, c);
        successtext.setVisible(false);

        //button takes user back to the main panel
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });

        //addcard button creates a popup for if the user submits a working or nonworking card
        addcard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String cname = cardName.getText();
                String cID = cardID.getText(); //takes user inputs

                tempcard = null;
                cardName.setText("");
                cardID.setText("");
                tempcard = NewCard.inputCard(cname, cID); //uses the name and the ID of the card, gets card information from API call


                //ERROR CASE: if the card return does not exist, IE the user inputs were incorrect
                if(tempcard == null)
                {
                    popup = new JDialog(mfrm, "Error Popup");
                    popup.setLayout(new GridBagLayout());
                    popup.setSize(400, 400);
                    popup.setVisible(true);
                    JPanel errorpnl = new JPanel(new GridLayout(2, 1, 0, 50));

                    JLabel errorlab = new JLabel("This card is invalid\n please re-enter the card Information");
                    SpecialButton errorbttn = new SpecialButton("Back", new Color(238, 21, 21), new Color(34, 34,36), mfrm.maincolor);

                    errorpnl.add(errorlab);
                    errorpnl.add(errorbttn);
                    popup.add(errorpnl);

                    //deletes error popup
                    errorbttn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                }
                else //SUCCESS CASE: User inputted proper pokemon card name and ID
                {
                    popup = new JDialog(mfrm, "Add Card Popup");
                    popup.setLayout(new BorderLayout());
                    popup.setSize(425, 350);
                    popup.setVisible(true);

                    JPanel topqpnl = new JPanel(new BorderLayout());
                    topqpnl.add(new JLabel("Add card to Collection?", SwingConstants.CENTER), BorderLayout.CENTER);

                    SingleCardPanel mainpnl = new SingleCardPanel(tempcard, mfrm.GetCardAmount(tempcard)); //single card panel displays all the relevant card information

                    JPanel bttnpnl = new JPanel(new GridLayout(1, 2, 20, 0));
                    SpecialButton addcollect = new SpecialButton("Yes", new Color(34, 34,36), new Color(238, 21, 21), mfrm.maincolor);
                    SpecialButton canceladd = new SpecialButton("No", new Color(238, 21, 21), new Color(34, 34,36), mfrm.maincolor);
                    bttnpnl.add(addcollect);
                    bttnpnl.add(canceladd);

                    popup.add(topqpnl, BorderLayout.PAGE_START);
                    popup.add(mainpnl, BorderLayout.CENTER);
                    popup.add(bttnpnl, BorderLayout.PAGE_END);


                    //adds the new card to the users collection arraylist, NEEDS DATABASE
                    addcollect.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //WHEN DATABASE
                            successtext.setVisible(true);
                            mfrm.addToCollect(tempcard);
                            System.out.println("added");
                            popup.dispose();
                        }
                    });

                    //closes popup
                    canceladd.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            popup.dispose();
                        }
                    });
                }
            }
        });

        ////cosmetic actions for INPUTPANEL: CARDNAME, CARDID
        cardID.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                successtext.setVisible(false);
                if (cardID.getText().equals("Card ID")) {
                    cardID.setText("");
                }
            }
        });

        cardID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cardID.getText().isEmpty()) {
                    cardID.setText("Card ID");
                } else {
                    cid = cardID.getText(); // only save when real text exists
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (cardID.getText().equals("Card ID")) {
                    cardID.setText("");
                }
            }
        });

        cardName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                successtext.setVisible(false);
                if (cardName.getText().equals("Card Name")) {
                    cardName.setText("");
                }
            }
        });

        cardName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cardName.getText().isEmpty()) {
                    cardName.setText("Card Name");
                } else {
                    cname = cardName.getText(); // only save when real text exists
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (cardName.getText().equals("Card Name")) {
                    cardName.setText("");
                }
            }
        });

    }

    //makes the triangles in the background
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(238, 21, 21));
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(new Color(34, 34,36));
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }

}
