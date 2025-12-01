package org.example.AppStuff.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.example.AppStuff.SpecialButton;
import org.example.FullCard;
import org.example.NewCard;
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
    private boolean camrun = true;
    private Mat tempframe;
    private JLabel errorlab;
    private FullCard actcard;

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
        tempframe = new Mat();
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
                Imgcodecs.imwrite("src/main/java/org/example/AppStuff/Logo/poke.png", card);
                getCardParts("src/main/java/org/example/AppStuff/Logo/poke.png");
                actcard = findcard();

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
                Image tempimage = mattoimage(card);
                camlab.setIcon(new ImageIcon(tempimage));
            }
        }
    }

    private BufferedImage mattoimage(Mat mat)
    {

        int type = BufferedImage.TYPE_3BYTE_BGR;
        if (mat.channels() == 1)
        {
            type = BufferedImage.TYPE_BYTE_GRAY;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] b = new byte[bufferSize];
        mat.get(0, 0, b);

        BufferedImage tempimage = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] target = ((DataBufferByte) tempimage.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, target);

        return tempimage;
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
        name = new Mat(temp, new Rect(0,0,temp.width(), (int)(temp.height() * 0.18)));
        number = new Mat(temp, new Rect(0, (int)(temp.height()*0.82), temp.width(), (int)(temp.height() * 0.18)));
    }

    private FullCard findcard()
    {

        if(name == null || number == null)
        {
            errorlab = new JLabel("not working, please us manual input");
        }

        Imgcodecs.imwrite("src/main/java/org/example/AppStuff/Logo/tempname.png", name);
        Imgcodecs.imwrite("src/main/java/org/example/AppStuff/Logo/tempid.png", number);

        FullCard tempcard = NewCard.inputCard(gettexts("src/main/java/org/example/AppStuff/Logo/tempname.png"), gettexts("src/main/java/org/example/AppStuff/Logo/tempid.png"));
        return tempcard;

    }

    private String gettexts(String file)
    {
        ITesseract tess = new Tesseract();
        tess.setDatapath(System.getenv("TESSDATA_PREFIX") + "tessdata"); //CHANGE THIS TO "C:\WHERE EVER\Tesseract-OCR\tessdata"
        tess.setLanguage("eng");
        try
        {
            return tess.doOCR(new File(file)).trim();
        }
        catch (TesseractException e)
        {
            return "stinkyerror";
        }
    }


}
