package net.coffeecoding;

import net.coffeecoding.model.Game;

import java.util.HashMap;
import java.util.Map;

public class Test5 {
    public static void main(String[] args) {

        Map<String,Object > map = new HashMap<>();

        Object cokolwiek = map.get("cokolwiek");



        System.out.println(cokolwiek.equals(null));
    }

}