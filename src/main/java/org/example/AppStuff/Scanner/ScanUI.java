package org.example.AppStuff.Scanner;
import org.example.AppStuff.MainFrame;
import org.example.AppStuff.SpecialButton;
import net.sourceforge.tess4j.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScanUI extends JPanel{

    private Camera cam;


    public ScanUI(MainFrame mfrm)
    {
        setLayout(new BorderLayout());
        JPanel bttnpnl = new JPanel(new GridLayout(1, 1));
        SpecialButton back = new SpecialButton("back", new Color(238, 21, 21), new Color(34, 34, 36), mfrm.maincolor);
        cam = new Camera(mfrm);
        System.out.println("stinky3");

        bttnpnl.add(back);

        add(bttnpnl, BorderLayout.NORTH);
        add(cam, BorderLayout.CENTER);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mfrm.changepanel("Main");
            }
        });
    }


    //makes the triangles in the background
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(34, 34,36));
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(new Color(238, 21, 21));
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }


}
