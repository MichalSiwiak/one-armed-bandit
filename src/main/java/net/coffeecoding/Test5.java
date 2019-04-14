package net.coffeecoding;

import java.util.HashMap;
import java.util.Map;

public class Test5 {
    public static void main(String[] args) {

        Map<String,String > map = new HashMap<>();

        map.put("1","Dupa");
        map.put("2","Dupa");
        map.put("3","Dupa");
        map.put("4","Dupa");
        map.put("5","Dupa");
        map.put("6","Dupa");

        System.out.println(map.size()>5);


    }

}