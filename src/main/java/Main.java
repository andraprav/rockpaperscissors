package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static GUI gui;
    static List<Character> options = new ArrayList<>(Arrays.asList('r', 'p', 's'));

    public static void main(String[] args) {
        gui = new GUI();
    }
}


