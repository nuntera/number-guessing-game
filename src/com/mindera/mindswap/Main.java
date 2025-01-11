package com.mindera.mindswap;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = Arrays.asList(
            new Player("Pedro"),
            new Player("Maria"),
            new Player("John")
        );

        Game game = new Game(players);
        game.start();
    }
}
