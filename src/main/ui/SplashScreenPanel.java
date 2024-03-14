package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreenPanel extends JFrame {
    JLabel label;
    ImageIcon image;

    public SplashScreenPanel() {
        image = new ImageIcon("./src/bio.png"); //Load image
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(125, 125, java.awt.Image.SCALE_SMOOTH); //Scale smooth way
        image = new ImageIcon(newImg);

        createLabel();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(400, 400); //sets the x-dimension, and y-dimension of frame
        this.setLayout(new BorderLayout()); //makes all bounds for labels null, standard layout
        this.getContentPane().setBackground(Color.BLACK);
        this.add(label);
        this.setVisible(true);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.dispose();
        new SimulationPanel();


    }

    private void createLabel() {
        label = new JLabel();
        label.setIcon(image);
        label.setText("Loading Epidemic Simulator!");
        label.setFont(new Font("Arial Black", Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER, RIGHT of imageIcon
        label.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP, DOWN, RIGHT of imageIcon
    }
}
