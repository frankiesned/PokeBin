package org.example.AppStuff.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import org.example.AppStuff.SpecialButton;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Camera extends JPanel
{
    private VideoCapture capture;
    private JLabel camlab;
    private JLabel instruct;
    private JButton capbut;
    private Mat card;
    private Mat name;
    private Mat number;
    private Timer timer;

    public Camera()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        instruct = new JLabel("Please align card with camera and click the Scan button");
        add(instruct);
        c.anchor = GridBagConstraints.CENTER;
        capture = new VideoCapture(0);
        camlab = new JLabel();
        add(camlab);
        c.anchor = GridBagConstraints.PAGE_END;
        capbut = new SpecialButton("Scan", new Color(34, 34,36), new Color(238, 21, 21));
        add(capbut);

        timer = new Timer(33, e -> updateFrame());
        timer.start();

        capbut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                card = new Mat();
                capture.read(card);
                Imgcodecs.imwrite("poke.png", card);
                getCardParts("poke.png");
            }
        });

    }

    private void updateFrame()
    {
        if (capture.isOpened())
        {
            capture.read(card);
            if (!card.empty())
            {
                Image imageToShow = matToBufferedImage(card);
                camlab.setIcon(new ImageIcon(imageToShow));
            }
        }
    }

    private void captureImage()
    {
        if (!card.empty())
        {
            Imgcodecs.imwrite("captured_card.png", card);
            System.out.println("Saved captured_card.png");
        }
    }

    private BufferedImage matToBufferedImage(Mat mat)
    {

        int type = BufferedImage.TYPE_3BYTE_BGR;
        if (mat.channels() == 1)
        {
            type = BufferedImage.TYPE_BYTE_GRAY;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] b = new byte[bufferSize];
        mat.get(0, 0, b);

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);

        return image;
    }

    public void stopCamera()
    {
        timer.stop();
        capture.release();
    }



    public void getCardParts(String file)
    {
        Mat temp = Imgcodecs.imread(file);
        Imgproc.cvtColor(temp, temp, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(temp, temp, new Size(3, 3), 0);
        Imgproc.threshold(temp, temp, 120, 255, Imgproc.THRESH_BINARY);
        Mat[] matHolder = new Mat[2];
        name = new Mat(temp, new Rect(0,0,temp.width(), (int)(temp.height() * 0.18)));
        number = new Mat(temp, new Rect(0, (int)(temp.height()*0.82), temp.width(), (int)(temp.width() * 0.18)));
    }

    public Mat getcardname()
    {
        return name;
    }

    public Mat getcardnumber()
    {
        return number;
    }



}
