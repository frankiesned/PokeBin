package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScanUI extends JPanel{

    private JButton back;

    ScanUI(MainFrame mfrm)
    {
        back = new JButton("back");

        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });
    }


}
