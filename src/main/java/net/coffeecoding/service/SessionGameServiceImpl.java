package net.coffeecoding.service;

import net.coffeecoding.dao.SessionGameDAO;
import net.coffeecoding.entity.SessionGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionGameServiceImpl implements SessionGameService {

    @Autowired
    SessionGameDAO sessionGameDAO;


    @Transactional
    @Override
    public int saveSessionGame(SessionGame sessionGame) {
        sessionGameDAO.saveSessionGame(sessionGame);
        return sessionGame.getNumberOfGame();
    }

    @Transactional
    @Override
    public List<SessionGame> findAll() {
        return sessionGameDAO.findAll();
    }
}
