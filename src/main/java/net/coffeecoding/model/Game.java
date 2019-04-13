package net.coffeecoding.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.google.gson.Gson;
import net.coffeecoding.config.GameConfig;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private String status;
    private int gameId;
    private int rno;
    private String message;
    private List<List<Byte>> symbols;
    private double win;

    public Game() {

        this.status = "OK";
        this.gameId = 13;
        this.rno = getRandomNumberInRange(0, 500);
        this.message = "OK";

        Gson gson = new Gson();
        try (Reader reader = new FileReader("C:\\Users\\msiwiak\\IdeaProjects\\projects\\one-armed-bandit\\src\\main\\resources\\config.json")) {
            GameConfig gameConfig = gson.fromJson(reader, GameConfig.class);

            List<Byte> reel0 = gameConfig.getReels().get(0);
            List<Byte> reel1 = gameConfig.getReels().get(1);
            List<Byte> reel2 = gameConfig.getReels().get(2);

            System.out.println(reel0.toString());
            System.out.println(reel1.toString());
            System.out.println(reel2.toString());

            for (int i = 0; i < rno; i++) {
                Collections.rotate(reel0, gameConfig.getSpin().get(0));
                Collections.rotate(reel1, gameConfig.getSpin().get(1));
                Collections.rotate(reel2, gameConfig.getSpin().get(2));
            }

            System.out.println("======   rno=" + rno + "   ======");

            System.out.println(reel0.toString());
            System.out.println(reel1.toString());
            System.out.println(reel2.toString());


        } catch (IOException e) { // obsłużyć IllegalArgumentException
            e.printStackTrace();
            this.status = "ERROR";
            this.message = "Invalid Config file.";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
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
                '}';
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
