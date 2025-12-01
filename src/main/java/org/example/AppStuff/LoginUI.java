package org.example.AppStuff;
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
    private String usrnme = "Username";
    private String psswrd = "Password";
    private final JLabel logo;
    private JDialog newaccount;
    public LoginUI(MainFrame mfrm)
    {
        setLayout(new GridBagLayout());

        //all components of the login are added into the input panel
        username = new JTextField(usrnme);
        password = new JTextField(psswrd);
        enterLogin = new SpecialButton("Login", new Color(34, 34,36), new Color(238, 21, 21));
        newUser = new SpecialButton("New Account", new Color(238, 21, 21), new Color(34, 34,36));
        ImageIcon temp = new ImageIcon("src/main/java/org/example/AppStuff/Logo/pokeball_PNG24.png");
        logo = new JLabel(new ImageIcon(temp.getImage().getScaledInstance(200, 200, 4)));
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        inputPanel.add(username);
        inputPanel.add(password);
        inputPanel.add(enterLogin);
        inputPanel.add(newUser);

        //input panel added to the login pane/
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;

        add(logo, c);

        c.gridy = 1;
        add(inputPanel, c);

        //when LOGIN button pressed, goes to main menu
        enterLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    if(checkAccount()){}
                //else{
                mfrm.changepanel("Main");
                //}
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

                SpecialButton ppback = new SpecialButton("Back", new Color(238, 21, 21), new Color(34, 34,36));
                SpecialButton ppconfirm = new SpecialButton("Confirm", new Color(34, 34,36), new Color(238, 21, 21));

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
        g.setColor(new Color(238, 21, 21));
        g.fillPolygon(new int[] {0, getWidth()/2, 0}, new int[] {0, 0, getHeight()/2}, 3);
        g.setColor(new Color(34, 34,36));
        g.fillPolygon(new int[] {getWidth(), getWidth(), getWidth()/2}, new int[] {getHeight(), getHeight()/2, getHeight()}, 3);
    }

    private boolean CheckAccount() //NEEDS TO BE IMPLIMENTED WITH DATABASE
    {
        return true;
    }

}
