package Part2;
import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame ("Typing Race GUI");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startButton = new JButton("Start Race");

        frame.add(startButton);
        frame.setVisible(true);
    }
}