package net.coffeecoding.TDDtests;

import java.util.Random;


public class RandomRnoTest {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            System.out.println(getRandomNumberInRange(0, 5));
        }


    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}