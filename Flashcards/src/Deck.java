import java.util.LinkedHashMap;

public class Deck {
    private LinkedHashMap<Integer, Flashcard> flashcards;
    private LinkedHashMap<String, Integer> terms;
    private LinkedHashMap<String, Integer> definitions;
    private int deckSize;
    private int id;

    public Deck() {
        flashcards = new LinkedHashMap<>();
        terms = new LinkedHashMap<>();
        definitions = new LinkedHashMap<>();
        deckSize = 0;
        id = 1;
    }

    protected Flashcard getFlashcard(int id) {
        return getFlashcards().get(id);
    }

    protected void addFlashcard(Flashcard flashcard) {
        getFlashcards().put(id, flashcard);
        getTerms().put(flashcard.getTerm(), id);
        getDefinitions().put(flashcard.getDefinition(), id);
        setId(getId() + 1);
    }

    // check flashcard inputs

    protected boolean termExists(String input) {
        return terms.containsKey(input);
    }

    protected boolean definitionExists(String definition) {
        return definitions.containsKey(definition);
    }

    // check user answers

    protected boolean answerIsCorrect(String userAnswer, int id) {
        String correctAnswer = getFlashcards().get(id).getDefinition();
        return userAnswer.equals(correctAnswer);
    }

    protected String correctTerm(String userAnswer, int id) {
        return getFlashcards().get(id).getTerm();
    }

    // getters and setters


    protected LinkedHashMap<Integer, Flashcard> getFlashcards() {
        return flashcards;
    }

    private void setFlashcards(LinkedHashMap<Integer, Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    protected LinkedHashMap<String, Integer> getTerms() {
        return terms;
    }

    private void setTerms(LinkedHashMap<String, Integer> terms) {
        this.terms = terms;
    }

    protected LinkedHashMap<String, Integer> getDefinitions() {
        return definitions;
    }

    private void setDefinitions(LinkedHashMap<String, Integer> definitions) {
        this.definitions = definitions;
    }

    protected int getDeckSize() {
        return deckSize;
    }

    protected void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
