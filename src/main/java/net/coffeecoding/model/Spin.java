package net.coffeecoding.model;

public class Spin {

    private String gameId;
    private int rno;

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

    @Override
    public String toString() {
        return "Spin{" +
                "gameId='" + gameId + '\'' +
                ", rno=" + rno +
                '}';
    }
}
