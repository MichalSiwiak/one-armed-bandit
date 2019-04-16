package net.coffeecoding.tests;

import net.coffeecoding.model.Game;

public class Test4 {
    public static void main(String[] args) {

        Game game = new Game();
        int count =0;
       /* System.out.println(game.toString());
        game.spin();
        System.out.println(game.toString());
*/


        while (true){
            System.out.println(game.getSymbols()+" || "+game.getWin()+" || "+game.checkWin());

            if(game.getWin()!=0)
                break;
            game.spin();
            count++;

        }

        System.out.println(count);


    }

}