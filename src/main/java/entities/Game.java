package entities;

import javax.persistence.*;
import java.util.Set;


@Entity(name="Game")
@Table(name="game")
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="players")
	private long players;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="game_id")
	private Set<Move> moves;

	public Game() {
	}

	public Game(long players) {
		this.players = players;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayers() {
		return players;
	}

	public void setPlayers(long players) {
		this.players = players;
	}

	public Set<Move> getMoves() {
		return moves;
	}

	public void setMoves(Set<Move> moves) {
		this.moves = moves;
	}

}

