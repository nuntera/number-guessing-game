package com.mindera.mindswap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Main game class that handles the game logic and flow
 */
public class Game {
    // Constants for game configuration
    private static final int MIN = 1;                // Minimum number that can be generated/guessed
    private static final int MAX = 10;               // Maximum number that can be generated/guessed
    private static final int WINS_TO_END = 3;        // Number of wins needed to end the game

    // Game state variables
    private final List<Player> players;              // List of players in the game
    private final int[] playerWins;                  // Array to track wins for each player
    private int gameRound;                          // Current game round number

    /**
     * Constructor to initialize the game with a list of players
     * @param players List of players participating in the game
     * @throws IllegalArgumentException if less than 2 players are provided
     */
    public Game(List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("Game needs at least 2 players");
        }
        this.players = players;
        this.playerWins = new int[players.size()];
        this.gameRound = 0;
    }

    /**
     * Starts and manages the main game loop
     */
    public void start() {
        System.out.println("Game is starting");
        printPlayersNames();

        int gameNumber = RandomGenerator.generate(MIN, MAX);
        
        // Continue game until someone wins enough rounds
        while (!hasWinner()) {
            gameRound++;
            System.out.println("\nRound " + gameRound);
            System.out.println("The game number is: " + gameNumber);
            
            boolean roundWon = false;
            // Continue round until someone guesses correctly
            while (!roundWon) {
                for (int i = 0; i < players.size(); i++) {
                    Player currentPlayer = players.get(i);
                    int guess = currentPlayer.pickNumber(MIN, MAX);
                    System.out.println(currentPlayer.getName() + " guesses: " + guess);
                    
                    // Check if current player won
                    if (guess == gameNumber) {
                        System.out.println(currentPlayer.getName() + " wins this round!");
                        playerWins[i]++;
                        roundWon = true;
                        break;
                    }
                }
            }
            
            // Reset for next round
            gameNumber = RandomGenerator.generate(MIN, MAX);
            players.forEach(Player::resetGuesses);
        }
        printGameWinner();
    }

    /**
     * Checks if any player has won enough rounds to win the game
     * @return true if there's a winner, false otherwise
     */
    private boolean hasWinner() {
        for (int wins : playerWins) {
            if (wins >= WINS_TO_END) return true;
        }
        return false;
    }

    /**
     * Prints the final game winner
     */
    private void printGameWinner() {
        for (int i = 0; i < players.size(); i++) {
            if (playerWins[i] >= WINS_TO_END) {
                System.out.println(players.get(i).getName() + " wins with " + playerWins[i] + " victories!");
                return;
            }
        }
    }

    /**
     * Prints the names of all players at the start of the game
     */
    private void printPlayersNames() {
        System.out.println("Players: " + 
            players.stream()
                   .map(Player::getName)
                   .collect(Collectors.joining(" VS ")));
    }
}
