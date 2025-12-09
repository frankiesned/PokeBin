package org.example;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* made with help from chatgpt*/
public class Database {
    public class User {
        public int id;
        public String username;
        public String password;

        public User(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }
    }
    // Card.java
    public class Card {
        public int cardId;
        public String name;
        public String uniq;
        public int power;

        public Card(int cardId, String name, String uniq, int power) {
            this.cardId = cardId;
            this.name = name;
            this.uniq = uniq;
            this.power = power;
        }
    }
    // CollectionItem.java
    public class CollectionItem {
        public Card card;
        public int numOf;

        public CollectionItem(Card card, int numOf) {
            this.card = card;
            this.numOf = numOf;
        }
    }


    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Card> cards = new HashMap<>();
    private Map<Integer, List<CollectionItem>> collections = new HashMap<>();

    public Database() { }

    // ------------------ LOAD DATA -----------------------

    public void loadUsers(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];

            users.put(id, new User(id, username, password));
        }
        br.close();
    }

    public void loadCards(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int cardId = Integer.parseInt(parts[0]);
            String name = parts[1];
            String type = parts[2];
            int power = Integer.parseInt(parts[3]);

            cards.put(cardId, new Card(cardId, name, type, power));
        }
        br.close();
    }

    public void loadCollections(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int userId = Integer.parseInt(parts[0]);
            int cardId = Integer.parseInt(parts[1]);
            int numOf = Integer.parseInt(parts[2]);

            Card card = cards.get(cardId);
            CollectionItem item = new CollectionItem(card, numOf);

            collections.computeIfAbsent(userId, k -> new ArrayList<>()).add(item);
        }
        br.close();
    }

    // ------------------ SAVE DATA -----------------------

    public void saveCollections(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        for (int userId : collections.keySet()) {
            for (CollectionItem item : collections.get(userId)) {
                bw.write(userId + "," +
                        item.card.cardId + "," +
                        item.numOf + "\n");
            }
        }
        bw.close();
    }

    // ------------------ QUERY METHODS ---------------------

    public User getUserById(int id) {
        return users.get(id);
    }

    public int getIdByUser(String nm, String pw)
    {
        for(Integer key: users.keySet())
        {
            if(users.get(key).username.equals(nm) && users.get(key).password.equals(pw))
            {
                return key;
            }
        }
        return 0;
    }

    public boolean UserinDB(String nm, String pw) {
        for(User us: users.values())
        {
            if(us.username.equals(nm) && us.password.equals(pw))
            {
                return true;
            }
        }
        return false;
    }

    public Card getCardById(int id) {
        return cards.get(id);
    }

    public List<CollectionItem> getUserCollection(int userId) {
        return collections.getOrDefault(userId, new ArrayList<>());
    }


    public void addOrUpdateCard(int userId, int cardId, int amount) {
        Card card = cards.get(cardId);
        if (card == null) {
            System.out.println("Card ID " + cardId + " not found.");
            return;
        }

        // Get the user's collection (or create one)
        List<CollectionItem> items =
                collections.computeIfAbsent(userId, k -> new ArrayList<>());

        // Look for the card in the user's collection
        for (CollectionItem item : items) {
            if (item.card.cardId == cardId) {
                //  Card already exists → update numOf
                item.numOf += amount;
                return;
            }
        }

        //  If not found → add as new entry
        items.add(new CollectionItem(card, amount));
    }


    
   public void removeOrDecrementCard(int userId, int cardId, int amount) {
    List<CollectionItem> items = collections.get(userId);
    if (items == null) return;

    Iterator<CollectionItem> it = items.iterator();

    while (it.hasNext()) {
        CollectionItem item = it.next();

        if (item.card.cardId == cardId) {

            // If removing more than or equal to what they own → delete card
            if (amount >= item.numOf) {
                it.remove();
                return;
            }

            // Otherwise, decrement the quantity
            item.numOf -= amount;
            return;
        }
    }
}
public User addUser(String username, String password) {
    // Generate new unique ID
    int newId = users.keySet().stream()
            .mapToInt(v -> v)
            .max()
            .orElse(0) + 1;

    User u = new User(newId, username, password);
    users.put(newId, u);

    // Ensure they have an empty collection list
    collections.put(newId, new ArrayList<>());

    return u;
}
}
