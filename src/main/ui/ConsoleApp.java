package ui;

public class ConsoleApp {
    private double transmissionRate;
    private double deathRate;
    private double recoveryRate;
    private double contactRate;

    public ConsoleApp() {
        runConsole();
    }

    public void runConsole() {
        System.out.println("Welcome Message");
        askQuestions();



    }

    public void askQuestions() {
        System.out.println("What will the transmission rate for this simulation be?");
        ///
        System.out.println("What will the death rate for this simulation be?");
        ///
        System.out.println("What will the recovery rate for this simulation be?");
        ///
        System.out.println("What will the contact rate for this simulation be?");
        ///
        System.out.println("How many people do you want to start the simulation with?");
        ///
    }

}
