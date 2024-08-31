package com.mindera.mindswap;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private List<Integer> guesses;

    public Player(String name) {
        this.name = name;
        guesses = new ArrayList<>();
    }



    public void resetGuesses() {
        guesses.clear();
    }

    public boolean hasGuessed(int guess) {
        for (int g : guesses) {
            if (g == guess) {
                return true;
            }
        }
        return false;
    }

    private void addGuess(int guess) {
        guesses.add(guess);
    }

    public int pickNumber(int min, int max) {
        int guess;
        do {
            guess = RandomGenerator.generate(min, max);
        } while (hasGuessed(guess));
        addGuess(guess);
        return guess;
    }


    public List<Integer> getGuesses() {
        return guesses;
    }

    public String getName() {
        return name;
    }
}
