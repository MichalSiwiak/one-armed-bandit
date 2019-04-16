package net.coffeecoding.TDDtests;

import net.coffeecoding.model.Game;

public class WinSummaryTest {
    public static void main(String[] args) {

        Game game = new Game();
        int wins = 0;
        int count = 0;

        while (true) {
            boolean spin = game.spin((byte) 1);

            if (spin ==true) {
                wins++;
                System.out.println(game.toString());
            }

            if (wins == 52) {
                break;
            }

            count++;
        }

        System.out.println(count);
    }
}