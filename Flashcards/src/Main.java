import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StateMachine stateMachine = new StateMachine();
        do {
            String input = scanner.nextLine();
            String response = stateMachine.processInput(input);
            System.out.println(response);
        } while (!stateMachine.getState().equals(State.END));

    }
}