package net.coffeecoding.TDDtests;

import java.util.HashMap;
import java.util.Map;

public class LimitGameTest {
    public static void main(String[] args) {

        Map<String,String > map = new HashMap<>();

        map.put("1","Test");
        map.put("2","Test");
        map.put("3","Test");
        map.put("4","Test");
        map.put("5","Test");
        map.put("6","Test");

        System.out.println(map.size()>5);


    }

}