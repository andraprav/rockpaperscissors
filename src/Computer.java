import java.util.*;

public class Computer {

    String playerLastMove;
    String myLastMove;
    Map<String, String> beatMoves;
    public Boolean playerWon = false;

    public Computer() {
        beatMoves = new HashMap<>();

        beatMoves.put(Main.options.get(0), Main.options.get(1));
        beatMoves.put(Main.options.get(1), Main.options.get(2));
        beatMoves.put(Main.options.get(2), Main.options.get(0));

        myLastMove = dummyStrategy();
    }

    public void enhanceMemory(String move) {
        playerLastMove = move;
    }

    public String getNextMove() {
        return basicStrategy();
    }

    private String dummyStrategy() {
        Random randomGen = new Random();
        int random = randomGen.nextInt(3);
        return Main.options.get(random);
    }

    /**
     * @return if the player won last time it's more probable he'll play the same move (win stay strategy)
     * if the player lost, he'll play the move which will beat my last (lose shift strategy)
     */
    private String basicStrategy() {
        if (playerWon) {
            myLastMove = beatMoves.get(playerLastMove);
        } else {
            myLastMove = beatMoves.get(beatMoves.get(myLastMove));
        }
        return myLastMove;
    }
}
