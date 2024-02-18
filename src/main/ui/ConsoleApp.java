package ui;

import model.Person;
import model.PopulationDay;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ConsoleApp {
    private int numPeople;
    private int startingSick;
    private double transmissionRate;
    private double deathRate;
    private double recoveryRate;
    private double contactRate;
    private Scanner input;
    private PopulationDay population;
    private int day;
    private boolean checkStop;

    public ConsoleApp() {
        runConsole();
    }

    public void runConsole() {
        System.out.println("Welcome Message");
        input = new Scanner(System.in);

        askQuestions();

        initialize();

        while (checkStop == true) {
            runSimulation();
        }

    }

    public void initialize() {
        population = new PopulationDay(contactRate, transmissionRate, deathRate, 0);
        day = 1;
        checkStop = true;

        for (int i = 0; i < startingSick; i++) {
            Person temp = new Person(20);
            temp.setState("Sick");
            population.addPeople(temp);
        }

        for (int i = 0; i < (numPeople - startingSick); i++) {
            Person temp = new Person(20);
            population.addPeople(temp);
        }
    }


    public void runSimulation() {
        System.out.println("Day" + day + ": ");

        addPeople();

        System.out.println("Simulation in progress...");

        population.simulateDeadPeople();
        population.simulateContactedAmount();
        population.simulateSickPeople();

        statusUpdate();

        String temp = "";

        do {
            System.out.println("Continue to next day? (y/n)");
            temp = input.next();

            if (!temp.equals("y") && !temp.equals("n")) {
                System.out.println("Invalid input. Please enter y or n.");
            }
        } while (!temp.equals("y") && !temp.equals("n"));

        if (temp.equals("n")) {
            checkStop = false;
        }

        day++;

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

        do {
            System.out.println("How many people do you want to start the simulation sick?"
                    + " (between 1 and number of people you started with)");
            startingSick = input.nextInt();

            if (startingSick < 1 || startingSick > numPeople) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        } while (startingSick < 1 || startingSick > numPeople);

        System.out.println("The people in your population will be: " + numPeople);
        ///
        System.out.println("Transmission Rate: " + transmissionRate);
        System.out.println("Death Rate: " + deathRate);
        System.out.println("Contact Rate: " + contactRate);
        System.out.println("People in Population: " + numPeople);
        System.out.println("People that will start sick: " + startingSick);
        System.out.println("---------------------------");
    }

    public void statusUpdate() {
        System.out.println("Current People Alive: " + population.returnTotalAlivePopulation());
        System.out.println("Current People Sick: " + population.returnTotalSickPopulation());
        System.out.println("Current People Dead: "  + population.returnTotalDeadPopulation());
        System.out.println("Total People in population: " + (population.getPeopleSize() + population.getDeathAmount()));
        System.out.println("---------------------------");
    }

    public void addPeople() {
        String temp = "";

        do {
            System.out.println("Do you want to add people to simulation? (y/n)");
            temp = input.next();

            if (!temp.equals("y") && !temp.equals("n")) {
                System.out.println("Invalid input. Please enter y or n.");
            }
        } while (!temp.equals("y") && !temp.equals("n"));

        int temp1 = 0;

        if (temp.equals("y")) {
            do {
                System.out.println("How many people do you to add? (positive integer)");
                temp1 = input.nextInt();

                if (temp1 < 0) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } while (temp1 < 0);

            for (int i = 0; i < temp1; i++) {
                Person newPeople = new Person(20);
                population.addPeople(newPeople);
            }

            System.out.println("Successfully added " + temp1 + " people!");
        }
    }

}
