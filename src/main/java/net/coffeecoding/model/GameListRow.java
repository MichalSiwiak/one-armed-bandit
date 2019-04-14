package net.coffeecoding.model;

import java.util.Date;

public class GameListRow {

    private String gameId;
    private Date created;
    private Date lastSpin;

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

    @Override
    public String toString() {
        return "GameListRow{" +
                "gameId='" + gameId + '\'' +
                ", created=" + created +
                ", lastSpin=" + lastSpin +
                '}';
    }
}
