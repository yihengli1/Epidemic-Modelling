package ui;

import model.Person;
import model.PopulationDay;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

//Create Simulation Panel that the user can simulate the simulation
public class SimulationPanel extends JFrame implements ActionListener {
    private PopulationDay population = new PopulationDay("", 0, 0, 0, 0, 0);

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/population.json";

    private ImageIcon image;
    private JLabel logoLabel;
    private JLabel nameLabel;

    private JLabel simulationNameLabel;
    private JLabel contactRateLabel;
    private JLabel transmissionRateLabel;
    private JLabel deathRateLabel;
    private JLabel recoveryRateLabel;
    private JLabel recoverLabel;
    private JLabel peopleLabel;
    private JLabel peopleSickLabel;
    private JLabel simulationLabel;
    private JLabel loadFileLabel;
    private JLabel saveFileLabel;
    private JLabel simulationAliveLabel;
    private JLabel simulationSickLabel;
    private JLabel simulationDeadLabel;
    private JLabel simulationTotalLabel;
    private JLabel peopleAddLabel;
    private JLabel daysSimulationLabel;

    private JTextField simulationNameField;
    private JTextField contactRateField;
    private JTextField transmissionRateField;
    private JTextField deathRateField;
    private JTextField recoveryRateField;
    private JTextField peopleField;
    private JTextField peopleSickField;
    private JTextField peopleAddField;

    private JPanel informationPanel;
    private JPanel simulationPanel; //JPanel is container to hold all components
    private JPanel bluePanel;
    private JPanel orangePanel;
    private JPanel greenPanel;
    private JPanel pinkPanel;

    private JCheckBox recoverCheckBox;

    private JButton simulationButton;
    private JButton loadFileButton;
    private JButton saveFileButton;
    private JButton nextDayButton;
    private JButton endSimulationButton;

    private String simulationName;
    private double contactAmount;
    private double transmissionRate;
    private double deathRate;
    private double recoveryRate;
    private int totalPeople;
    private int startSickPeople;
    private int day;
    private Boolean loaded;
    private Boolean checkStop;


    //EFFECTS: Begins Simulation
    public SimulationPanel() {
        createSimulationLabels();
        createInformationLabels();
        createTopLabels();
        createMiddleLabels();
        createBottomLabels();

        createPanels();

        createFrame();

        addComponents();

        this.setVisible(true); //make frame visible

        revalidate(); //reset components
    }

    //EFFECTS: Creates all labels for simulation Statistics
    public void createSimulationLabels() {
        createStatisticsLabel();
    }

    //EFFECTS: Creates all labels for information Statistics
    public void createInformationLabels() {
        createInfoLabels();
        createInfoButtons();
        createInfoFields();
    }

    //EFFECTS: Creates labels for the name and the logo
    public void createTopLabels() {
        createLogoLabel();
        createNameLabel();
    }

    //EFFECTS: Creates labels for the Variables
    public void createMiddleLabels() {
        createRatesLabels();
        createCheckBoxLabel();
        createFields();
        createRecoverBox();
        createPeopleLabel();
        createSimulationName();
    }

    //EFFECTS: Creates labels for starting the simulation and load/save file
    public void createBottomLabels() {
        createBotLabels();
        createSimulationButton();
        createLoadFileButton();
        createSaveFileButton();
    }

