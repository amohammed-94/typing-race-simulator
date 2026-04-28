package Part2;
import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.Font;

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

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(startButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}