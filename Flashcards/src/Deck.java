import java.util.ArrayList;

public class Deck {
    private ArrayList<Flashcard> deck = new ArrayList<>();
    public Deck() {

    }

    // getters and setters

    protected ArrayList<Flashcard> getDeck() {
        return deck;
    }

    private void setDeck(ArrayList<Flashcard> deck) {
        this.deck = deck;
    }
}
