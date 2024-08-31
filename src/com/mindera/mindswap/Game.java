package com.mindera.mindswap;

public class Game {
    private static final int MIN = 1;
    private static final int MAX = 10;

    private final Player player1;
    private final Player player2;

    private int gameRound;
    private int player1Wins;
    private int player2Wins;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameRound = 0;
        player1Wins = 0;
        player2Wins = 0;
    }


    public void start() {
        System.out.println("Game is starting");
        printPlayersName(player1.getName(), player2.getName());

        // Initial game number
        int gameNumber = RandomGenerator.generate(MIN, MAX);

        while (player1Wins < 3 && player2Wins < 3) {
            gameRound++;
            System.out.println("Round " + gameRound);

            int player1Number = this.player1.pickNumber(MIN, MAX);
            int player2Number = this.player2.pickNumber(MIN, MAX);

            boolean winnerFound = determineRoundWinner(gameNumber, player1Number, player2Number);
            // Generate a new game number and reset guesses only if a winner is found
            if (winnerFound) {
                gameNumber = RandomGenerator.generate(MIN, MAX);
                player1.resetGuesses();
                player2.resetGuesses();
            }
        }
        printGameWinner();
    }

    private void printGameWinner() {
        if (getPlayer1Wins() == 3) {
            System.out.println(player1.getName() + " wins with " + getPlayer1Wins() + " victories.");
        }
        else System.out.println(player2.getName() + " wins with " + getPlayer2Wins() + " victories.");
    }

    private boolean determineRoundWinner(int gameNumber, int player1Number, int player2Number) {
        System.out.println("The game number is: " + gameNumber);
        System.out.println(this.player1.getName() + " number is: " + player1Number);
        System.out.println(this.player2.getName() + " number is: " + player2Number);

        if (gameNumber == player1Number) {
            System.out.println(player1.getName() + " wins this round!");
            player1Wins++;
            return true;
        } else if (gameNumber == player2Number) {
            System.out.println(player2.getName() + " wins this round!");
            player2Wins++;
            return true;
        } else {
            System.out.println("It's a tie! No winner this round.");
            return false;
        }
    }

    private void printPlayersName(String player1Name, String player2Name) {
        System.out.println("Players: " + player1Name + " VS " + player2Name);
    }


    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }
}
