public class StateMachine {
    private State state = State.MENU;
    private final Menu menu = new Menu();
    private final Deck deck = new Deck();
    private Flashcard currentCard;
    private int currentCardNumber = 0;

    public StateMachine() {
    }

    private String handleMenu(String command) {
        switch(command) {
            case "add":
            setState(State.ADD_TERM);
            return "The card:";

            case "remove":
            setState(State.REMOVE);
            return "Which card?";

            default:
                return "";

        }
    }

    // ADD

    private String handleAddTerm(String userTerm) {
        if (getDeck().termExists(userTerm)) {
            setState(State.MENU);
            return String.format("The term \"%s\" already exists.", userTerm);
        }
        setCurrentCardNumber(getCurrentCardNumber() + 1);
        setCurrentCard(new Flashcard());
        getCurrentCard().setId(userTerm.hashCode()); // set generated hash code as flashcard id
        getCurrentCard().setTerm(userTerm);
        setState(State.ADD_DEFINITION);
        return String.format("The definition for card #%d:", getCurrentCardNumber());
    }

    private String handleAddDefinition(String userDefinition) {
        setState(State.MENU);
        if (deck.definitionExists(userDefinition)) {
            return String.format("The definition \"%s\" already exists.", userDefinition);
        }
        getCurrentCard().setDefinition(userDefinition);
        getDeck().addFlashcard(getCurrentCard());
        return String.format("The pair (\"%s\":\"%s\") has been added.", getCurrentCard().getTerm(), getCurrentCard().getDefinition());
    }

    private String handleRemove(String cardToBeRemoved) {
        setState(State.MENU);
        if (!getDeck().termExists(cardToBeRemoved)) {
            return String.format("Can't remove \"%s\": there is no such card.", cardToBeRemoved);
        }
        getDeck().removeFlashcard(cardToBeRemoved);
        return "The card has been removed.";
    }

    private String handleAnswer(String userAnswer) {
        StringBuilder response = new StringBuilder();
        if (getDeck().answerIsCorrect(userAnswer, getCurrentCardNumber())) {
            response.append("Correct!");
        } else {
            response.append(String.format("Wrong. The right answer is \"%s\"", getDeck().correctDefinitionForCurrentCard(getCurrentCardNumber())));
            if (getDeck().definitionExists(userAnswer)) {
                response.append(String.format(", but your definition is correct for \"%s\"", getDeck().correctTermForDifferentCard(userAnswer)));
            }
            response.append(".");
        }
        if (getCurrentCardNumber() + 1 <= deck.getDeckSize()) {
            setCurrentCardNumber(getCurrentCardNumber() + 1);
            response.append("\n");
            response.append(String.format("Print the definition of \"%s\":", getDeck().getSpecificFlashcard(getCurrentCardNumber()).getTerm()));
        } else {
            setState(State.END);
        }
        return response.toString();
    }

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

            case ANSWER:
                return handleAnswer(input);

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

    private int getCurrentCardNumber() {
        return currentCardNumber;
    }

    private void setCurrentCardNumber(int currentCardNumber) {
        this.currentCardNumber = currentCardNumber;
    }
}
