package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CollectionUI extends JPanel{

    private JButton back;
    private ArrayList<JButton> cardholder = new ArrayList<>();
    private ArrayList<Integer> cardnumtracker = new ArrayList<>();
    JScrollPane scroller;




    CollectionUI(MainFrame mfrm)
    {
        back = new JButton("back");
        scroller = new JScrollPane(this);

        //add for loop that adds a button to arraylist for each return on the database
        //Arraylist.add(new JButton(NAMEOFPOKEMON, POKEMONIMAGE));

        setLayout(new GridLayout(3, 1));
        back.setHorizontalAlignment(SwingConstants.LEFT);
        back.setVerticalAlignment(SwingConstants.TOP);
        add(back, BorderLayout.NORTH);
        cardholder.forEach(this::add);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
