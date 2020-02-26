package game.entities;

import game.Position;
import game.stategy.move.Move;

public class Unit {

	private int id;
	private int level;
	Position coordenates;
	private Move strategy;

	public Unit(final int id, final int level, final Position coordenates) {
		this.id = id;
		this.level = level;
		this.coordenates = coordenates;
	}

	public static Unit ghostTrain(final int level, final Position coordenates, final Move strategy) {
		final Unit unit = new Unit(-1, level, coordenates);
		unit.setStrategy(strategy);
		return unit;
	}

	public int getId() {
		return id;
	}

	public Unit train(final int id) {
		if (this.id < 0) {
			this.id = id;
			return this;
		}
		throw new IllegalArgumentException("Already trained");
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public Position getCoordenates() {
		return coordenates;
	}

	public void setCoordenates(final Position coordenates) {
		this.coordenates = coordenates;
	}

	public Move getStrategy() {
		return strategy;
	}

	public void setStrategy(final Move strategy) {
		this.strategy = strategy;
	}

	public String runStrategy() {
		return strategy.move(this);
	}
}
