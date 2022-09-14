import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StateMachine stateMachine = new StateMachine();
        boolean firstMenuPrint = true;
        do {
            if (stateMachine.getState().equals(State.MENU)) {
                stateMachine.getMenu().printMenuMessage(firstMenuPrint);
                firstMenuPrint = false;
            }
            String input = scanner.nextLine();
            String response = stateMachine.processInput(input);
            System.out.println(response);
        } while (!stateMachine.getState().equals(State.END));

    }
}