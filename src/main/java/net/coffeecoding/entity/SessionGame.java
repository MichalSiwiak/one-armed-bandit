package net.coffeecoding.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sessionGame")
public class SessionGame {

    @Id
    @GeneratedValue
    private int numberOfGame;
    private String gameId;
    private Date created;
    private Date lastSpin;
    private String status;
    private double win;

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
}
