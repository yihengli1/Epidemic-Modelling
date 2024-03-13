package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;


public class SimulationPanel extends JFrame implements ActionListener {
    ImageIcon image;
    JLabel logoLabel;
    JLabel nameLabel;

    JLabel contactRateLabel;
    JLabel transmissionRateLabel;
    JLabel deathRateLabel;
    JLabel recoveryRateLabel;
    JLabel recoverLabel;
    JLabel peopleLabel;
    JLabel peopleSickLabel;
    JLabel simulationLabel;
    JLabel loadFileLabel;
    JLabel saveFileLabel;
    JLabel simulationAliveLabel;
    JLabel simulationSickLabel;
    JLabel simulationDeadLabel;
    JLabel simulationTotalLabel;

    JTextField contactRateField;
    JTextField transmissionRateField;
    JTextField deathRateField;
    JTextField recoveryRateField;
    JTextField peopleField;
    JTextField peopleSickField;



    JPanel redPanel; //JPanel is container to hold all components
    JPanel bluePanel;
    JPanel orangePanel;
    JPanel greenPanel;
    JPanel pinkPanel;

    JCheckBox recoverCheckBox;

    JButton simulationButton;
    JButton loadFileButton;
    JButton saveFileButton;

    @SuppressWarnings("methodlength")
    public SimulationPanel() {
        createSimulationLabels();

        createTopLabels();
        createMiddleLabels();
        createBottomLabels();

        createPanels();

        createFrame();

        addComponents();

        this.setVisible(true); //make frame visible

        revalidate(); //reset components
    }

    public void createSimulationLabels() {
        createStatisticsLabel();
    }

    public void createTopLabels() {
        createLogoLabel();
        createNameLabel();
    }

    public void createMiddleLabels() {
        createRatesLabels();
        createCheckBoxLabel();
        createFields();
        createRecoverBox();
        createPeopleLabel();
    }

    public void createBottomLabels() {
        createBotLabels();
        createSimulationButton();
        createLoadFileButton();
        createSaveFileButton();
    }

    public void createFrame() {
        this.setTitle("Epidemic Simulation"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(700, 725); //sets the x-dimension, and y-dimension of frame
        this.setLayout(null); //makes all bounds for labels null, standard layout

        //this.setVisible(true); //make frame visible

        //this.getContentPane().setBackground(new Color(0xBB3B3B)); //change color of background
    }

    public void addComponents() {
        bluePanel.add(logoLabel);
        bluePanel.add(nameLabel);

        addRedPanel();

        addGreenPanel();

        addPinkPanel();

        addOrangePanel();

        this.add(redPanel);
        this.add(bluePanel);
        this.add(greenPanel);
        this.add(pinkPanel);
        this.add(orangePanel);
    }

    public void addRedPanel() {

    }

    public void addGreenPanel() {
        greenPanel.add(contactRateLabel);
        greenPanel.add(transmissionRateLabel);
        greenPanel.add(deathRateLabel);
        greenPanel.add(recoverLabel);
        greenPanel.add(recoveryRateLabel);
        greenPanel.add(peopleLabel);
        greenPanel.add(peopleSickLabel);
    }

    public void addPinkPanel() {
        pinkPanel.add(contactRateField);
        pinkPanel.add(transmissionRateField);
        pinkPanel.add(deathRateField);
        pinkPanel.add(recoverCheckBox);
        pinkPanel.add(recoveryRateField);
        pinkPanel.add(peopleField);
        pinkPanel.add(peopleSickField);
    }

    public void addOrangePanel() {
        orangePanel.add(loadFileLabel);
        orangePanel.add(loadFileButton);
        orangePanel.add(saveFileLabel);
        orangePanel.add(saveFileButton);
        orangePanel.add(simulationLabel);
        orangePanel.add(simulationButton);
    }

    public void createStatisticsLabel() {

    }

    //EFFECTS: Create label with Biohazard Logo
    public void createLogoLabel() {
        produceImg();

        Border border = BorderFactory.createLineBorder(Color.green, 3);

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

    public void createNameLabel() {
        nameLabel = new JLabel();
        nameLabel.setText("Project by Yiheng Li");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void createCheckBoxLabel() {
        recoverLabel = new JLabel();
        recoverLabel.setText("Recovery?");
        recoverLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        recoverLabel.setHorizontalAlignment(JLabel.LEFT);
    }

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

        loadFileLabel.setText("Load Simulation?");
        loadFileLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
        loadFileLabel.setHorizontalAlignment(JLabel.CENTER);

    }

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

    public void createLoadFileButton() {
        loadFileButton = new JButton();
        loadFileButton.setPreferredSize((new Dimension(20, 40)));
        loadFileButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadFileButton) {
                    startSimulation();
                }
            }
        });
        loadFileButton.setText("Load File"); //sets text of button
        loadFileButton.setFocusable(false); //removes border around text
        loadFileButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
    }

    public void createSaveFileButton() {
        saveFileButton = new JButton();
        saveFileButton.setPreferredSize((new Dimension(20, 40)));
        saveFileButton.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveFileButton) {
                    startSimulation();
                }
            }
        });
        saveFileButton.setText("Save File"); //sets text of button
        saveFileButton.setFocusable(false); //removes border around text
        saveFileButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
    }

    @SuppressWarnings("methodlength")
    public void createPanels() {
        redPanel = new JPanel();
        redPanel.setBackground(Color.RED); //set background colour of panel to red
        redPanel.setBounds(0, 0, 500, 700); //set position and bounds of panel
        redPanel.setLayout(new GridLayout(2,1)); //set Layout of components, new BorderLayout() defaults to border
        //null = label layout, can change with setBounds on label

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(500, 0, 200, 200);
        bluePanel.setPreferredSize(new Dimension(200, 200));
        bluePanel.setLayout(new FlowLayout());

        greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(500, 200, 150, 250);
        greenPanel.setPreferredSize(new Dimension(150, 250));
        greenPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);
        pinkPanel.setBounds(650, 200, 50, 250);
        pinkPanel.setPreferredSize(new Dimension(50, 250));
        pinkPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 14));

        orangePanel = new JPanel();
        orangePanel.setBackground(Color.ORANGE);
        orangePanel.setBounds(500, 450, 200, 250);
        orangePanel.setPreferredSize(new Dimension(200, 250));
        orangePanel.setLayout(new GridLayout(6,1, 5, -10));


    }

    public void createRecoverBox() {
        recoverCheckBox = new JCheckBox();
        recoverCheckBox.setSelected(true);
        /*
        recoverCheckBox.setText("Recovery?");
        recoverCheckBox.setFocusable(false);
        recoverCheckBox.setFont(new Font("Zapfino", Font.PLAIN, 7));
         */
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

    public void startSimulation() {
        //TODO:
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
