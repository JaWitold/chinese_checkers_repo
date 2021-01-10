package dao;

import java.util.List;


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

	public List<Move> getMovesForGame(int id) {
		
		Session session = sessionFactory.openSession();
		String sqlQuery = "FROM Move WHERE game=" + id;
    	    
        @SuppressWarnings("unchecked")
		List<Move> moves = (List<Move>) session.createQuery(sqlQuery).list();
        
        session.close();
        
        for (Move mvs : moves) {
            System.out.println(mvs.getId()+" " +mvs.getFromX()+" "+mvs.getFromY() +" "+mvs.getToX()+ " "+ mvs.getToY()+ " "+mvs.getColor());
       	}
        return moves;
	}
	

}
