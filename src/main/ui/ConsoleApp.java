package ui;

import model.Person;
import model.PopulationDay;

import java.util.Scanner;

public class ConsoleApp {
    private int numPeople;
    private double transmissionRate;
    private double deathRate;
    private double recoveryRate;
    private double contactRate;
    private Scanner input;
    private PopulationDay population;

    public ConsoleApp() {
        runConsole();
    }

    public void runConsole() {
        System.out.println("Welcome Message");
        input = new Scanner(System.in);

        askQuestions();

        initialize();



    }

    public void initialize() {
        population = new PopulationDay(contactRate, transmissionRate, deathRate, 0);

        for (int i = 0; i < numPeople; i++) {
            Person temp = new Person(20);

        }
    }

    @SuppressWarnings("methodlength")
    public void askQuestions() {
        do {
            System.out.println("What will the transmission rate for this simulation be? (between 0 - 1) ");
            transmissionRate = input.nextDouble();

            if (transmissionRate <= 0 || transmissionRate >= 1) {
                System.out.println("Invalid input. Please enter a number between 0 and 1.");
            }
        } while (transmissionRate < 0 || transmissionRate > 1);
        System.out.println("Your Transmission Rate will be: " + transmissionRate);

        do {
            System.out.println("What will the death rate for this simulation be? (between 0 - 1)");
            deathRate = input.nextDouble();

            if (deathRate < 0 || deathRate > 1) {
                System.out.println("Invalid input. Please enter a number between 0 and 1.");
            }
        } while (deathRate < 0 || deathRate > 1);
        System.out.println("Your Death Rate will be: " + deathRate);

        do {
            System.out.println("What will the contact rate for this simulation be? (any positive number)");
            contactRate = input.nextDouble();

            if (contactRate < 0) {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        } while (contactRate < 0);
        System.out.println("Your Contact Rate will be: " + contactRate);

        do {
            System.out.println("How many people do you want to start the simulation with? (positive integer)");
            numPeople = input.nextInt();

            if (numPeople < 0) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        } while (numPeople < 0);
        System.out.println("The people in your population will be: " + numPeople);
        ///
        System.out.println("Transmission Rate: " + transmissionRate);
        System.out.println("Death Rate: " + deathRate);
        System.out.println("Contact Rate: " + contactRate);
        System.out.println("People in Population: " + numPeople);

    }

}
