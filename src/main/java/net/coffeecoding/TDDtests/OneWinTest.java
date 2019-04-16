package net.coffeecoding.TDDtests;

import net.coffeecoding.model.Game;

public class OneWinTest {
    public static void main(String[] args) {

        Game game = new Game();
        int count = 0;


        while (true) {
            System.out.println(game.getSymbols() + " || " + game.getWin() + " || ");

            if (game.getWin() != 0)
                break;
            game.spin((byte)(1));
            count++;
        }
        System.out.println(count);
    }
}