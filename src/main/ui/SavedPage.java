package ui;

import javax.swing.*;
import java.awt.*;

//Represents a pop-up that tells the user that their simulation is saved
public class SavedPage extends JFrame {
    JLabel label;

    public SavedPage() {
        label = new JLabel();
        label.setText("Saved Simulation Data Successfully!");
        label.setFont(new Font("Arial Black", Font.PLAIN, 15));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        this.setTitle("Saved Page"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(400, 300); //sets the x-dimension, and y-dimension of frame
        this.setLayout(new GridLayout(1,1)); //makes all bounds for labels null, standard layout

        this.add(label);
        this.setVisible(true);
    }
}
