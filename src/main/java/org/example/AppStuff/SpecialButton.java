package org.example.AppStuff;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//special buttons change color when hovered over and when pressed
public class SpecialButton extends JButton{

    private final Color bgcolor;
    private final Color pcolor;
    public SpecialButton(String name, Color backgroupColor, Color pressedColor, Color textcolor)
    {
        super(name);
        bgcolor = backgroupColor;
        pcolor = pressedColor;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(textcolor);
    }

    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed())
        {
            g.setColor(pcolor);
        }
        else if (getModel().isRollover())
        {
            g.setColor(new Color(bgcolor.getRed() - 20, bgcolor.getGreen() - 20, bgcolor.getBlue() - 20));
        }
        else
        {
            g.setColor(bgcolor);
        }

        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }


}
