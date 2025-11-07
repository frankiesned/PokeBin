package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuUI extends JPanel{

    private JButton manual;
    private JButton scnnr;
    private JButton logout;
    private JButton collection;

    MainMenuUI(MainFrame mfrm)
    {
        manual = new JButton("Manual Input");
        scnnr = new JButton("Scan Card");
        logout = new JButton("Logout");
        collection = new JButton("Collection");

        add(manual);
        add(scnnr);
        add(collection);
        add(logout);

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

}
