import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static GUI gui;
    static List<String> options = new ArrayList(Arrays.asList("rock", "paper", "scissors"));

    public static void main(String[] args) {
        try {
            gui = new GUI();
        } catch (IOException io) {

        }
    }
}


