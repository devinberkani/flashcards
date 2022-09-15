public class Flashcard {
    private int id;
    private String term;
    private String definition;

    public Flashcard() {

    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected String getTerm() {
        return term;
    }

    protected void setTerm(String term) {
        this.term = term;
    }

    protected String getDefinition() {
        return definition;
    }

    protected void setDefinition(String definition) {
        this.definition = definition;
    }
}
