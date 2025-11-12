package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualInputUI extends JPanel{

    private SpecialButton back;
    private JTextField cardID;
    private JTextField cardName;
    private SpecialButton addcard;
    private JLabel errorCard;

    ManualInputUI(MainFrame mfrm)
    {
        setLayout(new GridBagLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        back = new SpecialButton("back", new Color(238, 21, 21), new Color(34, 34,36));
        cardID = new JTextField("Card ID");
        cardName = new JTextField("Card Name");
        addcard = new SpecialButton("Add Card", new Color(34, 34,36), new Color(238, 21, 21));

        inputPanel.add(back);
        inputPanel.add(cardName);
        inputPanel.add(cardID);
        inputPanel.add(addcard);

        add(inputPanel);

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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(238, 21, 21));
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(new Color(34, 34,36));
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }

}
