package main.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener {

    int scorePlayer = 0;
    int scoreComputer = 0;
    JLabel playerChoice = new JLabel();
    JLabel computerChoice = new JLabel();
    String EXTENSION = ".png";
    Computer computer;
    int turns = 0;

    public GUI() {
        super("Rock Paper Scissors");
        computer = new Computer();
        setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel choicePanel = new JPanel();
        add(choicePanel, BorderLayout.NORTH);
        for (Character option : Main.options) {
            addButton(option, choicePanel);
        }

        JPanel resultPanel = new JPanel();
        add(resultPanel, BorderLayout.SOUTH);
        computerChoice.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        resultPanel.add(playerChoice);
        resultPanel.add(new JLabel("-"));
        resultPanel.add(computerChoice);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String move = e.getActionCommand();
        displayChoices(move.charAt(0));
        computer.enhanceMemory(move.charAt(0));
    }

    void addButton(Character option, JPanel panel) {


        JButton button = new JButton();
        button.setIcon(getImage(option));
        button.setPreferredSize(new Dimension(100, 100));
        button.setActionCommand(option + "");
        panel.add(button);
        button.addActionListener(this);
    }

    void displayChoices(Character playerMove) {
        playerChoice.setIcon(getImage(playerMove));


        Character computerMove = computer.getNextMove();
        computerChoice.setIcon(getImage(computerMove));

        updateScore(playerMove, computerMove);
        playerChoice.setText("You " + scorePlayer);
        computerChoice.setText(scoreComputer + " Computer ");
        setVisible(true);
    }

    void updateScore(Character playerChoice, Character computerChoice) {
        int playerIndex = Main.options.indexOf(playerChoice);
        int computerIndex = Main.options.indexOf(computerChoice);
        turns++;
        if (computerIndex == playerIndex) {
            this.computer.hisWinnings.add(true);
            return;
        }

        if (playerIndex - computerIndex == 2 || computerIndex - playerIndex == 1) {
            scoreComputer++;
            this.computer.hisWinnings.add(false);
        } else {
            scorePlayer++;
            this.computer.hisWinnings.add(true);
        }
    }

    private ImageIcon getImage(Character move) {
        ImageIcon img = null;
        try {
            img = new ImageIcon(ImageIO.read(Main.class.getResource("../resources/" + move + EXTENSION)));
        } catch (IllegalArgumentException io) {
            System.out.println("no image or smth");
        } catch (IOException io) {
            System.out.println(Arrays.toString(io.getStackTrace()));
        }
        return img;
    }
}
