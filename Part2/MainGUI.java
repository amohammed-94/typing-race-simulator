package Part2;
import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame ("Typing Race GUI");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Typing Race Simulator" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JButton startButton = new JButton("Start Race");

        JButton resetButton = new JButton("Reset Race");

        JButton compareButton = new JButton("Compare Typists");

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

        JCheckBox wristSupportBox = new JCheckBox("Wrist Support");
        JCheckBox energyDrinkBox = new JCheckBox("Energy Drink");
        JCheckBox headphonesBox = new JCheckBox("Noise-Cancelling Headphones");

        JTextField symbolField = new JTextField("A");

        JLabel statusLabel = new JLabel("Click 'Start Race' to begin.", SwingConstants.CENTER);
        
        JLabel bestLabel = new JLabel("Personal Bests: ALICE - 0 WPM | BOB - 0 WPM | CHARLIE - 0 WPM", SwingConstants.CENTER);

        JLabel leaderboardLabel = new JLabel("Leaderboard: ALICE - 0pts | BOB - 0pts | CHARLIE - 0pts", SwingConstants.CENTER);

        final long[] startTime = {0};

        final double[] aliceBestWPM = new double[1];
        final double[] bobBestWPM = new double[1];
        final double[] charlieBestWPM = new double[1];

        final int[] alicePoints = {0};
        final int[] bobPoints = {0};
        final int[] charliePoints = {0};

        final int[] aliceWins = {0};
        final int[] bobWins = {0};
        final int[] charlieWins = {0};

        final String[] passage = {"The quick brown fox jumps over the lazy dog."};

        JLabel passageLabel = new JLabel("Passage: " + passage[0], SwingConstants.CENTER);
        passageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel racePanel = new JPanel();
        racePanel.setLayout(new GridLayout(3,1));

        JTextArea historyArea = new JTextArea(5,30);
        historyArea.setEditable(false);

        JScrollPane historyScroll = new JScrollPane(historyArea);

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

                startTime[0] = System.currentTimeMillis();

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

                    if(wristSupportBox.isSelected())
                    {
                        aliceMove = Math.max(aliceMove, 2);
                        bobMove = Math.max(bobMove, 2);
                        charlieMove = Math.max(charlieMove, 2);
                    }
                    if(energyDrinkBox.isSelected())
                    {
                        if (aliceBar.getValue() <50)
                        {
                            aliceMove += 2;
                            bobMove += 2;
                            charlieMove +=2;

                        }
                        else
                        {
                            aliceMove = Math.max(0, aliceMove - 2);
                            bobMove = Math.max(0, bobMove - 2);
                            charlieMove = Math.max(0, charlieMove - 2);
                        }

                    }
                    if(headphonesBox.isSelected())
                    {
                        aliceMove = (aliceMove + 2) / 2;
                        bobMove = (bobMove + 2) / 2;
                        charlieMove = (charlieMove + 2) / 2;
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

                    long endTime = System.currentTimeMillis();
                    double timeSeconds = (endTime - startTime[0]) / 1000.0;

                    double aliceBonus = 0;
                    double bobBonus = 0;
                    double charlieBonus = 0;

                    int maxPoints = Math.max(alicePoints[0], Math.max(bobPoints[0], charliePoints[0]));

                    if (alicePoints[0] == maxPoints)
                    {
                        aliceBonus = 5;
                    }
                    if (bobPoints[0] == maxPoints)
                    {
                        bobBonus = 5;
                    }
                    if (charliePoints[0] == maxPoints)
                    {
                        charlieBonus = 5;
                    }

                    double aliceWPM = ((aliceBar.getValue() / 100.0) * passage[0].length() / 5.0) / (timeSeconds / 60.0);
                    double bobWPM = ((bobBar.getValue() / 100.0) * passage[0].length() / 5.0) / (timeSeconds / 60.0);
                    double charlieWPM = ((charlieBar.getValue() / 100.0) * passage[0].length() / 5.0) / (timeSeconds / 60.0);

                        if (aliceWPM > aliceBestWPM[0])
                        {
                            aliceBestWPM[0] = aliceWPM;
                        }
                        if (bobWPM > bobBestWPM[0])
                        {
                            bobBestWPM[0] = bobWPM;
                        }
                        if (charlieWPM > charlieBestWPM[0])
                        {
                            charlieBestWPM[0] = charlieWPM;
                        }

                    double wpm = (passage[0].length() / 5.0) / (timeSeconds / 60.0);

                    double aliceAccuracy = 80 + (Math.random() * 20) + aliceBonus;
                    double bobAccuracy = 80 + (Math.random() * 20) + bobBonus;
                    double charlieAccuracy = 80 + (Math.random() * 20) + charlieBonus;

                    int burnout = (int)(Math.random() * 3);
                    double aliceAccuracyChange = aliceAccuracy - 85;
                    double bobAccuracyChange = bobAccuracy - 85;
                    double charlieAccuracyChange = charlieAccuracy - 85;

                    String winnerName = "";

                    if (aliceBar.getValue() >= 100)
                        {
                            winnerName = "ALICE";
                            statusLabel.setText("ALICE wins! WPM: " + (int)wpm +" | Accuracy: " + (int)aliceAccuracy + "% | Burnout: " + burnout + " turns | Accuracy Change: " + (int)aliceAccuracyChange + "%");
                        }
                        else if (bobBar.getValue() >= 100)
                        {
                            winnerName = "BOB";

                            statusLabel.setText("BOB wins! WPM: " + (int)wpm +" | Accuracy: " + (int)bobAccuracy + "% | Burnout: " + burnout + " turns | Accuracy Change: " + (int)bobAccuracyChange + "%");
                        }
                        else
                        {
                            winnerName = "CHARLIE";
                            statusLabel.setText("CHARLIE wins! WPM: " + (int)wpm +" | Accuracy: " + (int)charlieAccuracy + "% | Burnout: " + burnout + " turns | Accuracy Change: " + (int)charlieAccuracyChange + "%");
                        }

                        leaderboardLabel.setText("Leaderboard: ALICE - " + alicePoints[0] + "pts | BOB - " + bobPoints[0] + "pts | CHARLIE - " + charliePoints[0] + "pts");

                        if(winnerName.equals("ALICE"))
                        {
                            alicePoints[0]+=3;
                        }
                        else if(winnerName.equals("BOB"))
                        {
                            bobPoints[0]+=3;
                        }
                        else
                        {
                            charliePoints[0]+=3;
                        }

                        String aliceTitle = aliceWins[0] >= 3 ? "Speed Demon" : "Rookie";
                        String bobTitle = bobWins[0] >= 3 ? "Speed Demon" : "Rookie";
                        String charlieTitle = charlieWins[0] >= 3 ? "Speed Demon" : "Rookie";

                        leaderboardLabel.setText("Leaderboard: ALICE - " + alicePoints[0] + "pts (" + aliceTitle + ") | BOB - " + bobPoints[0] + "pts (" + bobTitle + ") | CHARLIE - " + charliePoints[0] + "pts (" + charlieTitle + ")");

                        bestLabel.setText("Personal Bests: ALICE - " + (int)aliceBestWPM[0] + " WPM | BOB - " + (int)bobBestWPM[0] + " WPM | CHARLIE - " + (int)charlieBestWPM[0] + " WPM");

                            historyArea.append(
                                winnerName + " won | WPM: " + (int)wpm + " | Accuracy: " + (int)aliceAccuracy + "% | Burnout: " + burnout + " turns | Accuracy Change: " + (int)aliceAccuracyChange + "%\n"
                            );
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

        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, 
                    "Typist Comparison:\n" + "ALICE Best WPM: " + (int)aliceBestWPM[0] + "\nBOB Best WPM: " + (int)bobBestWPM[0] + "\nCHARLIE Best WPM: " + (int)charlieBestWPM[0],
                    "Comparison View",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(0,1));
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
        topPanel.add(wristSupportBox);
        topPanel.add(energyDrinkBox);
        topPanel.add(headphonesBox);

        JScrollPane controlScroll = new JScrollPane(topPanel);
        controlScroll.setPreferredSize(new Dimension(260, 500));

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(controlScroll, BorderLayout.EAST);
        frame.add(racePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(compareButton);

        frame.add(buttonPanel, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel(new GridLayout(4,1));
        bottomPanel.add(statusLabel);
        bottomPanel.add(bestLabel);
        bottomPanel.add(leaderboardLabel);
        bottomPanel.add(historyScroll);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}