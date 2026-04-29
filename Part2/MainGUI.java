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

        JButton resetButton = new JButton("Reset Race");

        JCheckBox autocorrectBox = new JCheckBox("Autocorrect");

        JComboBox<String> passageSelector = new JComboBox<>(
            new String[] {"Short", "Medium", "Long"}
        );
        JLabel statusLabel = new JLabel("Click 'Start Race' to begin.", SwingConstants.CENTER);

        final String[] passage = {"The quick brown fox jumps over the lazy dog."};

        JLabel passageLabel = new JLabel("Passage: " + passage[0], SwingConstants.CENTER);
        passageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

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

                String selected = (String) passageSelector.getSelectedItem();
                if (selected.equals("Short"))
                {
                    passage[0] = "The quick brown fox.";
                }
                else if (selected.equals("Medium"))
                {
                    passage[0] = "The quick brown fox jumps over the lazy dog.";
                }
                else
                {
                    passage[0] = "The quick brown fox jumps over the lazy dog again and again and again until it gets tired.";
                }
                passageLabel.setText(passage[0]);
            
            Timer timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int aliceMove = (int)(Math.random() * 12);
                    int bobMove = (int)(Math.random() * 8);
                    int charlieMove = (int)(Math.random() * 10);

                    if (autocorrectBox.isSelected())
                    {
                        aliceMove = aliceMove / 2;
                        bobMove = bobMove / 2;
                        charlieMove = charlieMove / 2;
                    }

                    aliceBar.setValue(aliceBar.getValue() + aliceMove);
                    bobBar.setValue(bobBar.getValue() + bobMove);
                    charlieBar.setValue(charlieBar.getValue() + charlieMove);

                    int progress = aliceBar.getValue() /2;

                    if (progress > passage[0].length())
                    {
                        progress = passage[0].length();
                    }
                    String completed = passage[0].substring(0, progress).toUpperCase();
                    String remaining = passage[0].substring(progress);

                    passageLabel.setText("Passage: " + completed + remaining);

                if (aliceBar.getValue() >= 100 ||
                    bobBar.getValue() >= 100 ||
                    charlieBar.getValue() >= 100)

                    

                    {
                        ((Timer)e.getSource()).stop();
                        if (aliceBar.getValue() >= 100)
                        {
                            statusLabel.setText("ALICE wins!");
                        }
                        else if (bobBar.getValue() >= 100)
                        {
                            statusLabel.setText("BOB wins!");
                        }
                        else
                        {
                            statusLabel.setText("CHARLIE wins!");
                        }
                }
                }
        });

        timer.start();
    }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aliceBar.setValue(0);
                bobBar.setValue(0);
                charlieBar.setValue(0);
                statusLabel.setText("Race reset. Click 'Start Race' to begin.");
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(3,1));
        topPanel.add(titleLabel);
        topPanel.add(passageLabel);
        topPanel.add(passageSelector);
        topPanel.add(autocorrectBox);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(racePanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.WEST);
        frame.add(resetButton, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}