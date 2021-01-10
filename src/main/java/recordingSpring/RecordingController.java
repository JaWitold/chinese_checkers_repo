package recordingSpring;

import java.util.*;

import dao.GameDAO;
import game.CustomColor;

import entities.Game;
import entities.Move;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RecordingController {
	final ApplicationContext appContext;
	Game game;
	Set<Move> moves;

//	public static void main(String[] args) {
//		RecordingController RC = new RecordingController(3);
//		RC.addMoveToTheGame(1,2,3,4, CustomColor.BLUE);
//		RC.addMoveToTheGame(1,2,3,3, CustomColor.BLUE);
//		RC.addMoveToTheGame(1,2,3,5, CustomColor.BLUE);
//		RC.saveGameInDatabase();
//		RC.getGameFromDatabase(1);
//	}

	public RecordingController(){
		appContext = new ClassPathXmlApplicationContext(
				"config/spring-configuration.xml");

	}

	public RecordingController(final int players){
		appContext = new ClassPathXmlApplicationContext(
				"config/spring-configuration.xml");
		game = new Game(players);
		moves = new LinkedHashSet<>();
	}

	public void addMoveToTheGame(final int fromX, final int fromY, final int toX, final int toY, final CustomColor color) {
		final Move newMove = new Move(fromX, fromY, toX, toY, color.toString(), game);
		addMoveToTheGame(newMove);
	}
	public void addMoveToTheGame(final Move newMove) {
		moves.add(newMove);
	}

	public void saveGameInDatabase() {
		moves.forEach(mvs -> System.out.println(mvs.getColor()));
		game.setMoves(moves);
		final GameDAO gameDAO = (GameDAO) this.appContext.getBean("gameDAO");
		gameDAO.createGame(game);
	}

	public boolean getGameFromDatabase(final int id) {
		game = null;
		final GameDAO gameDAO = (GameDAO) this.appContext.getBean("gameDAO");
		moves = gameDAO.getMovesForGame(id);
		game = gameDAO.getGameInfo(id).get(0);
		return game != null;
	}
}
