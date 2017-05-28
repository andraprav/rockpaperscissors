import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Andra on 5/28/2017.
 */
public class GUI extends JFrame implements ActionListener {

    int scorePlayer = 0;
    int scoreComputer = 0;
    JLabel playerChoice = new JLabel();
    JLabel computerChoice = new JLabel();
    String EXTENSION = ".png";
    Computer computer;
    int turns = 0;

    public GUI() throws IOException {
        super("Rock Paper Scissors");
        computer = new Computer();
        setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel choicePanel = new JPanel();
        add(choicePanel, BorderLayout.NORTH);
        for (String option : Main.options) {
            addButton(option, choicePanel);
        }

        JPanel resultPanel = new JPanel();
        add(resultPanel, BorderLayout.SOUTH);
        playerChoice.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        resultPanel.add(playerChoice);
        resultPanel.add(computerChoice);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String move = e.getActionCommand();
            displayChoice(move);
            computer.enhanceMemory(move);
        } catch (IOException io) {

        }
    }

    void addButton(String option, JPanel panel) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(option + EXTENSION));
        ImageIcon imageIcon = new ImageIcon(img);

        JButton button = new JButton();
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(100, 100));
        button.setActionCommand(option);
        panel.add(button);
        button.addActionListener(this);
    }

    void displayChoice(String option) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(option + EXTENSION));
        playerChoice.setIcon(new ImageIcon(img));


        String nextMove = computer.getNextMove();
        img = ImageIO.read(Main.class.getResource(nextMove + EXTENSION));
        computerChoice.setIcon(new ImageIcon(img));

        updateScore(option, nextMove);
        playerChoice.setText("You " + scorePlayer);
        computerChoice.setText(scoreComputer + " Computer ");
        setVisible(true);
    }

    void updateScore(String playerChoice, String computerChoice) {
        int playerIndex = Main.options.indexOf(playerChoice);
        int computerIndex = Main.options.indexOf(computerChoice);
        turns++;
        if (computerIndex == playerIndex) {
            return;
        }

        if (playerIndex - computerIndex == 2 || computerIndex - playerIndex == 1) {
            scoreComputer++;
            this.computer.playerWon = false;
        } else {
            scorePlayer++;
            this.computer.playerWon = true;
        }
    }
}
