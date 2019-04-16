package net.coffeecoding.dao;


import net.coffeecoding.entity.SessionGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SessionGameDAOImpl implements SessionGameDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveSessionGame(SessionGame sessionGame) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(sessionGame);
        return sessionGame.getNumberOfGame();
    }

    @Override
    public List<SessionGame> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<SessionGame> query =
                currentSession.createQuery("SELECT s from SessionGame s",
                        SessionGame.class);
        List<SessionGame> sessionGames = query.getResultList();

        return sessionGames;
    }











   /* @Override
    public int saveSales(Sales sales) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(sales);
        return sales.getId();
    }

    @Override
    public List<Sales> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sales> query =
                currentSession.createQuery("SELECT c from Sales c",
                        Sales.class);
        List<Sales> salesList = query.getResultList();

        return salesList;
    }*/


}
