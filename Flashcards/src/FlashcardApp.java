import java.util.Scanner;

public class FlashcardApp {

    public void run () {
        Scanner scanner = new Scanner(System.in);
        StateMachine stateMachine = new StateMachine();
        boolean firstMenu = true;
        do {
            if (stateMachine.getState().equals(State.MENU)) {
                if (!firstMenu) {
                    System.out.println();
                }
                firstMenu = false;
                System.out.println(printMenu());
            }
            String input = scanner.nextLine();
            String response = stateMachine.processInput(input);
            System.out.println(response);
        } while (!stateMachine.getState().equals(State.EXIT));
    }

    private String printMenu() {
        return "Input the action (add, remove, import, export, ask, exit):";
    }
}
