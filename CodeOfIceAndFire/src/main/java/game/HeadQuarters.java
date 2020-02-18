package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe que armazena informacoes dos dois lados da batalha
 *
 */
final class HeadQuarters implements Train {

	private int gold;
	private int income;
	private final List<Building> buildings;
	private final Position coordenates;
	private final List<Unit> units = new ArrayList<>();

	public HeadQuarters(final Position coordenates, final List<Building> buildings) {
		this.coordenates = coordenates;
		this.buildings = buildings;
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

	List<Unit> getUnits() {
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

	@Override
	public String train(final Position coordenate) {
		// TODO Auto-generated method stub
		return null;
	}
}
