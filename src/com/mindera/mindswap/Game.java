package com.mindera.mindswap;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final int WINS_TO_END = 3;

    private final List<Player> players;
    private final int[] playerWins;
    private int gameRound;

    public Game(List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("Game needs at least 2 players");
        }
        this.players = players;
        this.playerWins = new int[players.size()];
        this.gameRound = 0;
    }

    public void start() {
        System.out.println("Game is starting");
        printPlayersNames();

        int gameNumber = RandomGenerator.generate(MIN, MAX);
        
        while (!hasWinner()) {
            gameRound++;
            System.out.println("\nRound " + gameRound);
            System.out.println("The game number is: " + gameNumber);
            
            boolean roundWon = false;
            while (!roundWon) {
                for (int i = 0; i < players.size(); i++) {
                    Player currentPlayer = players.get(i);
                    int guess = currentPlayer.pickNumber(MIN, MAX);
                    System.out.println(currentPlayer.getName() + " guesses: " + guess);
                    
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

    private boolean hasWinner() {
        for (int wins : playerWins) {
            if (wins >= WINS_TO_END) return true;
        }
        return false;
    }

    private void printGameWinner() {
        for (int i = 0; i < players.size(); i++) {
            if (playerWins[i] >= WINS_TO_END) {
                System.out.println(players.get(i).getName() + " wins with " + playerWins[i] + " victories!");
                return;
            }
        }
    }

    private void printPlayersNames() {
        System.out.println("Players: " + 
            players.stream()
                   .map(Player::getName)
                   .collect(Collectors.joining(" VS ")));
    }
}
