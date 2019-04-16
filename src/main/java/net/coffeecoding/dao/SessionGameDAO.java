package net.coffeecoding.dao;

import net.coffeecoding.entity.SessionGame;

import java.util.List;

public interface SessionGameDAO {

    public int saveSessionGame(SessionGame sessionGame);

    public List<SessionGame> findAll();


}
