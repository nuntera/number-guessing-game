package com.mindera.mindswap;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game
 */
public class Player {

    private final String name;                   // Player's name
    private List<Integer> guesses;               // List to store player's guesses for current round

    /**
     * Constructor to create a new player
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
        guesses = new ArrayList<>();
    }

    /**
     * Clears all guesses for a new round
     */
    public void resetGuesses() {
        guesses.clear();
    }

    /**
     * Checks if a number has already been guessed by this player
     * @param guess The number to check
     * @return true if the number was already guessed, false otherwise
     */
    public boolean hasGuessed(int guess) {
        for (int g : guesses) {
            if (g == guess) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new guess to the player's list of guesses
     * @param guess The number to add
     */
    private void addGuess(int guess) {
        guesses.add(guess);
    }

    /**
     * Generates a unique random number for the player's guess
     * @param min Minimum possible number
     * @param max Maximum possible number
     * @return A number that hasn't been guessed before
     */
    public int pickNumber(int min, int max) {
        int guess;
        do {
            guess = RandomGenerator.generate(min, max);
        } while (hasGuessed(guess));
        addGuess(guess);
        return guess;
    }

    /**
     * Gets the list of player's guesses
     * @return List of previous guesses
     */
    public List<Integer> getGuesses() {
        return guesses;
    }

    /**
     * Gets the player's name
     * @return Player's name
     */
    public String getName() {
        return name;
    }
}
