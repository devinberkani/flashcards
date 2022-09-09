public class Flashcard {
    private int number;
    private String front;
    private String back;

    public Flashcard(int number, String front, String back) {
        this.number = number;
        this.front = front;
        this.back = back;
    }

    // getters and setters

    protected int getNumber() {
        return number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    protected String getFront() {
        return front;
    }

    protected void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
