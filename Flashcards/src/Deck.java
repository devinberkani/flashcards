import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Deck {
    // old list for having an arraylist of flashcard objects
    private ArrayList<Flashcard> deck = new ArrayList<>();
    private LinkedHashMap<String, String> hashMapDeck = new LinkedHashMap<>();
    public Deck() {

    }

    // getters and setters

    protected ArrayList<Flashcard> getDeck() {
        return deck;
    }

    private void setDeck(ArrayList<Flashcard> deck) {
        this.deck = deck;
    }

    protected LinkedHashMap<String, String> getHashMapDeck() {
        return hashMapDeck;
    }

    protected void setHashMapDeck(LinkedHashMap<String, String> hashMapDeck) {
        this.hashMapDeck = hashMapDeck;
    }
}
