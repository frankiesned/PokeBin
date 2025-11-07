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
    private JButton enterLogin;
    public LoginUI(MainFrame mfrm)
    {
        setLayout(new GridLayout(3, 3, (int)(10/*mfrm.getScaler()*/), 10));
        username = new JTextField("Username");
        password = new JTextField("Password");
        enterLogin = new JButton("Login");
        enterLogin.setBackground(new Color(34, 34,36));
        enterLogin.setForeground(new Color(240, 240, 240));
        enterLogin.setOpaque(true);
        enterLogin.setBorderPainted(false);
        enterLogin.setFocusPainted(false);     // no focus glow
        enterLogin.setContentAreaFilled(true);
        add(username);
        add(password);
        add(enterLogin);

        enterLogin.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(enterLogin.getModel().isPressed())
                {
                    enterLogin.setBackground(new Color(238, 21, 21));
                }
                else
                {
                    enterLogin.setBackground(new Color(34, 34,36));
                }
            }
        });

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

    private boolean CheckAccount() //Checks if the account name and password are in the database, needs JQuery?
    {
        return true;
    }

}
