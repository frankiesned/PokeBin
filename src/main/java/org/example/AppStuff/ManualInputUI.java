package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualInputUI extends JPanel{

    private JButton back;
    private JTextField cardID;
    private JTextField cardName;
    private JButton addcard;
    private JLabel errorCard;

    ManualInputUI(MainFrame mfrm)
    {
        back = new JButton("back");
        cardID = new JTextField("Card ID");
        cardName = new JTextField("Card Name");
        addcard = new JButton("Add Card");

        add(back);
        add(cardName);
        add(cardID);
        add(addcard);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });

        addcard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname = cardName.getText();
                String cID = cardID.getText();
                //if(CARDFOUND)
                //{mfrm.changeframe("confirmadd");}
                //else {
                //errorCard = new JLabel("Card not found");
                //cardName.setText("");
                //cardID.setText("");
                //}

            }
        });
    }

}
