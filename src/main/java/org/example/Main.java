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
public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database();

            db.loadUsers("src/users");
            db.loadCards("src/Cards");
            db.loadCollections("src/card holders");

            // Example: get user 1's collection
            List<Database.CollectionItem> items = db.getUserCollection(1);
            for (Database.CollectionItem item : items) {
                System.out.println(item.card.name + " x" + item.numOf);
            }

            // Example: add a card to a user
            db.addOrUpdateCard(1, 2, 5);

             items = db.getUserCollection(1);
            for (Database.CollectionItem item : items) {
                System.out.println(item.card.name + " x" + item.numOf);
            }
            // Save updated collections
            db.saveCollections("src/card holders");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
