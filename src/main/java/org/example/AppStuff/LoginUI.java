package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginUI extends JPanel{
    private JTextField username;
    private JTextField password;
    private SpecialButton enterLogin;
    public LoginUI(MainFrame mfrm)
    {
        setLayout(new GridBagLayout());

        username = new JTextField("Username");
        password = new JTextField("Password");
        enterLogin = new SpecialButton("Login", new Color(34, 34,36), new Color(238, 21, 21));

        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        inputPanel.add(username);
        inputPanel.add(password);
        inputPanel.add(enterLogin);

        add(inputPanel);


        enterLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("Main");
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

    private boolean CheckAccount() //Checks if the account name and password are in the database, needs JQuery?
    {
        return true;
    }

}
