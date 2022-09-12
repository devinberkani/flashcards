import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private int deckSize;
    public Menu() {

    }

    protected void goThroughDeck(Deck deck) {

        String incorrectMessage = "Wrong. The right answer is \"";

        for (var flashcard : deck.getHashMapDeck().entrySet()) {
            String front = flashcard.getKey();
            String back = deck.getHashMapDeck().get(front);
            System.out.println("Print the definition of \"" + front + "\":");
            String answer = scanner.nextLine();
            if (answer.equals(back)) {
                System.out.println("Correct!");
            } else if (deck.getHashMapDeck().containsValue(answer)) {
                // if the user entered the answer for a different card, show them the one they had the right answer for
                String correctTerm = "";
                for (var flashcard2 : deck.getHashMapDeck().entrySet()) {
                    if (flashcard2.getValue().equals(answer)) {
                        correctTerm = flashcard2.getKey();
                    }
                }
                System.out.println(incorrectMessage + back + "\", but your definition is correct for \"" + correctTerm + "\".");
            } else {
                System.out.println(incorrectMessage + back + "\".");
            }
        }

        // old logic for having an arraylist of flashcard objects
//        for (int i = 0; i < getDeckSize(); i++) {
//            String front = deck.getDeck().get(i).getFront();
//            String back = deck.getDeck().get(i).getBack();
//            System.out.println("Print the definition of \"" + front + "\":");
//            String answer = scanner.nextLine();
//            if (answer.equals(back)) {
//                System.out.println("Correct!");
//            } else {
//                System.out.println("Wrong. The right answer is \"" + back + "\".");
//            }
//        }
    }

    protected void fillDeck(Deck deck) {

        // start counting at one to keep track of flashcards
        for (int i = 1; i <= getDeckSize(); i++) {
            System.out.println("Card #" + i + ":");
            String front = scanner.nextLine();

            // check if front is already in deck
            while (deck.getHashMapDeck().containsKey(front)) {
                System.out.println("The term \"" + front + "\" already exists. Try again:");
                front = scanner.nextLine();
            }

            System.out.println("The definition for card #" + i + ":");
            String back = scanner.nextLine();

            // check if back is already in deck
            while (deck.getHashMapDeck().containsValue(back)) {
                System.out.println("The definition \"" + back + "\" already exists. Try again:");
                back = scanner.nextLine();
            }

            addFlashcardToHashMapDeck(deck, front, back);
//            addFlashcardToDeck(deck, i, front, back); // old logic for having an arraylist of flashcards
        }

    }

    private void addFlashcardToHashMapDeck(Deck deck, String front, String back) {
        deck.getHashMapDeck().put(front, back);
    }

    // old logic for having an arraylist of flashcard objects
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
