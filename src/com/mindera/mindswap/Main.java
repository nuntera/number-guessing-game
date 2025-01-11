package com.mindera.mindswap;

import java.util.Arrays;
import java.util.List;

/**
 * Main class to start the game
 */
public class Main {
    /**
     * Entry point of the application
     * Creates players and starts the game
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create list of players
        List<Player> players = Arrays.asList(
            new Player("Pedro"),
            new Player("Maria"),
            new Player("John")
        );

        // Create and start the game
        Game game = new Game(players);
        game.start();
    }
}
