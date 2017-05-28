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

    public GUI() throws IOException {
        super("Rock Paper Scissors");
        setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel choicePanel = new JPanel();
        add(choicePanel, BorderLayout.NORTH);
        addButton("rock.png", choicePanel);
        addButton("paper.png", choicePanel);
        addButton("scissors.png", choicePanel);


        JPanel resultPanel = new JPanel();
        add(resultPanel, BorderLayout.SOUTH);
        JLabel lab1 = new JLabel("You " + scorePlayer);
        resultPanel.add(lab1);
        resultPanel.add(playerChoice);
        resultPanel.add(computerChoice);

        JLabel lab2 = new JLabel(scoreComputer + " Computer ");
        resultPanel.add(lab2);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            displayChoice(e.getActionCommand());
        } catch (IOException io) {

        }
    }

    void addButton(String fileName, JPanel panel) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(fileName));
        ImageIcon imageIcon = new ImageIcon(img);

        JButton button = new JButton();
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(100, 100));
        button.setActionCommand(fileName);
        panel.add(button);
        button.addActionListener(this);
    }

    void displayChoice(String fileName) throws IOException {
        Image img = ImageIO.read(Main.class.getResource(fileName));
        playerChoice.setIcon(new ImageIcon(img));
        setVisible(true);
    }
}
