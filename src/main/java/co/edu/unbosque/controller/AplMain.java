package co.edu.unbosque.controller;

/**
 * The {@code AplMain} class serves as the entry point for the application.
 * <p>
 * It initializes the {@link Controller} class and starts the program execution
 * by calling its {@code run()} method.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public class AplMain {

    /**
     * Main method that launches the application.
     * <p>
     * It creates an instance of {@link Controller} and calls its
     * {@code run()} method to start the program's logic.
     * </p>
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.run();
    }
}
