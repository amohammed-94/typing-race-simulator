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

        JCheckBox caffeineBox = new JCheckBox("Caffeine Mode");

        JCheckBox nightShiftBox = new JCheckBox("Night Shift");

        JComboBox<String> passageSelector = new JComboBox<>(
            new String[] {"Short", "Medium", "Long"}
        );

        JComboBox<Integer> seatCountSelector = new JComboBox<>(
            new Integer[] {2, 3, 4, 5, 6}
        );

        JComboBox<String> styleSelector = new JComboBox<>(
            new String[] {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"}
        );

        JComboBox<String> keyboardSelector = new JComboBox<>(
            new String[] {"Mechanical", "Membrane", "Touchscreen", "Stenography"}
        );

        JComboBox<String> colourSelector = new JComboBox<>(
            new String[] {"Blue", "Red", "Green", "Orange"}
        );

        JTextField symbolField = new JTextField("A");

        JLabel statusLabel = new JLabel("Click 'Start Race' to begin.", SwingConstants.CENTER);

        final String[] passage = {"The quick brown fox jumps over the lazy dog."};

        JLabel passageLabel = new JLabel("Passage: " + passage[0], SwingConstants.CENTER);
        passageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel racePanel = new JPanel();
        racePanel.setLayout(new GridLayout(3,1));

        JProgressBar aliceBar = new JProgressBar(0, 100);
        aliceBar.setStringPainted(true);

        JProgressBar bobBar = new JProgressBar(0, 100);
        bobBar.setStringPainted(true);

        JProgressBar charlieBar = new JProgressBar(0, 100);
        charlieBar.setStringPainted(true);

        racePanel.add(aliceBar);
        racePanel.add(bobBar);
        racePanel.add(charlieBar);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Race Started!");

                String symbol = symbolField.getText();

                aliceBar.setString("ALICE (" + symbol + ")");
                bobBar.setString("BOB (" + symbol + ")");
                charlieBar.setString("CHARLIE (" + symbol + ")");
                
                String colour = (String) colourSelector.getSelectedItem();
                if (colour.equals("Blue"))
                {
                    aliceBar.setForeground(java.awt.Color.BLUE);
                    bobBar.setForeground(java.awt.Color.BLUE);
                    charlieBar.setForeground(java.awt.Color.BLUE);
                }
                else if (colour.equals("Red"))
                {
                    aliceBar.setForeground(java.awt.Color.RED);
                    bobBar.setForeground(java.awt.Color.RED);
                    charlieBar.setForeground(java.awt.Color.RED);
                }
                else if (colour.equals("Green"))
                {
                    aliceBar.setForeground(java.awt.Color.GREEN);
                    bobBar.setForeground(java.awt.Color.GREEN);
                    charlieBar.setForeground(java.awt.Color.GREEN);
                }
                else if (colour.equals("Orange"))
                {
                    aliceBar.setForeground(java.awt.Color.ORANGE);
                    bobBar.setForeground(java.awt.Color.ORANGE);
                    charlieBar.setForeground(java.awt.Color.ORANGE);
                }

                int seatCount = (Integer) seatCountSelector.getSelectedItem();
                aliceBar.setVisible(seatCount >= 1);
                bobBar.setVisible(seatCount >= 2);
                charlieBar.setVisible(seatCount >= 3);

                racePanel.revalidate();
                racePanel.repaint();

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

                    if (caffeineBox.isSelected())
                    {
                        aliceMove = (int)(aliceMove * 2);
                        bobMove = (int)(bobMove * 2);
                        charlieMove = (int)(charlieMove * 2);
                    }

                    if (nightShiftBox.isSelected())
                    {
                        aliceMove = (int)(aliceMove / 2);
                        bobMove = (int)(bobMove / 2);
                        charlieMove = (int)(charlieMove / 2);
                    }

                    if (autocorrectBox.isSelected())
                    {
                        aliceMove = aliceMove / 2;
                        bobMove = bobMove / 2;
                        charlieMove = charlieMove / 2;
                    }

                    if(styleSelector.getSelectedItem().equals("Hunt & Peck"))
                    {
                        aliceMove = Math.max(0, aliceMove - 2);
                        bobMove = Math.max(0, bobMove - 2);
                        charlieMove = Math.max(0, charlieMove - 2);
                    }
                    else if(styleSelector.getSelectedItem().equals("Phone Thumbs"))
                    {
                        aliceMove += 1;
                        bobMove += 1;
                        charlieMove +=1;
                    }
                    else if(styleSelector.getSelectedItem().equals("Voice-to-Text"))
                    {
                        aliceMove += 3;
                        bobMove += 3;
                        charlieMove +=3;
                    }
                    else if(styleSelector.getSelectedItem().equals("Touch Typist"))
                    {
                        aliceMove += 2;
                        bobMove += 2;
                        charlieMove +=2;
                    }

                    String keyboard = (String) keyboardSelector.getSelectedItem();

                    if(keyboard.equals("Mechanical"))
                    {
                        aliceMove += 2;
                        bobMove += 2;
                        charlieMove +=2;
                    }
                    else if(keyboard.equals("Membrane"))
                    {
                        aliceMove += 1;
                        bobMove += 1;
                        charlieMove +=1;
                    }
                    else if(keyboard.equals("Touchscreen"))
                    {
                        aliceMove = Math.max(0, aliceMove - 1);
                        bobMove = Math.max(0, bobMove - 1);
                        charlieMove = Math.max(0, charlieMove - 1);
                    }
                    else if(keyboard.equals("Stenography"))
                    {
                        aliceMove += 4;
                        bobMove += 4;
                        charlieMove +=4;
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

        JPanel topPanel = new JPanel(new GridLayout(11,1));
        topPanel.add(titleLabel);
        topPanel.add(passageLabel);
        topPanel.add(passageSelector);
        topPanel.add(autocorrectBox);
        topPanel.add(caffeineBox);
        topPanel.add(nightShiftBox);
        topPanel.add(seatCountSelector);
        topPanel.add(styleSelector);
        topPanel.add(keyboardSelector);
        topPanel.add(colourSelector);
        topPanel.add(symbolField);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(racePanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.WEST);
        frame.add(resetButton, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}