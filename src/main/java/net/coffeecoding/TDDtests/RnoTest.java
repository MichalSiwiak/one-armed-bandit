package net.coffeecoding.TDDtests;

import net.coffeecoding.model.Game;

public class RnoTest {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.getRno());

        game.setRno(game.getRno()+1);

        System.out.println(game.getRno());
        System.out.println("Done.");

    }

}