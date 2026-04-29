package Part2;
import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame ("Typing Race GUI");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Typing Race Simulator" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JButton startButton = new JButton("Start Race");

        JLabel statusLabel = new JLabel("Click 'Start Race' to begin.", SwingConstants.CENTER);

        JPanel racePanel = new JPanel();
        racePanel.setLayout(new GridLayout(3,1));

        JProgressBar aliceBar = new JProgressBar(0, 100);
        aliceBar.setStringPainted(true);
        aliceBar.setString("ALICE");

        JProgressBar bobBar = new JProgressBar(0, 100);
        bobBar.setStringPainted(true);
        bobBar.setString("BOB");

        JProgressBar charlieBar = new JProgressBar(0, 100);
        charlieBar.setStringPainted(true);
        charlieBar.setString("CHARLIE");

        racePanel.add(aliceBar);
        racePanel.add(bobBar);
        racePanel.add(charlieBar);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Race Started!");
            
            aliceBar.setValue(85);
            bobBar.setValue(60);
            charlieBar.setValue(40);
            }
        });

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(racePanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.WEST);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}