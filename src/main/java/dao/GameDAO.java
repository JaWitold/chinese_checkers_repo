package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

import entities.Move;
import entities.Game;


public class GameDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createGame(Game game) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(game);
		session.getTransaction().commit();
		session.close();
	}

	public Set<Move> getMovesForGame(int id) {
		
		Session session = sessionFactory.openSession();
		String sqlQuery = "FROM Move WHERE game=" + id;
    	    
        @SuppressWarnings("unchecked")
		List<Move> moves = (List<Move>) session.createQuery(sqlQuery).list();
        session.close();

		return new HashSet<>(moves);
//        for (Move mvs : moves) {
//            System.out.println(mvs.getId()+" " +mvs.getFromX()+" "+mvs.getFromY() +" "+mvs.getToX()+ " "+ mvs.getToY()+ " "+mvs.getColor());
//       	}
	}

	public List<Game> getGameInfo(int id) {

		Session session = sessionFactory.openSession();
		String sqlQuery = "FROM Game WHERE id=" + id;

		@SuppressWarnings("unchecked")
		List<Game> games = (List<Game>) session.createQuery(sqlQuery).list();

		session.close();

		return games;
	}

}
