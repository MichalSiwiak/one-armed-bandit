package net.coffeecoding.TDDtests;

import net.coffeecoding.model.Game;

public class OneSpinTest {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.toString());
        System.out.println("One spin");
        game.spin((byte)2);
        System.out.println(game.toString());


    }

}