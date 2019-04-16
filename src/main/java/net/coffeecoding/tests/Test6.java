package net.coffeecoding.tests;

import net.coffeecoding.model.Game;

import java.util.HashMap;
import java.util.Map;

public class Test6 {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.getRno());

        game.setRno(game.getRno()+1);

        System.out.println(game.getRno());
        System.out.println("Done.");

    }

}