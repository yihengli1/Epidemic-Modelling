package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents an error message when a user enters an invalid argument
public class ErrorPanel extends JFrame {
    JLabel label;
    JLabel paragraph1;
    JLabel paragraph2;
    JLabel paragraph3;
    JLabel paragraph4;
    JLabel paragraph5;
    JPanel splitPanel;
    JPanel splitPanel1;

    JButton goBackButton;

    //EFFECTS: Renders the entire Error Panel
    public ErrorPanel() {
        createTitle();
        paragraph1();
        paragraph2();
        paragraph3();
        paragraph4();
        paragraph5();
        //createButton();

        createSplitPanel();

        createFrame();


        this.add(splitPanel);
        this.add(paragraph1);
        this.add(paragraph2);
        this.add(paragraph3);
        this.add(paragraph4);
        this.add(paragraph5);


        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Renders the 5th paragraph
    private void paragraph5() {
        paragraph5 = new JLabel();
        paragraph5.setText("- The add people field must be a positive integer!");

        paragraph5.setFont(new Font("Arial Black", Font.PLAIN, 12));
        paragraph5.setHorizontalAlignment(JLabel.LEFT);
    }

    //MODIFIES: this
    //EFFECTS: Renders the 4th paragraph
    private void paragraph4() {
        paragraph4 = new JLabel();
        paragraph4.setText("- Starting number of sick people <= Total number of people");

        paragraph4.setFont(new Font("Arial Black", Font.PLAIN, 12));
        paragraph4.setHorizontalAlignment(JLabel.LEFT);
    }

    //MODIFIES: this
    //EFFECTS: Renders the 3rd paragraph
    private void paragraph3() {
        paragraph3 = new JLabel();
        paragraph3.setText("- People who are starting with sick and alive should be >= 1");

        paragraph3.setFont(new Font("Arial Black", Font.PLAIN, 12));
        paragraph3.setHorizontalAlignment(JLabel.LEFT);
    }

    //MODIFIES: this
    //EFFECTS: Renders the 2nd paragraph
    private void paragraph2() {
        paragraph2 = new JLabel();
        paragraph2.setText("- Contact Amount is a positive Integer");

        paragraph2.setFont(new Font("Arial Black", Font.PLAIN, 12));
        paragraph2.setHorizontalAlignment(JLabel.LEFT);
    }

    //MODIFIES: this
    //EFFECTS: Renders the 1st paragraph
    private void paragraph1() {
        paragraph1 = new JLabel();
        paragraph1.setText("- Transmission Rate, Death Rate, and Recovery Rate are all between 0 and 1.");

        paragraph1.setFont(new Font("Arial Black", Font.PLAIN, 12));
        paragraph1.setHorizontalAlignment(JLabel.LEFT);
    }

    //MODIFIES: this
    //EFFECTS: Creates frame of error panel
    private void createFrame() {
        this.setTitle("Error Page!"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(600, 600); //sets the x-dimension, and y-dimension of frame
        this.setLayout(new GridLayout(6,1)); //makes all bounds for labels null, standard layout
    }

    //MODIFIES: this
    //EFFECTS: Creates split frame of error panel at the top to separate button from title
    private void createSplitPanel() {
        splitPanel1 = new JPanel();
        splitPanel1.setBounds(0, 0, 300, 100);
        splitPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //splitPanel1.add(goBackButton);

        splitPanel = new JPanel();
        splitPanel.setBounds(0, 0, 600, 100);
        splitPanel.setLayout(new GridLayout(1, 2));
        splitPanel.add(label);
        splitPanel.add(splitPanel1);

    }

//    //EFFECTS: Creates button to return back to main menu
//    private void createButton() {
//        goBackButton = new JButton();
//        goBackButton.setPreferredSize((new Dimension(70, 20)));
//        goBackButton.addActionListener(new ActionListener() { //sets the action of pressing the button
//            @Override                                   //can use lambda function of (e -> sout"Who") too
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == goBackButton) {
//                    helpDispose();
//                    new SimulationPanel();
//                }
//            }
//        });
//        goBackButton.setText("Go Back"); //sets text of button
//        goBackButton.setFocusable(false); //removes border around text
//        goBackButton.setFont(new Font("Ariel Black", Font.PLAIN, 11));
//    }

    //EFFECTS: Disposes of the current JFrame because can't use this inside of a nested method call
    public void helpDispose() {
        this.dispose();
    }

    //EFFECTS: Creates a title message for the Error Panel
    private void createTitle() {
        label = new JLabel();
        label.setText("Error! Make sure you follow the instructions!");
        label.setFont(new Font("Arial Black", Font.PLAIN, 12));
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(JLabel.CENTER);
    }
}
