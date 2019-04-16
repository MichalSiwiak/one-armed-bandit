package net.coffeecoding.model;

import java.util.Date;

public class SessionGame {


    private int numberOfGame;
    private String gameId;
    private Date created;
    private Date lastSpin;
    private String status;
    private double win;

    public int getNumberOfGame() {
        return numberOfGame;
    }

    public void setNumberOfGame(int numberOfGame) {
        this.numberOfGame = numberOfGame;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastSpin() {
        return lastSpin;
    }

    public void setLastSpin(Date lastSpin) {
        this.lastSpin = lastSpin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "SessionGame{" +
                "numberOfGame=" + numberOfGame +
                ", gameId='" + gameId + '\'' +
                ", created=" + created +
                ", lastSpin=" + lastSpin +
                ", status='" + status + '\'' +
                ", win=" + win +
                '}';
    }
}
