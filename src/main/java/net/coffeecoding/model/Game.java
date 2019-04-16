package net.coffeecoding.model;

import com.google.gson.Gson;
import net.coffeecoding.config.GameConfig;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private String status;
    private String gameId;
    private int rno;
    private String message;
    private List<List<Byte>> symbols;
    private double win;
    private List<List<Byte>> reels;
    private GameConfig gameConfig;

    public Game() {

        rno = getRandomNumberInRange(0, 500);

        Gson gson = new Gson();
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            status = "ERROR";
            message = "Could not found config file!";
        }

        try (Reader reader = new FileReader(file)) {

            gameConfig = gson.fromJson(reader, GameConfig.class);
            reels = gameConfig.getReels();

            int temp = rno;
            for (int i = 0; i < temp; i++) {
                spin((byte) 1);
            }

            win = 0;

        } catch (IOException e) { // support for  IllegalArgumentException !
            e.printStackTrace();
            status = "ERROR";
            message = "Invalid Config file!";
        }
    }

    public boolean spin(byte numberOfSpins) {

        for (int i = 0; i < numberOfSpins; i++) {
            Collections.rotate(reels.get(0), gameConfig.getSpin().get(0));
            Collections.rotate(reels.get(1), gameConfig.getSpin().get(1));
            Collections.rotate(reels.get(2), gameConfig.getSpin().get(2));
        }

        List<Byte> symbols1 = new ArrayList<>();
        symbols1.add(reels.get(0).get(0));
        symbols1.add(reels.get(0).get(1));
        symbols1.add(reels.get(0).get(2));

        List<Byte> symbols2 = new ArrayList<>();
        symbols2.add(reels.get(1).get(0));
        symbols2.add(reels.get(1).get(1));
        symbols2.add(reels.get(1).get(2));

        List<Byte> symbols3 = new ArrayList<>();
        symbols3.add(reels.get(2).get(0));
        symbols3.add(reels.get(2).get(1));
        symbols3.add(reels.get(2).get(2));

        symbols = new ArrayList<>();
        symbols.add(symbols1);
        symbols.add(symbols2);
        symbols.add(symbols3);

        rno = rno + numberOfSpins;

        if ((symbols.get(0).get(1).equals(symbols.get(1).get(1))) && (symbols.get(0).get(1).equals(symbols.get(2).get(1)))) {
            win = win + gameConfig.getWinnings().get(symbols.get(0).get(1));
            return true;
        } else {
            return false;
        }


    }

    public List<List<Byte>> getInitSymbols() {

        List<List<Byte>> symbols = new ArrayList<>();

        List<Byte> symbols1 = new ArrayList<>();
        symbols1.add((byte) 0);
        symbols1.add((byte) 1);
        symbols1.add((byte) 2);

        List<Byte> symbols2 = new ArrayList<>();
        symbols2.add((byte) 0);
        symbols2.add((byte) 1);
        symbols2.add((byte) 2);

        List<Byte> symbols3 = new ArrayList<>();
        symbols3.add((byte) 0);
        symbols3.add((byte) 1);
        symbols3.add((byte) 2);

        symbols = new ArrayList<>();
        symbols.add(symbols1);
        symbols.add(symbols2);
        symbols.add(symbols3);

        return symbols;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<Byte>> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<List<Byte>> symbols) {
        this.symbols = symbols;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Game{" +
                "status='" + status + '\'' +
                ", gameId=" + gameId +
                ", rno=" + rno +
                ", message='" + message + '\'' +
                ", symbols=" + symbols +
                ", win=" + win +
                ", reels=" + reels +
                ", gameConfig=" + gameConfig +
                '}';
    }
}