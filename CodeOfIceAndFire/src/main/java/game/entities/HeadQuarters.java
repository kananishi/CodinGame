package game.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import game.Position;
import game.stategy.train.Train;

/**
 * Classe que armazena informacoes dos dois lados da batalha
 *
 */
public final class HeadQuarters {

	private int gold;
	private int income;
	private final List<Building> buildings;
	private Position coordenates;
	private final List<Unit> units = new ArrayList<>();
	private Train strategy;

	public HeadQuarters(final Position coordenates, final List<Building> buildings) {
		this.coordenates = coordenates;
		this.buildings = buildings;
	}

	public static HeadQuarters createHQFoundation() {
		return new HeadQuarters(Position.create(-1, -1), new ArrayList<Building>());
	}

	public int getGold() {
		return gold;
	}

	public void setGold(final int gold) {
		this.gold = gold;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(final int income) {
		this.income = income;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public Position getCoordenates() {
		return coordenates;
	}

	public void setCoordenates(final Position coordenates) {
		if (coordenates.equals(Position.INVALID_POSITION)) {
			this.coordenates = coordenates;
		}
	}

	public List<Unit> getUnits() {
		return units;
	}

	/**
	 * Atualiza a unidade na lista de unidades que possui o id passado como
	 * parametro Se a unidade nao existir ela eh criada e inserida na lista de
	 * unidades
	 */
	void updateUnits(final int id, final int level, final Position position) {
		final Optional<Unit> possibleUnit = units.stream().filter(u -> u.getId() == id).findFirst();
		Unit unit;
		if (!possibleUnit.isPresent()) {
			unit = new Unit(id, level, position);
			units.add(unit);
			return;
		}
		possibleUnit.get().setCoordenates(position);

	}

	public Train getStrategy() {
		return strategy;
	}

	public void setStrategy(final Train strategy) {
		this.strategy = strategy;
	}

	public String runStrategy(final int level) {
		return strategy.train(level);
	}

}
