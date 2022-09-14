public class Menu {

    private final String[] menuOptions = {"add", "remove", "import", "export", "ask", "exit"};

    public Menu() {
    }

    protected void printMenuMessage(boolean firstMenuPrint) {
        if (firstMenuPrint) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
        } else {
            System.out.println("\nInput the action (add, remove, import, export, ask, exit):");
        }
    }
}
