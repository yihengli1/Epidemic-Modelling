package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class SimulationPanel extends JFrame implements ActionListener {
    ImageIcon image;
    JLabel label;
    JPanel redPanel; //JPanel is container to hold all components
    JPanel bluePanel;
    JPanel greenPanel;
    JButton button;

    @SuppressWarnings("methodlength")
    public SimulationPanel() {

        createCustomLabel();

        createPanels();

        button = new JButton();
        button.setBounds(400, 100, 300, 150);
        button.addActionListener(new ActionListener() { //sets the action of pressing the button
            @Override                                   //can use lambda function of (e -> sout"Who") too
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    System.out.println("Who");
                }
            }
        });
        button.setText("I'm a text"); //sets text of button
        button.setFocusable(false); //removes border around text
        button.setFont(new Font("Zapfino", Font.PLAIN, 20));


        //button.setEnabled(false); //disable/enable button

        createFrame();

        this.add(label);
        greenPanel.add(label);
        this.add(redPanel);
        this.add(bluePanel);
        this.add(greenPanel);
        this.add(button);

        this.setVisible(true); //make frame visible



        revalidate(); //reset components
    }

    public void createFrame() {
        this.setTitle("Epidemic Simulation"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when hit x
        //this.setResizable(false); //makes impossible to resize frame
        this.setSize(1000, 1000); //sets the x-dimension, and y-dimension of frame
        this.setLayout(null); //makes all bounds for labels null

        //this.add(label);

        //this.setVisible(true); //make frame visible

        //this.getContentPane().setBackground(new Color(0xBB3B3B)); //change color of background
    }

    //EFFECTS: Create label with Biohazard Logo
    public void createCustomLabel() {
        produceImg();

        Border border = BorderFactory.createLineBorder(Color.green, 3);

        label = new JLabel(); //create new label
        label.setText("Whats good?"); //set text of label
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER, RIGHT of imageIcon
        label.setVerticalTextPosition(JLabel.TOP); //set text TOP, DOWN, RIGHT of imageIcon
        label.setForeground(new Color(0,0,255));
        label.setFont(new Font("Zapfino", Font.PLAIN, 7)); //set text with name, style, and font-size
        label.setIconTextGap(-10); //set gap of text to image
        //label.setBackground(Color.WHITE); //set background color
        //label.setOpaque(true); //display background color
        //label.setBorder(border); //Set border of label
        label.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
        label.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon_text within label
        label.setBounds(0,0,75,75); //set bounds of label
    }

    //EFFECTS: Get Image file and resize
    public void produceImg() {
        image = new ImageIcon("./src/bio.png"); //Load image
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH); //Scale smooth way
        image = new ImageIcon(newImg);
    }

    public void createPanels() {
        redPanel = new JPanel();
        redPanel.setBackground(Color.RED); //set background colour of panel to red
        redPanel.setBounds(0, 0, 250, 250); //set position and bounds of panel
        redPanel.setLayout(null); //set Layout of componenets, new BorderLayout() defaults to border layout
        //null = label layout, can change with setBounds on label

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(250, 0, 250, 250);
        bluePanel.setLayout(null);

        greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(0, 250, 500, 250);
        greenPanel.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
