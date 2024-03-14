package ui;

import model.PopulationDay;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

//represents the statistics and the outcome of the simulation that the user just ended
public class StatisticsPanel extends JFrame {
    JLabel name;
    JLabel deadLabel;
    JLabel percentAlive;
    JLabel percentSick;
    JLabel averageDeath;
    JLabel averageSick;

    JPanel panel;

    ImageIcon smile;
    ImageIcon sad;

    public StatisticsPanel(PopulationDay pd) {
        addName(pd);

        createPhoto();

        createDeadLabel(pd);

        createPercentSick(pd);

        createPercentAlive(pd);

        createAverageDeath(pd);

        createAverageSick(pd);

        this.setTitle("Saved Page"); //Sets title of Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when hit x
        this.setResizable(false); //makes impossible to resize frame
        this.setSize(700, 725); //sets the x-dimension, and y-dimension of frame
        this.setLayout(null); //makes all bounds for labels null, standard layout

        addPanel();

        this.add(panel);
        this.setVisible(true);
    }

    //EFFECTS: creates the title for the panel to display the user's simulation name
    private void addName(PopulationDay pd) {
        name = new JLabel();
        name.setText(pd.getName() + "'s simulation results!");
        name.setFont(new Font("Arial Black", Font.PLAIN, 20));
        name.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates information about the average number of people that were sick in users simulation
    private void createAverageSick(PopulationDay pd) {
        double average2 = ((double) pd.returnTotalSickPopulation() / (double) pd.getDay());
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedAverage2 = df.format(average2);
        averageSick = new JLabel();
        averageSick.setText("Your simulation got " + formattedAverage2 + " people sick on average in one day!");
        if (average2 > 10) {
            averageSick.setIcon(smile);
        } else {
            averageSick.setIcon(sad);
        }
        averageSick.setFont(new Font("Arial Black", Font.PLAIN, 15));
        averageSick.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates information about the average number of people that were dead in users simulation
    private void createAverageDeath(PopulationDay pd) {
        double average1 = ((double) pd.returnTotalDeadPopulation() / (double) pd.getDay());
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedAverage1 = df.format(average1);
        averageDeath = new JLabel();
        averageDeath.setText("Your simulation killed on average " + formattedAverage1 + " people in one day!");
        if (average1 > 5) {
            averageDeath.setIcon(smile);
        } else {
            averageDeath.setIcon(sad);
        }
        averageDeath.setFont(new Font("Arial Black", Font.PLAIN, 15));
        averageDeath.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: adds panel to the frame and adds all components to the panel
    private void addPanel() {
        panel = new JPanel();
        panel.setBounds(0, 0, 700, 700);
        panel.setPreferredSize(new Dimension(700, 700));
        panel.setLayout(new GridLayout(6,1));

        panel.add(name);
        panel.add(deadLabel);
        panel.add(percentAlive);
        panel.add(percentSick);
        panel.add(averageDeath);
        panel.add(averageSick);
    }

    //EFFECTS: creates information about the percentage of people that are alive in users simulation
    private void createPercentAlive(PopulationDay pd) {
        double percent1 = ((double) pd.returnTotalAlivePopulation() / (double) pd.getPeopleSize());
        percent1 *= 100;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedPercent1 = df.format(percent1);
        percentAlive = new JLabel();
        percentAlive.setText(formattedPercent1 + "% people in your simulation survived!");
        if (percent1 > 50) {
            percentAlive.setIcon(sad);
        } else {
            percentAlive.setIcon(smile);
        }
        percentAlive.setFont(new Font("Arial Black", Font.PLAIN, 15));
        percentAlive.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates information about the percentage of people that are sick in users simulation
    private void createPercentSick(PopulationDay pd) {
        double percent2 = ((double) pd.returnTotalSickPopulation() / (double) pd.getPeopleSize());
        percent2 *= 100;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedPercent2 = df.format(percent2);
        percentSick = new JLabel();
        percentSick.setText(formattedPercent2 + "% people in your simulation were sick!");
        if (percent2 > 50) {
            percentSick.setIcon(sad);
        } else {
            percentSick.setIcon(smile);
        }
        percentSick.setFont(new Font("Arial Black", Font.PLAIN, 15));
        percentSick.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates information about the number of people that died in users simulation
    private void createDeadLabel(PopulationDay pd) {
        deadLabel = new JLabel();
        deadLabel.setText("You killed " + pd.returnTotalDeadPopulation() + " people in your simulation!");
        deadLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
        deadLabel.setIcon(smile);
        deadLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    //EFFECTS: creates two photos that are displayed alongside all information packages, sad and smile
    private void createPhoto() {
        smile = new ImageIcon("./src/smile.png");
        Image smile1 = smile.getImage();
        Image newImg = smile1.getScaledInstance(125, 125, Image.SCALE_SMOOTH); //Scale smooth way
        smile = new ImageIcon(newImg);

        sad = new ImageIcon("./src/sad.png");
        Image sad1 = sad.getImage();
        Image newImg1 = sad1.getScaledInstance(125, 125, Image.SCALE_SMOOTH); //Scale smooth way
        sad = new ImageIcon(newImg1);
    }
}