    //EFFECTS: Creates JFrame Object
    public void createFrame() {
        this.setTitle("Epidemic Simulation"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(700, 725); //sets the x-dimension, and y-dimension of frame
        this.setLayout(null); //makes all bounds for labels null, standard layout

        //this.setVisible(true); //make frame visible

        //this.getContentPane().setBackground(new Color(0xBB3B3B)); //change color of background
    }

    //EFFECTS: Adds each individual JPanel to JFrame
    public void addComponents() {
        bluePanel.add(logoLabel);
        bluePanel.add(nameLabel);

        addRedPanel();
        addYellowPanel();
        addGreenPanel();
        addPinkPanel();
        addOrangePanel();

        this.add(informationPanel);
        this.add(simulationPanel);
        this.add(bluePanel);
        this.add(greenPanel);
        this.add(pinkPanel);
        this.add(orangePanel);
    }

    //EFFECTS: Adds all components relevant to simulation
    public void addRedPanel() {
        simulationPanel.add(daysSimulationLabel);
        simulationPanel.add(simulationAliveLabel);
        simulationPanel.add(simulationSickLabel);
        simulationPanel.add(simulationDeadLabel);
        simulationPanel.add(simulationTotalLabel);

    }

    //EFFECTS: Adds all components relevant to simulation information
    public void addYellowPanel() {
        informationPanel.add(simulationNameLabel);
        informationPanel.add(peopleAddLabel);
        informationPanel.add(nextDayButton);

        informationPanel.add(simulationNameField);
        informationPanel.add(peopleAddField);
        informationPanel.add(endSimulationButton);
    }

    //EFFECTS: Adds all components relevant to variables labels
    public void addGreenPanel() {
        greenPanel.add(contactRateLabel);
        greenPanel.add(transmissionRateLabel);
        greenPanel.add(deathRateLabel);
        greenPanel.add(recoverLabel);
        greenPanel.add(recoveryRateLabel);
        greenPanel.add(peopleLabel);
        greenPanel.add(peopleSickLabel);
    }

    //EFFECTS: Adds all components relevant to variables fields
    public void addPinkPanel() {
        pinkPanel.add(contactRateField);
        pinkPanel.add(transmissionRateField);
        pinkPanel.add(deathRateField);
        pinkPanel.add(recoverCheckBox);
        pinkPanel.add(recoveryRateField);
        pinkPanel.add(peopleField);
        pinkPanel.add(peopleSickField);
    }

    //EFFECTS: Adds all components relevant to saving/loading/starting simulation
    public void addOrangePanel() {
        orangePanel.add(simulationLabel);
        orangePanel.add(simulationButton);
        orangePanel.add(loadFileLabel);
        orangePanel.add(loadFileButton);
        orangePanel.add(saveFileLabel);
        orangePanel.add(saveFileButton);
    }

    @SuppressWarnings("methodlength")
    //EFFECTS: creates statistics labels for simulation
    public void createStatisticsLabel() {
        daysSimulationLabel = new JLabel();
        daysSimulationLabel.setText("Days In Simulation: 0");
        daysSimulationLabel.setFont(new Font("Arial Black", Font.PLAIN, 23));
        daysSimulationLabel.setHorizontalAlignment(JLabel.CENTER);
        daysSimulationLabel.setVerticalAlignment(JLabel.CENTER);

        simulationAliveLabel = new JLabel();
        simulationAliveLabel.setText("Total Current People Alive: 0");
        simulationAliveLabel.setFont(new Font("Arial Black", Font.PLAIN, 23));
        simulationAliveLabel.setHorizontalAlignment(JLabel.CENTER);
        simulationAliveLabel.setVerticalAlignment(JLabel.CENTER);

        simulationSickLabel = new JLabel();
        simulationSickLabel.setText("Total Current People Sick: 0");
        simulationSickLabel.setFont(new Font("Arial Black", Font.PLAIN, 23));
        simulationSickLabel.setHorizontalAlignment(JLabel.CENTER);
        simulationSickLabel.setVerticalAlignment(JLabel.CENTER);

        simulationDeadLabel = new JLabel();
        simulationDeadLabel.setText("Total Current People Dead: 0");
        simulationDeadLabel.setFont(new Font("Arial Black", Font.PLAIN, 23));
        simulationDeadLabel.setHorizontalAlignment(JLabel.CENTER);
        simulationDeadLabel.setVerticalAlignment(JLabel.CENTER);

        simulationTotalLabel = new JLabel();
        simulationTotalLabel.setText("Total People In Population: 0");
        simulationTotalLabel.setFont(new Font("Arial Black", Font.PLAIN, 23));
        simulationTotalLabel.setHorizontalAlignment(JLabel.CENTER);
        simulationTotalLabel.setVerticalAlignment(JLabel.CENTER);

    }

    //EFFECTS: creates information labels for simulation
    public void createInfoLabels() {
        peopleAddLabel = new JLabel();
        peopleAddLabel.setText("Add People?");
        peopleAddLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        peopleAddLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates information buttons for simulation
    public void createInfoButtons() {
        createNextDayButton();
        endSimulationButton();
    }

    //EFFECTS: creates nextDay button which proceeds simulation to the next day
    public void createNextDayButton() {
        nextDayButton = new JButton();
        nextDayButton.setPreferredSize((new Dimension(20, 40)));
        nextDayButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextDayButton) {
                    goNextDay();
                }
            }
        });
        nextDayButton.setText("Next Day"); //sets text of button
        nextDayButton.setFocusable(false); //removes border around text
        nextDayButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
        nextDayButton.setEnabled(false);
    }

    //EFFECTS: creates information buttons for ending the simulation and prompting the statistics JFrame
    public void endSimulationButton() {
        endSimulationButton = new JButton();
        endSimulationButton.setPreferredSize((new Dimension(20, 40)));
        endSimulationButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == endSimulationButton) {
                    endSimulation();
                }
            }
        });
        endSimulationButton.setText("End Simulation"); //sets text of button
        endSimulationButton.setFocusable(false); //removes border around text
        endSimulationButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
        endSimulationButton.setEnabled(false);
    }

    //EFFECTS: creates information fields for simulation
    public void createInfoFields() {
        peopleAddField = new JTextField();
        peopleAddField.setPreferredSize(new Dimension(45,20));
        peopleAddField.setEnabled(false);
        peopleAddField.setText("0");
    }

    //EFFECTS: Create label with Biohazard Logo
    public void createLogoLabel() {
        produceImg();

        //Border border = BorderFactory.createLineBorder(Color.green, 3);

        logoLabel = new JLabel(); //create new label
        logoLabel.setText("Epidemic Simulation"); //set text of label
        logoLabel.setIcon(image);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER, RIGHT of imageIcon
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, DOWN, RIGHT of imageIcon
        logoLabel.setForeground(new Color(0,0,0));
        logoLabel.setFont(new Font("Arial Black", Font.PLAIN, 17)); //set text with name, style, and font-size
        logoLabel.setIconTextGap(0); //set gap of text to image
        //logoLabel.setBackground(Color.WHITE); //set background color
        //logoLabel.setOpaque(true); //display background color
        //logoLabel.setBorder(border); //Set border of label
        logoLabel.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
        logoLabel.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon_text within label
        //logoLabel.setBounds(0,0,75,75); //set bounds of label for null
    }

    //EFFECTS: Get Image file and resize
    public void produceImg() {
        image = new ImageIcon("./src/bio.png"); //Load image
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(125, 125, java.awt.Image.SCALE_SMOOTH); //Scale smooth way
        image = new ImageIcon(newImg);
    }

    //EFFECTS: creates label with my name
    public void createNameLabel() {
        nameLabel = new JLabel();
        nameLabel.setText("Project by Yiheng Li");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates checkbox for toggling the recovery option
    public void createCheckBoxLabel() {
        recoverLabel = new JLabel();
        recoverLabel.setText("Recovery?");
        recoverLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        recoverLabel.setHorizontalAlignment(JLabel.LEFT);
    }

    //EFFECTS: creates variable label to ask for how many people in population
    public void createPeopleLabel() {
        peopleLabel = new JLabel();
        peopleLabel.setText("How Many People?");
        peopleLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        peopleLabel.setHorizontalAlignment(JLabel.LEFT);

        peopleSickLabel = new JLabel();
        peopleSickLabel.setText("People Sick?");
        peopleSickLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        peopleSickLabel.setHorizontalAlignment(JLabel.LEFT);
    }

    //EFFECTS: creates label and field of the name of the simulation
    public void createSimulationName() {
        simulationNameLabel = new JLabel();
        simulationNameLabel.setText("Simulation Name?");
        simulationNameLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        simulationNameLabel.setHorizontalAlignment(JLabel.CENTER);

        simulationNameField = new JTextField();
        simulationNameField.setPreferredSize(new Dimension(45,20));
    }

    //EFFECTS: creates variable label to ask for about the variable rates for the simulation
    public void createRatesLabels() {

        contactRateLabel = new JLabel();
        transmissionRateLabel = new JLabel();
        deathRateLabel = new JLabel();
        recoveryRateLabel = new JLabel();

        contactRateLabel.setText("Contact Rate:");
        contactRateLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        contactRateLabel.setHorizontalAlignment(JLabel.LEFT);

        transmissionRateLabel.setText("Transmission Rate:");
        transmissionRateLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        transmissionRateLabel.setHorizontalAlignment(JLabel.LEFT);

        deathRateLabel.setText("Death Rate:");
        deathRateLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        deathRateLabel.setHorizontalAlignment(JLabel.LEFT);

        recoveryRateLabel.setText("Recovery Rate:");
        recoveryRateLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        recoveryRateLabel.setHorizontalAlignment(JLabel.LEFT);
    }

    //EFFECTS: creates variable fields to ask for the rates and conditions of the simulation
    public void createFields() {
        contactRateField = new JTextField();
        transmissionRateField = new JTextField();
        deathRateField = new JTextField();
        recoveryRateField = new JTextField();
        peopleField = new JTextField();
        peopleSickField = new JTextField();

        contactRateField.setPreferredSize(new Dimension(45,20));

        transmissionRateField.setPreferredSize(new Dimension(45,20));

        deathRateField.setPreferredSize(new Dimension(45,20));

        recoveryRateField.setPreferredSize(new Dimension(45,20));

        peopleField.setPreferredSize(new Dimension(45,20));

        peopleSickField.setPreferredSize(new Dimension(45,20));


    }

    //EFFECTS: creates labels for saving/loading/starting the simulation
    public void createBotLabels() {
        simulationLabel = new JLabel();
        saveFileLabel = new JLabel();
        loadFileLabel = new JLabel();

        simulationLabel.setText("Start Simulation?");
        simulationLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        simulationLabel.setHorizontalAlignment(JLabel.CENTER);

        saveFileLabel.setText("Save Simulation?");
        saveFileLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        saveFileLabel.setHorizontalAlignment(JLabel.CENTER);
        saveFileLabel.setVisible(false);

        loadFileLabel.setText("Load Simulation?");
        loadFileLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        loadFileLabel.setHorizontalAlignment(JLabel.CENTER);

    }

    //EFFECTS: creates simulation button to start simulation
    public void createSimulationButton() {
        simulationButton = new JButton();
        simulationButton.setPreferredSize((new Dimension(20, 40)));
        simulationButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == simulationButton) {
                    startSimulation();
                }
            }
        });
        simulationButton.setText("Start Simulation"); //sets text of button
        simulationButton.setFocusable(false); //removes border around text
        simulationButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
    }

    //EFFECTS: creates loading file button to retrieve previous simulation
    public void createLoadFileButton() {
        loadFileButton = new JButton();
        loadFileButton.setPreferredSize((new Dimension(20, 40)));
        loadFileButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadFileButton) {
                    jsonWriter = new JsonWriter(JSON_STORE);
                    jsonReader = new JsonReader(JSON_STORE);

                    loadData();
                    if (loaded) {
                        startSimulationLoad();
                    }
                }
            }
        });
        loadFileButton.setText("Load File"); //sets text of button
        loadFileButton.setFocusable(false); //removes border around text
        loadFileButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
    }

    //Loads data from file
    public void loadData() {
        loaded = false;
        try {
            population = jsonReader.read();
            System.out.println("Loaded " + population.getName() + " from " + JSON_STORE);
            loaded = true;
        } catch (IOException exc) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: creates saving file button to save current simulation
    public void createSaveFileButton() {
        saveFileButton = new JButton();
        saveFileButton.setPreferredSize((new Dimension(20, 40)));
        saveFileButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveFileButton) {
                    saveData();
                }
            }
        });
        saveFileButton.setText("Save File"); //sets text of button
        saveFileButton.setFocusable(false); //removes border around text
        saveFileButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
        saveFileButton.setVisible(false);
    }

    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(population);
            jsonWriter.close();
            System.out.println("Saved " + population.getName() + "'s simulation to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        new SavedPage();

    }

    //EFFECTS: creates panels for the JFrame and all the components
    @SuppressWarnings("methodlength")
    public void createPanels() {
        informationPanel = new JPanel();
        informationPanel.setBackground(Color.lightGray);
        informationPanel.setBounds(0, 0, 500, 100);
        informationPanel.setPreferredSize(new Dimension(500, 100));
        informationPanel.setLayout(new GridLayout(2,3));

        simulationPanel = new JPanel();
        simulationPanel.setBackground(new Color(0,128,1)); //set background colour of panel to red
        simulationPanel.setBounds(0, 100, 500, 600); //set position and bounds of panel
        simulationPanel.setPreferredSize(new Dimension(500, 600));
        simulationPanel.setLayout(new GridLayout(5,1)); //set Layout of components, new BorderLayout()
        // defaults to border, null = label layout, can change with setBounds on label

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.lightGray);
        bluePanel.setBounds(500, 0, 200, 200);
        bluePanel.setPreferredSize(new Dimension(200, 200));
        bluePanel.setLayout(new FlowLayout());

        greenPanel = new JPanel();
        greenPanel.setBackground(Color.lightGray);
        greenPanel.setBounds(500, 200, 150, 250);
        greenPanel.setPreferredSize(new Dimension(150, 250));
        greenPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.lightGray);
        pinkPanel.setBounds(650, 200, 50, 250);
        pinkPanel.setPreferredSize(new Dimension(50, 250));
        pinkPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 14));

        orangePanel = new JPanel();
        orangePanel.setBackground(Color.lightGray);
        orangePanel.setBounds(500, 450, 200, 250);
        orangePanel.setPreferredSize(new Dimension(200, 250));
        orangePanel.setLayout(new GridLayout(6,1, 5, -10));
    }

    //EFFECTS: creates recovery box to toggle recovery rate
    public void createRecoverBox() {
        recoverCheckBox = new JCheckBox();
        recoverCheckBox.setSelected(true);
        recoverCheckBox.addItemListener(e -> {
            // Check the state of the checkbox
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Checkbox is selected, show the label
                recoveryRateLabel.setVisible(true);
                recoveryRateField.setVisible(true);
            } else {
                // Checkbox is deselected, hide the label
                recoveryRateLabel.setVisible(false);
                recoveryRateField.setVisible(false);
            }
        });
    }

    //EFFECTS: starts the simulation
    public void startSimulation() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        checkStop = false;
        checkConditions();
        if (!checkStop) {
            disableVariables();
            enableFields();

            initialize();

            setSimText();
        }
    }

    //EFFECTS: checks the conditions of the simulation, if there is an error, throw a new Error Panel
    public void checkConditions() {
        try {
            simulationName = simulationNameField.getText();
            contactAmount = Integer.parseInt(contactRateField.getText());
            transmissionRate = Double.parseDouble(transmissionRateField.getText());
            deathRate = Double.parseDouble(deathRateField.getText());
            if (recoverCheckBox.isSelected()) {
                recoveryRate = Double.parseDouble(recoveryRateField.getText());
            } else {
                recoveryRate = 0;
            }
            totalPeople = Integer.parseInt(peopleField.getText());
            startSickPeople = Integer.parseInt(peopleSickField.getText());

            if (contactAmount < 0 || transmissionRate > 1 || transmissionRate < 0
                    || deathRate > 1 || deathRate < 0 || recoveryRate > 1 || recoveryRate < 0
                    || totalPeople < 1 || startSickPeople > totalPeople || startSickPeople < 1) {
                new ErrorPanel();
                checkStop = true;
            }
        } catch (Exception e) {
            new ErrorPanel();
            checkStop = true;
        }
    }

    //EFFECTS: Disables all variables to prevent tampering of simulation
    public void disableVariables() {
        simulationNameField.setEnabled(false);
        simulationButton.setEnabled(false);
        contactRateField.setEnabled(false);
        transmissionRateField.setEnabled(false);
        deathRateField.setEnabled(false);
        recoverCheckBox.setEnabled(false);
        recoveryRateField.setEnabled(false);
        peopleField.setEnabled(false);
        peopleSickField.setEnabled(false);
        loadFileButton.setEnabled(false);
    }

    //EFFECTS: Enables all disabled fields at the start of the simulation
    public void enableFields() {
        saveFileButton.setVisible(true);
        saveFileLabel.setVisible(true);
        peopleAddField.setEnabled(true);
        endSimulationButton.setEnabled(true);
        nextDayButton.setEnabled(true);
    }

    //EFFECTS: Initializes the PopulationDay
    public void initialize() {
        day = 1;
        population = new PopulationDay(simulationName, contactAmount, transmissionRate, deathRate, recoveryRate, day);

        for (int i = 0; i < startSickPeople; i++) {
            Person temp = new Person("Alive", 20);
            temp.setState("Sick");
            population.addPeople(temp);
        }

        for (int i = 0; i < (totalPeople - startSickPeople); i++) {
            Person temp = new Person("Alive", 20);
            population.addPeople(temp);
        }


    }

    //EFFECTS: Runs the simulation
    public void runSimulation() {

        population.simulateDeadPeople();
        population.simulateRecovery();
        population.simulateContactedAmount();
        population.simulateSickPeople();
    }

    //EFFECTS: Sets the simulation texts to current day simulation data
    public void setSimText() {
        daysSimulationLabel.setText("Days In Simulation: " + population.getDay());
        simulationAliveLabel.setText("Total Current People Alive: " + population.returnTotalAlivePopulation());
        simulationSickLabel.setText("Total Current People Sick: " + population.returnTotalSickPopulation());
        simulationDeadLabel.setText("Total Current People Dead: " + population.returnTotalDeadPopulation());
        simulationTotalLabel.setText("Total People in Population: " + population.getPeopleSize());
    }

    //EFFECTS: Makes simulation render the next day
    public void goNextDay() {
        int temp = 0;
        try {
            temp = Integer.parseInt(peopleAddField.getText());
            for (int i = 0; i < temp; i++) {
                Person newPeople = new Person("Alive", 20);
                population.addPeople(newPeople);
            }

            runSimulation();
            population.increaseDay();
            setSimText();
        } catch (Exception e) {
            new ErrorPanel();
        }
    }

    //EFFECTS: Ends the simulation
    public void endSimulation() {
        this.dispose();
        new StatisticsPanel(population);
    }

    //EFFECTS: starts the simulation from a loaded file
    public void startSimulationLoad() {
        disableVariables();
        enableFields();
        setSimText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
