package org.example.AppStuff;
import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class MainFrame extends JFrame{

    private JPanel mpnl;
    private CardLayout clayout;
    //private double scaler;

//    public double getScaler()
//    {
//        return scaler;
//    }
    public MainFrame() {
        setSize(1000, 500);
        LoginUI loginPanel = new LoginUI(this);
        MainMenuUI mainMenuPanel = new MainMenuUI(this);
        CollectionUI collectionPanel = new CollectionUI(this);
        ManualInputUI manualInputPanel = new ManualInputUI(this);
        ScanUI scanCardPanel = new ScanUI(this);
        clayout = new CardLayout();
        mpnl = new JPanel(clayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //scaler = (double)(this.getHeight()/this.getWidth());
        mpnl.add(loginPanel,"login" );
        mpnl.add(mainMenuPanel, "Main");
        mpnl.add(collectionPanel, "collection");
        mpnl.add(manualInputPanel, "manualinput");
        mpnl.add(scanCardPanel, "scan");

        add(mpnl);
        clayout.show(mpnl, "login");
        setVisible(true);
    }

    public void changepanel(String name)
    {
        CardLayout cl = (CardLayout) mpnl.getLayout();
        cl.show(mpnl, String.valueOf(name));

    }

    public static void main(String[] args)
    {
        new MainFrame();
    }

}
