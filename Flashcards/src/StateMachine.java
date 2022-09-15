import java.util.ArrayList;

public class StateMachine {
    private State state;
    private final Menu menu;
    private final Deck deck;
    private Flashcard currentCard;

    // ask variables
    private int askNumber;
    private int currentAskNumber;
    private ArrayList<String> askSet;
    private String currentAskTerm;
    public StateMachine() {
        state = State.MENU;
        menu = new Menu();
        deck = new Deck();
    }

    // MENU
    private String handleMenu(String command) {
        switch(command) {
            case "add":
            setState(State.ADD_TERM);
            return "The card:";

            case "remove":
            setState(State.REMOVE);
            return "Which card?";

            case "ask":
            setState(State.GET_ASK_NUMBER);
            return "How many times to ask?";

            case "import":
                setState(State.IMPORT);
                return "File name:";

            case "export":
                setState(State.EXPORT);
                return "File name:";

            case "exit":
                setState(State.EXIT);
                return "Bye bye!";

            default:
                return "";

        }
    }

    // ADD
    private String handleAddTerm(String userTerm) {
        if (getDeck().termExists(userTerm)) {
            setState(State.MENU);
            return String.format("The card \"%s\" already exists.", userTerm);
        }
        setState(State.ADD_DEFINITION);
        setCurrentCard(new Flashcard());
        getCurrentCard().setTerm(userTerm);
        return "The definition of the card:";
    }

    private String handleAddDefinition(String userDefinition) {
        setState(State.MENU);
        if (deck.definitionExists(userDefinition)) {
            return String.format("The definition \"%s\" already exists.", userDefinition);
        }
        getCurrentCard().setDefinition(userDefinition);
        getDeck().addFlashcardToDeck(getCurrentCard());
        return String.format("The pair (\"%s\":\"%s\") has been added.", getCurrentCard().getTerm(), getCurrentCard().getDefinition());
    }

    // REMOVE
    private String handleRemove(String cardToBeRemoved) {
        setState(State.MENU);
        if (!getDeck().termExists(cardToBeRemoved)) {
            return String.format("Can't remove \"%s\": there is no such card.", cardToBeRemoved);
        }
        getDeck().removeFlashcardFromDeck(cardToBeRemoved);
        return "The card has been removed.";
    }

    // ASK
    private String handleAskNumber(String numberOfCardsToShow) {
        int numberOfCards = Integer.parseInt(numberOfCardsToShow);
        setState(State.ASK);
        setAskSet(getDeck().getAskSet(numberOfCards));
        setAskNumber(numberOfCards);
        setCurrentAskNumber(0);
        setCurrentAskTerm(getAskSet().get(getCurrentAskNumber()));
        return (String.format("Print the definition of \"%s\":", getCurrentAskTerm()));
    }

    private String handleAsk(String userAnswer) {
        StringBuilder response = new StringBuilder();
        if (getDeck().answerIsCorrect(userAnswer, getCurrentAskTerm().hashCode())) {
            response.append("Correct!");
        } else {
            response.append(String.format("Wrong. The right answer is \"%s\"", getDeck().correctDefinitionForCurrentCard(getCurrentAskTerm().hashCode())));
            if (getDeck().definitionExists(userAnswer)) {
                response.append(String.format(", but your definition is correct for \"%s\"", getDeck().correctTermForDifferentCard(userAnswer)));
            }
            response.append(".");
        }
        if (getCurrentAskNumber() + 1 < getAskNumber()) {
            setCurrentAskNumber(getCurrentAskNumber() + 1);
            setCurrentAskTerm(getAskSet().get(getCurrentAskNumber()));
            response.append("\n");
            response.append(String.format("Print the definition of \"%s\":", getCurrentAskTerm()));
        } else {
            setState(State.MENU);
        }
        return response.toString();
    }

    // IMPORT
    private String handleImport(String file) {
        setState(State.MENU);
        return getDeck().importCardsFromFile(file);
    }

    // EXPORT
    private String handleExport(String file) {
        setState(State.MENU);
        return getDeck().exportCardsToFile(file);
    }

    // PROCESS INPUT
    protected String processInput(String input) {
        switch(getState()) {
            case MENU:
                return handleMenu(input);

            case ADD_TERM:
                return handleAddTerm(input);

            case ADD_DEFINITION:
                return handleAddDefinition(input);

            case REMOVE:
                return handleRemove(input);

            case IMPORT:
                return handleImport(input);

            case EXPORT:
                return handleExport(input);

            case GET_ASK_NUMBER:
                return handleAskNumber(input);

            case ASK:
                return handleAsk(input);

            default:
                return "";
        }
    }

    // getters and setters

    protected State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public Menu getMenu() {
        return menu;
    }

    private Deck getDeck() {
        return deck;
    }

    private Flashcard getCurrentCard() {
        return currentCard;
    }

    private void setCurrentCard(Flashcard currentCard) {
        this.currentCard = currentCard;
    }

    public int getAskNumber() {
        return askNumber;
    }

    public void setAskNumber(int askNumber) {
        this.askNumber = askNumber;
    }

    public int getCurrentAskNumber() {
        return currentAskNumber;
    }

    public void setCurrentAskNumber(int currentAskNumber) {
        this.currentAskNumber = currentAskNumber;
    }

    public ArrayList<String> getAskSet() {
        return askSet;
    }

    public void setAskSet(ArrayList<String> askSet) {
        this.askSet = askSet;
    }

    public String getCurrentAskTerm() {
        return currentAskTerm;
    }

    public void setCurrentAskTerm(String currentAskTerm) {
        this.currentAskTerm = currentAskTerm;
    }
}
