import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private int deckSize;
    public Menu() {

    }

    protected void goThroughDeck(Deck deck) {

        for (int i = 0; i < getDeckSize(); i++) {
            String front = deck.getDeck().get(i).getFront();
            String back = deck.getDeck().get(i).getBack();
            System.out.println("Print the definition of \"" + front + "\":");
            String answer = scanner.nextLine();
            if (answer.equals(back)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The right answer is \"" + back + "\".");
            }
        }
    }

    protected void fillDeck(Deck deck) {

        // start counting at one to keep track of flashcards
        for (int i = 1; i <= getDeckSize(); i++) {
            System.out.println("Card #" + i + ":");
            String front = scanner.nextLine();
            System.out.println("The definition for card #" + i + ":");
            String back = scanner.nextLine();
            addFlashcardToDeck(deck, i, front, back);
        }

    }

    private void addFlashcardToDeck(Deck deck, int number, String front, String back) {
        Flashcard flashcard = new Flashcard(number, front, back);
        deck.getDeck().add(flashcard);
    }

    protected void getNumberOfCards() {
        System.out.println("Input the number of cards:");
        setDeckSize(scanner.nextInt());
        scanner.nextLine();
    }

    // getters and setters

    protected int getDeckSize() {
        return deckSize;
    }

    private void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }
}
