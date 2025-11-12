package org.example.AppStuff;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CollectionUI extends JPanel{

    private SpecialButton back;
    private SpecialButton showGraph;
    private JLabel totValue;
    private JLabel totCost;
    private JLabel netWorth;
    private ArrayList<JButton> cardholder = new ArrayList<>();
    private ArrayList<Integer> cardnumtracker = new ArrayList<>();
    JScrollPane scrllr;




    CollectionUI(MainFrame mfrm)
    {
        setLayout(new BorderLayout());

        JPanel Header = new JPanel(new GridLayout(1, 5));
        back = new SpecialButton("back", new Color(238, 21, 21), new Color(34, 34,36));
        totValue = new JLabel("Total Value:" /*getcard values*/, SwingConstants.CENTER);
        totValue.setBackground(new Color(34, 34,36));
        totValue.setForeground(new Color(240, 240, 240));
        totValue.setOpaque(true);
        totCost = new JLabel("Total Cost:" /*getcard values*/, SwingConstants.CENTER);
        totCost.setBackground(new Color(34, 34,36));
        totCost.setOpaque(true);
        totCost.setForeground(new Color(240, 240, 240));
        netWorth = new JLabel("Net Value:" /*getcard values*/, SwingConstants.CENTER);
        netWorth.setBackground(new Color(34, 34,36));
        netWorth.setOpaque(true);
        netWorth.setForeground(new Color(240, 240, 240));
        showGraph = new SpecialButton("Show Graph", new Color(238, 21, 21), new Color(34, 34,36));

        Header.add(back);
        Header.add(totValue);
        Header.add(totCost);
        Header.add(netWorth);
        Header.add(showGraph);
        add(Header, BorderLayout.NORTH);

        JPanel forCards = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        scrllr = new JScrollPane(forCards);
        scrllr.setBorder(null);
        scrllr.getVerticalScrollBar().setUnitIncrement(16);
        add(scrllr, BorderLayout.CENTER);

        //add for loop that adds a button to arraylist for each return on the database
        //Arraylist.add(new JButton(NAMEOFPOKEMON, POKEMONIMAGE));
        cardholder.forEach(this::add);
        scrllr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });

        for (JButton card : cardholder) {
            card.setLayout(new BorderLayout());
            JLabel label = new JLabel("Text"); //have text be the number of a type of card in their possession
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            card.add(label, BorderLayout.SOUTH);
            card.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel cardpopup = new JPanel();
                    JButton deleteCard = new JButton("Delete");

                    //cardpopup.add(new ImageIcon()); //image name here
                    cardpopup.add(new JLabel("CARDTEXTHERE"));
                    cardpopup.add(deleteCard);

                    deleteCard.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel confirmpan = new JPanel();
                            JLabel confirmlabel = new JLabel("Are you sure you want to delete CARDNAME?");
                            JButton confirmdelete = new JButton("Confirm");
                            JButton confirmback = new JButton("Cancel");

                            confirmpan.add(confirmlabel);
                            confirmpan.add(confirmdelete);
                            confirmpan.add(confirmback);

                            confirmback.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    confirmpan.hide();
                                }
                            });
                             
                            confirmdelete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("asdfjkhhaskjdfha");
                                }
                            });
                        }
                    });

                    cardpopup.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            cardpopup.hide();
                        }
                    });
                }
            });
        }
    }
}
