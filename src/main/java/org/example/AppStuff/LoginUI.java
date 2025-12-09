package org.example.AppStuff;
import org.example.Database;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginUI extends JPanel{
    private final JTextField username;
    private final JTextField password;
    private final SpecialButton enterLogin;
    private final SpecialButton newUser;
    //private SpecialButton darkmodebttn;
    private String usrnme = "Username";
    private String psswrd = "Password";
    private final JLabel logo;
    private JDialog newaccount;
    private MainFrame mfrm;
    public LoginUI(MainFrame mfrm)
    {
        this.mfrm = mfrm;
        setLayout(new GridBagLayout());


        //all components of the login are added into the input panel
        username = new JTextField(usrnme);
        password = new JTextField(psswrd);
        enterLogin = new SpecialButton("Login", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);
        newUser = new SpecialButton("New Account", mfrm.coloraccent1, mfrm.coloraccent2, mfrm.maincolor);
        //darkmodebttn = new SpecialButton("Luxury Mode", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);
        ImageIcon temp = new ImageIcon("src/main/java/org/example/AppStuff/Logo/pokeball_PNG24.png");
        logo = new JLabel(new ImageIcon(temp.getImage().getScaledInstance(200, 200, 4)));
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        //JPanel topbar = new JPanel(new BorderLayout());
        //topbar.setOpaque(false);
        //topbar.add(darkmodebttn, BorderLayout.EAST);

        inputPanel.add(username);
        inputPanel.add(password);
        inputPanel.add(enterLogin);
        inputPanel.add(newUser);

        //input panel added to the login pane/
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.VERTICAL;

        add(logo, c);

        c.gridy = 1;
        add(inputPanel, c);

//        c.gridx = 0;
//        c.gridy = 0;
//        c.weightx = 1.0;
//        c.anchor = GridBagConstraints.FIRST_LINE_END;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        add(topbar, c);

        //when LOGIN button pressed, goes to main menu
        enterLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!mfrm.db.UserinDB(username.getText(), password.getText()))
                {

                }
                else{
                    mfrm.userID = mfrm.db.getIdByUser(username.getText(), password.getText());
                    System.out.print(mfrm.userID);
                    for (Database.CollectionItem itm : mfrm.db.getUserCollection(mfrm.userID)) {

                        if (itm.card == null) {
                            System.out.println("ERROR: Bad collection entry for user " + mfrm.userID);
                            continue;
                        }

                        mfrm.addToCollectFromDB(itm.card.name, itm.card.uniq);
                        mfrm.addToCollectNumsFromDB(itm.card.name, itm.card.uniq, itm.numOf);
                    }
                    mfrm.changepanel("Main");
                }
            }
        });

        //when NEW USER pressed, creates a pop-up that allows the user to add their credentials to the database
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newaccount = new JDialog(mfrm, "New Account");
                newaccount.setLayout(new BorderLayout());
                newaccount.setSize(400, 400);
                newaccount.setVisible(true);

                JPanel toppnl = new JPanel();
                JPanel inputpnl = new JPanel(new GridBagLayout());
                JPanel bttnpnl = new JPanel(new GridLayout(1, 2, 20, 0));

                toppnl.add(new JLabel("Please Enter New Credentials", SwingConstants.CENTER), BorderLayout.PAGE_START);

                JTextField newname = new JTextField(usrnme);
                JTextField newpassword = new JTextField(psswrd);

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;

                c.anchor = GridBagConstraints.PAGE_START;
                inputpnl.add(newname, c);

                c.gridy = 1;
                inputpnl.add(newpassword, c);

                SpecialButton ppback = new SpecialButton("Back", mfrm.coloraccent1, mfrm.coloraccent2, mfrm.maincolor);
                SpecialButton ppconfirm = new SpecialButton("Confirm", mfrm.coloraccent2, mfrm.coloraccent1, mfrm.maincolor);

                bttnpnl.add(ppback);
                bttnpnl.add(ppconfirm);

                newaccount.add(toppnl, BorderLayout.PAGE_START);
                newaccount.add(inputpnl, BorderLayout.CENTER);
                newaccount.add(bttnpnl, BorderLayout.PAGE_END);

                ppback.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newaccount.dispose();
                    }
                });

                //THE DATA BASE NEEDS THIS BUTTON TO TAKE THE NAME AND PASSWORD
                ppconfirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mfrm.db.addUser(newname.getText(), newpassword.getText());
                        newaccount.dispose();
                    }
                });


                //cosmetic actions for NEWUSER popup INPUTS
                newname.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (newname.getText().equals("Username")) {
                            newname.setText("");
                        }
                    }
                });

                newname.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        String newusrnme = "Username";
                        if (newname.getText().isEmpty()) {
                            newname.setText("Username");
                        } else {
                            newusrnme = newname.getText();
                        }
                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        if (newname.getText().equals("Username")) {
                            newname.setText("");
                        }
                    }
                });

                newname.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (newname.getText().equals("Username")) {
                            newname.setText("");
                        }
                    }
                });

                newpassword.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        String newpsswrd = "Password";
                        if (newpassword.getText().isEmpty()) {
                            newpassword.setText("Password");
                        } else {
                            newpsswrd = newpassword.getText();
                        }
                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        if (newpassword.getText().equals("Password")) {
                            newpassword.setText("");
                        }
                    }
                });


            }
        });

        //cosmetic actions for LOGIN popup INPUTS
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                }
            }
        });

        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setText("Username");
                } else {
                    usrnme = username.getText(); // only save when real text exists
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                }
            }
        });

        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                }
            }
        });

//        darkmodebttn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mfrm.changedarkmode();
//            }
//        });

        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (password.getText().isEmpty()) {
                    password.setText("Password");
                } else {
                    psswrd = password.getText();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (password.getText().equals("Password")) {
                    password.setText("");
                }
            }
        });

    }

    //makes the triangles in the background
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(mfrm.coloraccent1);
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(mfrm.coloraccent2);
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }

    private boolean CheckAccount() //NEEDS TO BE IMPLIMENTED WITH DATABASE
    {
        return true;
    }

}
