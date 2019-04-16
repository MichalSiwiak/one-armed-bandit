package net.coffeecoding.TDDtests;

import net.coffeecoding.model.Game;

public class TestGameChanges {
    public static void main(String[] args) {

        Game game = new Game();
        int count = 0;

        while (true) {
            game.spin((byte) 1);
            System.out.println(game.toString());

            if (count == 1000) {
                break;
            }

            count++;
        }

    }
}