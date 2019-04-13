package net.coffeecoding;

import net.coffeecoding.model.Game;

public class Test3 {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.toString());
        System.out.println("One spin");
        game.spin();


    }

}