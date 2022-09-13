public class StateMachine {
    private State state;

    private Deck deck;
    private Flashcard currentCard;
    private int currentCardNumber;

    private void greet() {
        System.out.println("Input the number of cards:");
    }

    public StateMachine() {
        state = State.USER_PROMPT;
        deck = new Deck();
        currentCard = new Flashcard();
        currentCardNumber = 1;
        greet();
    }

    private String handleUserPrompt(String numberOfCards) {
        deck.setDeckSize(Integer.parseInt(numberOfCards));
        setState(State.ADD_TERM);
        return String.format("Card #%d:", getCurrentCardNumber());
    }

    private String handleAddTerm(String userTerm) {
        if (getDeck().termExists(userTerm)) {
            return String.format("The term \"%s\" already exists. Try again:", userTerm);
        }
        getCurrentCard().setTerm(userTerm);
        setState(State.ADD_DEFINITION);
        return String.format("The definition for card #%d:", getCurrentCardNumber());
    }

    private String handleAddDefinition(String userDefinition) {
        if (deck.definitionExists(userDefinition)) {
            return String.format("The definition \"%s\" already exists. Try again:", userDefinition);
        }
        getCurrentCard().setDefinition(userDefinition);
        deck.addFlashcard(currentCard);
        setCurrentCardNumber(getCurrentCardNumber() + 1);
        if (getCurrentCardNumber() <= deck.getDeckSize()) {
            setState(State.ADD_TERM);
            setCurrentCard(new Flashcard());
            return String.format("Card #%d:", getCurrentCardNumber());
        }
        setCurrentCardNumber(1);
        setState(State.ANSWER);
        return (String.format("Print the definition of \"%s\":", getDeck().getFlashcard(getCurrentCardNumber()).getTerm()));
    }

    private String handleAnswer(String userAnswer) {
        StringBuilder response = new StringBuilder();
        if (getCurrentCardNumber() <= deck.getDeckSize()) {
            if (getDeck().answerIsCorrect(userAnswer, getCurrentCardNumber())) {
                response.append("Correct!");
            } else {
                response.append(String.format("Wrong. The right answer is \"%s\"", getDeck().getFlashcards().get(getCurrentCardNumber()).getDefinition()));
                if (getDeck().definitionExists(userAnswer)) {
                    response.append(String.format(", but your definition is correct for \"%s\"", getDeck().correctTerm(userAnswer, getCurrentCardNumber())));
                }
                response.append(".");
            }
            setCurrentCardNumber(getCurrentCardNumber() + 1);
            if (getCurrentCardNumber() <= deck.getDeckSize()) {
                response.append("\n");
                response.append(String.format("Print the definition of \"%s\":", getDeck().getFlashcard(getCurrentCardNumber()).getTerm()));
            } else {
                setState(State.END);
            }
            return response.toString();
        }
        setState(State.END);
        return "";
    }

    protected String processInput(String input) {
        switch(getState()) {
            case USER_PROMPT:
                return handleUserPrompt(input);

            case ADD_TERM:
                return handleAddTerm(input);

            case ADD_DEFINITION:
                return handleAddDefinition(input);

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

    private Deck getDeck() {
        return deck;
    }

    private void setDeck(Deck deck) {
        this.deck = deck;
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
