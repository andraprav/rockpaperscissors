import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main {
    static int scorePlayer = 0;
    static int scoreComputer = 0;
    static JLabel playerChoice = new JLabel();
    static JLabel computerChoice = new JLabel();
    static JFrame frame;

    public static void main(String[] args) {
        try {
            createInterface();
        } catch (IOException io) {

        }
    }

    private static void createInterface() throws IOException {
        frame = new JFrame("Test");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel choicePanel = new JPanel();
        frame.add(choicePanel, BorderLayout.NORTH);
        addButton("rock.png", choicePanel);
        addButton("paper.png", choicePanel);
        addButton("scissors.png", choicePanel);


        JPanel resultPanel = new JPanel();
        frame.add(resultPanel, BorderLayout.SOUTH);
        JLabel lab1 = new JLabel("You " + scorePlayer);
        resultPanel.add(lab1);
        resultPanel.add(playerChoice);
        resultPanel.add(computerChoice);

        JLabel lab2 = new JLabel(scoreComputer + " Computer ");
        resultPanel.add(lab2);
        frame.setVisible(true);

    }

    static class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                displayChoice(e.getActionCommand());
            } catch (IOException io) {

            }
        }
    }

    static void addButton(String fileName, JPanel panel) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(fileName));
        ImageIcon imageIcon = new ImageIcon(img);

        JButton button = new JButton();
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(100, 100));
        button.setActionCommand(fileName);
        panel.add(button);
        button.addActionListener(new Action());
    }

    static void displayChoice(String fileName) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(fileName));
        playerChoice.setIcon(new ImageIcon(img));
        frame.setVisible(true);
    }
}


