package net.coffeecoding.service;

import net.coffeecoding.entity.SessionGame;

import java.util.List;

public interface SessionGameService {

    public int saveSessionGame(SessionGame sessionGame);

    public List<SessionGame> findAll();

}
