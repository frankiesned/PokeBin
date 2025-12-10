package org.example.AppStuff;
import net.tcgdex.sdk.TCGdex;
import org.example.FullCard;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.valueOf;

public class SingleCardPanel extends JPanel {

    private Image ogimage;
    public SingleCardPanel(FullCard tempcard, int possession) {

        setLayout(new GridLayout(1, 2, 0, 5));
        try {
            ImageIcon pokeimage = new ImageIcon(new URL(tempcard.getImage() + "/high.png"));
            ogimage = pokeimage.getImage().getScaledInstance(200, 260, Image.SCALE_SMOOTH);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }


        JPanel infopnl = new JPanel(new GridLayout(9, 1, 1, 1));
        infopnl.add(new JLabel("Name: " + tempcard.getName(), SwingConstants.CENTER));
        infopnl.add(new JLabel("ID: " + tempcard.getId(), SwingConstants.CENTER));
        infopnl.add(new JLabel("Rarity: " + tempcard.getRarity(), SwingConstants.CENTER));
        infopnl.add(new JLabel("Pack: " + tempcard.getSet().getName(), SwingConstants.CENTER));

        FullCard.TCGPlayerPrice tcgplay = tempcard.getPricing().getTcgplayer();

        JLabel pokeVariants;
        JLabel pokemarkprice;
        JLabel pokehighprice;
        if (tcgplay == null) {

            pokeVariants = new JLabel("Variant: NA", SwingConstants.CENTER);
            pokemarkprice = new JLabel("Market Price: NA", SwingConstants.CENTER);
            pokehighprice = new JLabel("High Price: NA", SwingConstants.CENTER);
        } else {
            if (tempcard.getPricing().getTcgplayer().getNormal() != null && tempcard.getVariants().isFirstEdition()) {
                pokeVariants = new JLabel("Variant: First Edition", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getMarketPrice()), SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getHighPrice()), SwingConstants.CENTER);

            } else if (tempcard.getPricing().getTcgplayer().getHolofoil() != null && tempcard.getVariants().isHolo()) {
                pokeVariants = new JLabel("Variant: Holographic", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: " + valueOf(tempcard.getPricing().getTcgplayer().getHolofoil().getMarketPrice()), SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: " + valueOf(tempcard.getPricing().getTcgplayer().getHolofoil().getHighPrice()), SwingConstants.CENTER);
            } else if (tempcard.getPricing().getTcgplayer().getReverse() != null && tempcard.getVariants().isReverse()) {
                pokeVariants = new JLabel("Variant: Reverse", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: " + valueOf(tempcard.getPricing().getTcgplayer().getReverse().getMarketPrice()), SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: " + valueOf(tempcard.getPricing().getTcgplayer().getReverse().getHighPrice()), SwingConstants.CENTER);
            } else if (tempcard.getVariants().isWPromo()) {
                pokeVariants = new JLabel("Variant: WPromo", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getMarketPrice()), SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getHighPrice()), SwingConstants.CENTER);
            } else if (tempcard.getPricing().getTcgplayer().getNormal() != null && tempcard.getVariants().isNormal()) {
                pokeVariants = new JLabel("Variant: Normal", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getMarketPrice()), SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: " + valueOf(tempcard.getPricing().getTcgplayer().getNormal().getHighPrice()), SwingConstants.CENTER);
            } else {
                pokeVariants = new JLabel("Variant: NA", SwingConstants.CENTER);
                pokemarkprice = new JLabel("Market Price: NA", SwingConstants.CENTER);
                pokehighprice = new JLabel("High Price: NA", SwingConstants.CENTER);
            }
        }

        infopnl.add(pokeVariants);
        infopnl.add(pokemarkprice);
        infopnl.add(pokehighprice);
        infopnl.add(new JLabel("In Possession: " + possession, SwingConstants.CENTER));

        add(new JLabel(new ImageIcon(ogimage), SwingConstants.CENTER));
        add(infopnl);
    }


}
