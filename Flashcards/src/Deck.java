import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Deck {
    private LinkedHashMap<String, String> flashcardTerms;
    private LinkedHashMap<String, Integer> flashcardData;
    private int deckSize;

    public Deck() {
        flashcardData = new LinkedHashMap<>();
        flashcardTerms = new LinkedHashMap<>();
        deckSize = 0;
    }

    protected void addFlashcardToDeck(Flashcard flashcard) {
        getFlashcardTerms().put(flashcard.getTerm(), flashcard.getDefinition());
//        printFlashcardMapsTest();
    }

    protected void addFlashcardToDeck(String term, String definition) {
        Flashcard flashcard = new Flashcard();
        flashcard.setTerm(term);
        flashcard.setDefinition(definition);
        addFlashcardToDeck(flashcard);
    }

    protected void printFlashcardMapsTest() {

        // flashcards-example-one.txt

        System.out.println("flashcard terms: " + getFlashcardTerms());
        System.out.println("flashcard data terms and definitions: ");
        for (var entry : getFlashcardTerms().entrySet()) {
            System.out.print(entry.getKey() + " ");
            System.out.println(entry.getValue());
        }
    }

    protected void removeFlashcardFromDeck(String term) {
        getFlashcardTerms().remove(term);
    }

    // check flashcard inputs

    protected boolean termExists(String term) {
        return getFlashcardTerms().containsKey(term);
    }

    protected boolean definitionExists(String definition) {
        return getFlashcardTerms().containsValue(definition);
    }

    // get terms for questioning
    protected ArrayList<String> getAskSet(int numberOfCardsInSet) {
        ArrayList<String> askSet = new ArrayList<>();
        int numberOfCardsNeeded = numberOfCardsInSet;
        while (numberOfCardsNeeded > 0) {
            for (var entry : getFlashcardTerms().entrySet()) {
                askSet.add(entry.getKey());
                numberOfCardsNeeded--;
                if (askSet.size() == numberOfCardsInSet) {
                    break;
                }
            }
        }
        return askSet;
    }

    // check user answers

    protected boolean answerIsCorrect(String currentAskTerm, String userAnswer) {
        return userAnswer.equals(getFlashcardTerms().get(currentAskTerm));
    }

    protected String correctDefinitionForCurrentCard(String currentAskTerm) {
        return getFlashcardTerms().get(currentAskTerm);
    }

    protected String correctTermForDifferentCard(String userAnswer) {
        String correctAnswer = "";
        for (var entry : getFlashcardTerms().entrySet()) {
            if (entry.getValue().equals(userAnswer)) {
                correctAnswer = entry.getKey();
            }
        }
        return correctAnswer;
    }

    // import cards from file
    protected String importCardsFromFile(String file) {

        int numberOfLoadedCards = 0;
        try {
            File newFile = new File("./" + file);
            Scanner myReader = new Scanner(newFile);
            while (myReader.hasNextLine()) {
                String term = myReader.nextLine();
                String definition = myReader.nextLine();
                addFlashcardToDeck(term, definition);
                numberOfLoadedCards++;
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return "File not found";
        }
        return String.format("%d cards have been loaded.", numberOfLoadedCards);

    }

    protected String exportCardsToFile(String file) {

        int numberOfSavedCards = 0;
        try {
            File newFile = new File("./" + file);
            newFile.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            for (var entry : getFlashcardTerms().entrySet()) {
                bufferedWriter.write(entry.getKey() + "\n");
                bufferedWriter.write(entry.getValue() + "\n\n");
                numberOfSavedCards++;
            }
            bufferedWriter.close();
        } catch (IOException e) {
            return "An error occured";
        }
        return String.format("%d cards have been saved.", numberOfSavedCards);

    }

    // getters and setters

    public LinkedHashMap<String, Integer> getFlashcardData() {
        return flashcardData;
    }

    public void setFlashcardData(LinkedHashMap<String, Integer> flashcardData) {
        this.flashcardData = flashcardData;
    }

    public LinkedHashMap<String, String> getFlashcardTerms() {
        return flashcardTerms;
    }

    public void setFlashcardTerms(LinkedHashMap<String, String> flashcardTerms) {
        this.flashcardTerms = flashcardTerms;
    }

    protected int getDeckSize() {
        return deckSize;
    }

    protected void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }
}
