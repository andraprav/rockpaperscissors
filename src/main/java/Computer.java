package main.java;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * the bot's strategies which plays to hooman
 * the comments, fields naming and others are from bot's perspective
 */
public class Computer {

    int rounds;
    final int MEMORY = 4;

    //these are my memories
    StringBuilder myMoves;
    StringBuilder hisMoves;
    public List<Boolean> hisWinnings;

    //this is my knowledge
    Map<Character, Character> beatMoves;

    public Computer() {
        beatMoves = new HashMap<>();
        myMoves = new StringBuilder();
        hisMoves = new StringBuilder();
        hisWinnings = new ArrayList<>();

        //I know rock beats scissors and so on
        beatMoves.put(Main.options.get(0), Main.options.get(1));
        beatMoves.put(Main.options.get(1), Main.options.get(2));
        beatMoves.put(Main.options.get(2), Main.options.get(0));

        //my first memories are delusions because IOOB
        myMoves.append(dummyStrategy());
        hisMoves.append(dummyStrategy());
        hisWinnings.add(true);
        rounds = 1;

    }

    /**
     * at the end of each turn I memorize what my opponent has done
     *
     * @param move what he moved
     */
    public void enhanceMemory(char move) {

        hisMoves.append(move);
        rounds++;

    }

    /**
     * apply strategy and save my move into memory
     *
     * @return my next move depending on the strategy I'm adopting
     */
    public Character getNextMove() {
        Character myMove = learningStrategy();
        myMoves.append(myMove);
        return myMove;
    }


    /**
     * applies a dummy strategy
     *
     * @return a random move
     */
    private char dummyStrategy() {
        Random randomGen = new Random();
        int random = randomGen.nextInt(Main.options.size());
        return Main.options.get(random);
    }

    /**
     * knows how players usually play and returns beat moves for them
     *
     * @return if the player won last time it's more probable he'll play the same move (win stay strategy)
     * if the player lost, he'll play the move which will beat my last (lose shift strategy)
     */
    private Character basicStrategy() {

        Character move;
        if (hisWinnings.get(rounds - 1)) {
            move = beatMoves.get(hisMoves.charAt(rounds - 1));
        } else {
            move = beatMoves.get(beatMoves.get(myMoves.charAt(rounds - 1)));
        }
        return move;
    }

    /**
     * if I learned enough, I'll return the beating move for my prediction of player's next move
     * if not, I'll apply basic strategy
     *
     * @return the next move
     */
    private Character learningStrategy() {

        //if we haven't played for 10 points, I'll just use my basic strategy
        if (rounds < 10) {
            return basicStrategy();
        }

        Set<Integer> hisPositions = getPatternPositions(hisMoves.toString());

        //if the pattern was never used before by this player, I'll just use my basic strategy
        if (hisPositions.isEmpty()) {
            return basicStrategy();
        }

        //get all next moves after pattern match and extract the most frequent one
        List<Character> hisPossibleNextMove = getHisPossibleNextMove(hisPositions);
        int max = 0;
        Character hisProbableNextMove = Main.options.get(0);

        for (Character option : Main.options) {

            int occurrences = Collections.frequency(hisPossibleNextMove, option);
            if (occurrences > max) {
                max = occurrences;
                hisProbableNextMove = option;
            }
        }

        //get the move which beats prediction of opponent's move
        return beatMoves.get(hisProbableNextMove);
    }

    /**
     * @param moves all the moves in my memory (opponent's or mine)
     * @return indexes of previous nextMoves after pattern match,
     * where the pattern is represented by the last <MEMORY> moves
     * e.g. for moves: mnamnflmn and MEMORY = 2 will return {2, 5}
     * this regex will sadly only return {4} for moves: prprprpr, memory: 4 and lose the {6}
     */
    private Set<Integer> getPatternPositions(String moves) {
        //get his last <MEMORY> moves and see if he did that in the past
        String lastMoves = moves.substring(rounds - MEMORY, rounds);
        Set<Integer> positions = new HashSet<>();
        Pattern p = Pattern.compile(lastMoves);
        Matcher m = p.matcher(moves.substring(0, rounds - 2)); // to not get the last match
        while (m.find()) {
            positions.add(m.end());
        }
        return positions;
    }

    /**
     * @param positions all next positions where the pattern matched
     * @return the list of corresponding position moves
     */
    private List<Character> getHisPossibleNextMove(Set<Integer> positions) {
        //interested only where player won or it was tie
        positions.stream().filter(p -> hisWinnings.get(p));
        return positions.stream().map(position -> hisMoves.charAt(position)).collect(Collectors.toList());
    }
}
