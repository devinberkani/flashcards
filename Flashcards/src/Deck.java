import java.util.LinkedHashMap;

public class Deck {
    private LinkedHashMap<String, String> flashcards;
    private LinkedHashMap<Integer, Flashcard> flashcardData;
    private int deckSize;

    public Deck() {
        flashcards = new LinkedHashMap<>();
        flashcardData = new LinkedHashMap<>();
        deckSize = 0;
    }

    protected void addFlashcard(Flashcard flashcard) {
        getFlashcards().put(flashcard.getTerm(), flashcard.getDefinition());
        getFlashcardData().put(flashcard.getId(), flashcard);
    }

    protected void removeFlashcard(String term) {
        getFlashcards().remove(term);
        getFlashcardData().remove(term.hashCode());
    }

    protected Flashcard getSpecificFlashcard(int id) {
        return getFlashcardData().get(id);
    }

    // check flashcard inputs

    protected boolean termExists(String term) {
        return getFlashcards().containsKey(term);
    }

    protected boolean definitionExists(String definition) {
        return getFlashcards().containsValue(definition);
    }

    // check user answers

    protected boolean answerIsCorrect(String userAnswer, int id) {
        String correctAnswer = getFlashcardData().get(id).getDefinition();
        return userAnswer.equals(correctAnswer);
    }

    protected String correctDefinitionForCurrentCard(int id) {
        return getFlashcardData().get(id).getDefinition();
    }

    protected String correctTermForDifferentCard(String userAnswer) {
        for (var entry : getFlashcards().entrySet()) {
            if (entry.getValue().equals(userAnswer)) {
                return entry.getKey();
            }
        }

        return "Flashcards do not contain a term for this definition.";
    }

    // getters and setters


    public LinkedHashMap<String, String> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(LinkedHashMap<String, String> flashcards) {
        this.flashcards = flashcards;
    }

    public LinkedHashMap<Integer, Flashcard> getFlashcardData() {
        return flashcardData;
    }

    public void setFlashcardData(LinkedHashMap<Integer, Flashcard> flashcardData) {
        this.flashcardData = flashcardData;
    }

    protected int getDeckSize() {
        return deckSize;
    }

    protected void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }
}
