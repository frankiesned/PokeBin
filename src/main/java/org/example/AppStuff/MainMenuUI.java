package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuUI extends JPanel{

    private SpecialButton manual;
    private SpecialButton scnnr;
    private SpecialButton logout;
    private SpecialButton collection;
    private MainFrame mfrm;

    MainMenuUI(MainFrame mfrm)
    {
        this.mfrm = mfrm;
        setLayout(new GridBagLayout());

        manual = new SpecialButton("Manual Input", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);
        scnnr = new SpecialButton("Scan Card", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);
        logout = new SpecialButton("Logout", mfrm.coloraccent1, mfrm.coloraccent2, mfrm.maincolor);
        collection = new SpecialButton("Collection", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);

        JPanel bttnpnl = new JPanel();
        bttnpnl.setLayout(new GridLayout(4, 1, 10, 10));
        bttnpnl.add(manual);
        bttnpnl.add(scnnr);
        bttnpnl.add(collection);
        bttnpnl.add(logout);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;

        add(bttnpnl, c);

        manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mfrm.changepanel("manualinput");
            }
        });

        scnnr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mfrm.changepanel("scan");
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mfrm.changepanel("login");
            }
        });

        collection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mfrm.collectionPanel.refreshCards();
                mfrm.changepanel("collection");
            }
        });


    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(mfrm.coloraccent1);
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(mfrm.coloraccent2);
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }

}
