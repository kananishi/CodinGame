package game.entities;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import game.Game;
import game.Position;
import game.stategy.train.Train;

/**
 * Classe que armazena informacoes dos dois lados da batalha
 *
 */
public final class HeadQuarters {

	private int gold;
	private int income;
	private Position coordenates;
	private final List<Position> access = new ArrayList<>();

	private final List<Building> buildings;

	private final List<Unit> army = new ArrayList<>();

	private final List<Unit> unitsToTrain = new ArrayList<>();
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

	public Position getCoordenates() {
		return coordenates;
	}

	public void setCoordenates(final Position coordenates) {
		Game.GAME.log(this.coordenates);
		if (!this.coordenates.isValid()) {
			this.coordenates = coordenates;
		}
	}

	public List<Position> getAcess() {
		if (access.isEmpty()) {
			access.addAll(getAccess());
		}
		return access;
	}

	private List<Position> getAccess() {
		final List<Position> possiblePosition = Arrays.asList(coordenates.getNorthPosition(),
				coordenates.getSouthPosition(), coordenates.getEastPosition(), coordenates.getWestPosition());
		return possiblePosition.parallelStream().filter(p -> p.isValid()).collect(toList());
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public List<Unit> getArmy() {
		return army;
	}

	/**
	 * Atualiza a unidade na lista de unidades que possui o id passado como
	 * parametro Se a unidade nao existir ela eh criada e inserida na lista de
	 * unidades
	 */
	void updateUnit(final int id, final int level, final Position position) {
		if (isUnitToTrain(level, position)) {
			trainUnit(id, level, position);
		}

		final Optional<Unit> possibleUnit = army.stream().filter(u -> u.getId() == id).findFirst();
		if (!possibleUnit.isPresent()) {
			final Unit unit = new Unit(id, level, position);
			army.add(unit);
			return;
		}
		possibleUnit.get().setCoordenates(position);
	}

	private boolean isUnitToTrain(final int level, final Position unitCoordenate) {
		return unitsToTrain.stream().filter(u -> u.getLevel() == level && u.getCoordenates().equals(unitCoordenate))
				.count() > 0;
	}

	public void addUnitToTrain(final Unit unit) {
		unitsToTrain.add(unit);
	}

	private boolean trainUnit(final int id, final int level, final Position coordenates) {
		final Unit unitToTrain = unitsToTrain.stream()
				.filter(u -> u.getLevel() == level && u.getCoordenates().equals(coordenates)).findAny()
				.orElseThrow(NullPointerException::new);
		army.add(unitToTrain.train(id));
		unitsToTrain.remove(unitToTrain);
		return true;
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
