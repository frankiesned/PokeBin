package org.example;
import java.util.List;
import java.util.Map;

public class FullCard {
    private String category;
    private String id;
    private String illustrator;
    private String image;
    private String localId;
    private String name;
    private String rarity;
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

    public String getCategory() { return category; }
    public String getId() { return id; }
    public String getIllustrator() { return illustrator; }
    public String getImage() { return image; }
    public String getLocalId() { return localId; }
    public String getName() { return name; }
    public String getRarity() { return rarity; }
    public Set getSet() { return set; }
    public Variants getVariants() { return variants; }
    public List<Integer> getDexId() { return dexId; }
    public int getHp() { return hp; }
    public List<String> getTypes() { return types; }
    public String getEvolveFrom() { return evolveFrom; }
    public String getDescription() { return description; }
    public String getStage() { return stage; }
    public List<Attack> getAttacks() { return attacks; }
    public List<Weakness> getWeaknesses() { return weaknesses; }
    public int getRetreat() { return retreat; }
    public String getRegulationMark() { return regulationMark; }
    public Legal getLegal() { return legal; }
    public String getUpdated() { return updated; }
    public Pricing getPricing() { return pricing; }


    public static class Set {
        private CardCount cardCount;
        private String id;
        private String logo;
        private String name;
        private String symbol;

        public CardCount getCardCount() {
            return cardCount;
        }

        public String getId() { return id; }
        public String getLogo() { return logo; }
        public String getName() { return name; }
        public String getSymbol() { return symbol; }
    }


    public static class CardCount {
        private int official;
        private int total;

        public int getOfficial() { return official; }
        public int getTotal() { return total; }
    }


    public static class Variants {
        private boolean firstEdition;
        private boolean holo;
        private boolean normal;
        private boolean reverse;
        private boolean wPromo;

        public boolean isFirstEdition() { return firstEdition; }
        public boolean isHolo() { return holo; }
        public boolean isNormal() { return normal; }
        public boolean isReverse() { return reverse; }
        public boolean isWPromo() { return wPromo; }
    }

    public static class Attack {
        private List<String> cost;
        private String name;
        private String effect;
        private Integer damage;

        public List<String> getCost() { return cost; }
        public String getName() { return name; }
        public String getEffect() { return effect; }
        public Integer getDamage() { return damage; }
    }

    public static class Weakness {
        private String type;
        private String value;

        public String getType() { return type; }
        public String getValue() { return value; }
    }

    public static class Legal {
        private boolean standard;
        private boolean expanded;

        public boolean isStandard() { return standard; }
        public boolean isExpanded() { return expanded; }
    }

    public static class Pricing {
        private PriceData cardmarket;
        private TCGPlayerPrice tcgplayer;

        public PriceData getCardmarket() { return cardmarket; }
        public TCGPlayerPrice getTcgplayer() { return tcgplayer; }
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

        public String getUpdated() { return updated; }
        public String getUnit() { return unit; }
        public double getAvg() { return avg; }
        public double getLow() { return low; }
        public double getTrend() { return trend; }
        public double getAvg1() { return avg1; }
        public double getAvg7() { return avg7; }
        public double getAvg30() { return avg30; }
        public double getAvgHolo() { return avgHolo; }
        public double getLowHolo() { return lowHolo; }
        public double getTrendHolo() { return trendHolo; }
        public double getAvg1Holo() { return avg1Holo; }
        public double getAvg7Holo() { return avg7Holo; }
        public double getAvg30Holo() { return avg30Holo; }
    }

    public static class TCGPlayerPrice {
        private String updated;
        private String unit;
        private PriceType normal;
        private PriceType reverse;

        public String getUpdated() { return updated; }
        public String getUnit() { return unit; }
        public PriceType getNormal() { return normal; }
        public PriceType getReverse() { return reverse; }
    }

    public static class PriceType {
        private double lowPrice;
        private double midPrice;
        private double highPrice;
        private double marketPrice;
        private double directLowPrice;

        public double getLowPrice() { return lowPrice; }
        public double getMidPrice() { return midPrice; }
        public double getHighPrice() { return highPrice; }
        public double getMarketPrice() { return marketPrice; }
        public double getDirectLowPrice() { return directLowPrice; }
    }
}