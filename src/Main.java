import java.io.IOException;

public class Main {

    static GUI gui;

    public static void main(String[] args) {
        try {
            createInterface();
        } catch (IOException io) {

        }
    }

    private static void createInterface() throws IOException {
        gui = new GUI();
    }


}


