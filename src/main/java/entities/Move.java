package entities;

import javax.persistence.*;

@Entity(name="Move")
@Table(name="moves")
public class Move {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="fromX")
	private int fromX;

	@Column(name="fromY")
	private int fromY;

	@Column(name="toX")
	private int toX;

	@Column(name="toY")
	private int toY;

	@Column(name="color")
	private String color;

	@ManyToOne
	private Game game;

	public Move() {
	}

	public Move(final int fromX, final int fromY, final int toX, final int toY, final String color, final Game game) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.color = color;
		this.game = game;

	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setFromX(final int fromX) {
		this.fromX = fromX;
	}

	public int getFromX() {
		return fromX;
	}

	public void setFromY(final int fromY) {
		this.fromY = fromY;
	}

	public int getFromY() {
		return fromY;
	}

	public void setToX(final int toX) {
		this.toX = toX;
	}

	public int getToX() {
		return toX;
	}

	public void setToY(final int toY) {
		this.toY = toY;
	}

	public int getToY() {
		return toY;
	}

	public void setGame(final Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}
}

