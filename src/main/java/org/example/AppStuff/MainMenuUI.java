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

    MainMenuUI(MainFrame mfrm)
    {
        setLayout(new GridBagLayout());

        manual = new SpecialButton("Manual Input", new Color(34, 34,36), new Color(238, 21, 21));
        scnnr = new SpecialButton("Scan Card", new Color(34, 34,36), new Color(238, 21, 21));
        logout = new SpecialButton("Logout", new Color(238, 21, 21), new Color(34, 34,36));
        collection = new SpecialButton("Collection", new Color(34, 34,36), new Color(238, 21, 21));

        JPanel bttnpnl = new JPanel();
        bttnpnl.setLayout(new GridLayout(4, 1, 10, 10));
        bttnpnl.add(manual);
        bttnpnl.add(scnnr);
        bttnpnl.add(collection);
        bttnpnl.add(logout);

        add(bttnpnl);

        manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("manualinput");
                //}
            }
        });

        scnnr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("scan");
                //}
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("login");
                //}
            }
        });

        collection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("collection");
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
