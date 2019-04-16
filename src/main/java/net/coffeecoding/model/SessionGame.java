package net.coffeecoding.model;

import java.util.Date;

public class SessionGame {

    private String gameId;
    private Date created;
    private Date lastSpin;
    private String status;

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

    @Override
    public String toString() {
        return "SessionGame{" +
                "gameId='" + gameId + '\'' +
                ", created=" + created +
                ", lastSpin=" + lastSpin +
                ", status='" + status + '\'' +
                '}';
    }
}
