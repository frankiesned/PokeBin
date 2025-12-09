package org.example.AppStuff.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.example.AppStuff.MainFrame;
import org.example.AppStuff.SingleCardPanel;
import org.example.AppStuff.SpecialButton;
import org.example.FullCard;
import org.example.NewCard;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Camera extends JPanel {
    private VideoCapture capture;
    private JLabel camlab;
    private JLabel instruct;
    private JButton capbut;
    private Mat liveFrame;        // used by background capture loop
    private Mat capturedFrame;    // used to store snapshot
    private Mat name;
    private Mat number;
    private volatile boolean running = false;
    private Thread cameraThread;
    private JLabel errorlab;
    private JDialog popup;
    private MainFrame mfrm;

    public Camera(MainFrame mfrm) {
        this.mfrm = mfrm;
        initUi(mfrm);
        initCamera();
    }

    private void initUi(MainFrame mfrm) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 0.0;
        instruct = new JLabel("Please align card with camera and click the Scan button");
        add(instruct, c);

        c.gridy++;
        c.weighty = 1.0;
        camlab = new JLabel();
        camlab.setHorizontalAlignment(SwingConstants.CENTER);
        camlab.setPreferredSize(new Dimension(640, 480));
        add(camlab, c);

        c.gridy++;
        c.weighty = 0.0;
        capbut = new SpecialButton("Scan", new Color(34, 34, 36), new Color(238, 21, 21), mfrm.maincolor);
        add(capbut, c);

        c.gridy++;
        errorlab = new JLabel("");
        add(errorlab, c);

        capbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeSnapshotAndProcess();
            }
        });

    }

    private void initCamera() {
        liveFrame = new Mat();
        capturedFrame = new Mat();

        capture = new VideoCapture(0); // device 0, change if needed
        if (!capture.isOpened()) {
            errorlab.setText("Camera not found / cannot open device.");
            return;
        }

        running = true;
        cameraThread = new Thread(() -> {
            while (running) {
                try {
                    if (capture.isOpened()) {
                        Mat fr = new Mat();
                        boolean ok = capture.read(fr);
                        if (ok && !fr.empty()) {
                            synchronized (this) {
                                fr.copyTo(liveFrame);
                            }
                            BufferedImage img = matToBufferedImage(fr);
                            SwingUtilities.invokeLater(() -> camlab.setIcon(new ImageIcon(img)));
                        }
                        fr.release();
                    }
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception ex) {
                    final String msg = ex.getMessage();
                    SwingUtilities.invokeLater(() -> errorlab.setText("Camera error: " + msg));
                }
            }
        }, "Camera-Capture-Thread");
        cameraThread.setDaemon(true);
        cameraThread.start();
    }

    //copies livestream frame for image capturing, if the image captured and information of the card is correct
    //a popup will appear to prompt the user to add the card to their collection
    private void takeSnapshotAndProcess() {
        synchronized (this) {
            if (liveFrame == null || liveFrame.empty()) {
                errorlab.setText("No frame available to capture.");
                return;
            }
            liveFrame.copyTo(capturedFrame);
        }

                try {

                    String outPath = "src/main/java/org/example/AppStuff/Logo/poke.png";
                    Imgcodecs.imwrite(outPath, capturedFrame);

                    getCardParts(outPath);

                    Imgcodecs.imwrite("src/main/java/org/example/AppStuff/Logo/tempname.png", name);
                    Imgcodecs.imwrite("src/main/java/org/example/AppStuff/Logo/tempid.png", number);

                    String nameText = gettexts("src/main/java/org/example/AppStuff/Logo/tempname.png");
                    String idText = gettexts("src/main/java/org/example/AppStuff/Logo/tempid.png");

                    // Build FullCard via your factory
                    FullCard tempcard = NewCard.inputCard(nameText, idText);
                    if(tempcard == null)
                    {
                        popup = new JDialog(mfrm, "Error Popup");
                        popup.setLayout(new GridBagLayout());
                        popup.setSize(400, 400);
                        popup.setVisible(true);
                        JPanel errorpnl = new JPanel(new GridLayout(2, 1, 0, 50));

                        JLabel errorlab = new JLabel("This card is invalid\n please re-enter the card Information");
                        SpecialButton errorbttn = new SpecialButton("Back", new Color(238, 21, 21), new Color(34, 34,36), mfrm.maincolor);

                        errorpnl.add(errorlab);
                        errorpnl.add(errorbttn);
                        popup.add(errorpnl);

                        //deletes error popup
                        errorbttn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                popup.dispose();
                            }
                        });
                    }
                    else //SUCCESS CASE: User inputted proper pokemon card name and ID
                    {
                        popup = new JDialog(mfrm, "Add Card Popup");
                        popup.setLayout(new BorderLayout());
                        popup.setSize(425, 350);
                        popup.setVisible(true);

                        JPanel topqpnl = new JPanel(new BorderLayout());
                        topqpnl.add(new JLabel("Add card to Collection?", SwingConstants.CENTER), BorderLayout.CENTER);

                        SingleCardPanel mainpnl = new SingleCardPanel(tempcard, mfrm.GetCardAmount(tempcard)); //single card panel displays all the relevant card information

                        JPanel bttnpnl = new JPanel(new GridLayout(1, 2, 20, 0));
                        SpecialButton addcollect = new SpecialButton("Yes", new Color(34, 34,36), new Color(238, 21, 21), mfrm.maincolor);
                        SpecialButton canceladd = new SpecialButton("No", new Color(238, 21, 21), new Color(34, 34,36), mfrm.maincolor);
                        bttnpnl.add(addcollect);
                        bttnpnl.add(canceladd);

                        popup.add(topqpnl, BorderLayout.PAGE_START);
                        popup.add(mainpnl, BorderLayout.CENTER);
                        popup.add(bttnpnl, BorderLayout.PAGE_END);


                        //adds the new card to the users collection arraylist, NEEDS DATABASE
                        addcollect.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //WHEN DATABASE
                                mfrm.addToCollect(tempcard);
                                System.out.println("added");
                                popup.dispose();
                            }
                        });

                        //closes popup
                        canceladd.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                popup.dispose();
                            }
                        });
                    }

                } catch (Exception ex) {
                    System.out.println("not workin");
                }
    }

    //changes image to greyscale and gets the part of the image that has the pokemon card
    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_3BYTE_BGR;
        if (mat.channels() == 1)
        {
            type = BufferedImage.TYPE_BYTE_GRAY;
        }

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, data);
        return image;
    }


    //creates 2 smaller images of just the Name and ID parts of the cards
    public void getCardParts(String file) {
        Mat temp = Imgcodecs.imread(file);
        if (temp == null || temp.empty())
        {
            name = null;
            number = null;
            return;
        }

        Imgproc.cvtColor(temp, temp, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(temp, temp, new Size(3, 3), 0);
        Imgproc.threshold(temp, temp, 120, 255, Imgproc.THRESH_BINARY);

        int w = temp.width();
        int h = temp.height();
        if (w <= 0 || h <= 0)
        {
            name = null;
            number = null;
            return;
        }

        int nameH = Math.max(1, (int) (h * 0.18));
        int idH = Math.max(1, (int) (h * 0.18));

        name = new Mat(temp, new Rect(0, 0, w, nameH));
        number = new Mat(temp, new Rect(0, Math.max(0, h - idH), w, idH));

        temp.release();
    }

    //gets english dataset for tessdata
    private String gettexts(String file) {
        ITesseract tess = new Tesseract();
        tess.setDatapath(System.getenv("TESSDATA_PREFIX") + "tessdata");
        tess.setLanguage("eng");
        try
        {
            return tess.doOCR(new File(file)).trim();
        } catch (TesseractException e)
        {
            e.printStackTrace();
            return "NO";
        }
    }
}


