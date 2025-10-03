package org.example;
import java.util.List;
import java.util.Map;

public class FullCard {
    String category;
    String id;
    String illustrator;
    String image;
    String localId;
    String name;
    String rarity;
    private Set set;
    private Variants variants;
    private List<Integer> dexId;
    private int hp;
    private List<String> types;
    private String evolveFrom;
    private String description;
    private String stage;
    private List<Attack> attacks;
    private List<Weakness> weaknesses;
    private int retreat;
    private String regulationMark;
    private Legal legal;
    private String updated;
    private Pricing pricing;


    public static class Set {
        private CardCount cardCount;
        private String id;
        private String logo;
        private String name;
        private String symbol;
    }

    public static class CardCount {
        private int official;
        private int total;
    }


    public static class Variants {
        private boolean firstEdition;
        private boolean holo;
        private boolean normal;
        private boolean reverse;
        private boolean wPromo;
    }

    public static class Attack {
        private List<String> cost;
        private String name;
        private String effect;
        private Integer damage;
    }

    public static class Weakness {
        private String type;
        private String value;
    }

    public static class Legal {
        private boolean standard;
        private boolean expanded;
    }

    public static class Pricing {
        private PriceData cardmarket;
        private TCGPlayerPrice tcgplayer;
    }

    public static class PriceData {
        private String updated;
        private String unit;
        private double avg;
        private double low;
        private double trend;
        private double avg1;
        private double avg7;
        private double avg30;
        private double avgHolo;
        private double lowHolo;
        private double trendHolo;
        private double avg1Holo;
        private double avg7Holo;
        private double avg30Holo;
    }

    public static class TCGPlayerPrice {
        private String updated;
        private String unit;
        private PriceType normal;
        private PriceType reverse;
    }

    public static class PriceType {
        private double lowPrice;
        private double midPrice;
        private double highPrice;
        private double marketPrice;
        private double directLowPrice;
    }
}