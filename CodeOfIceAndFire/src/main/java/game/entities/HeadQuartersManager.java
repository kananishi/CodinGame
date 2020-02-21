package game.entities;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import game.Game;
import game.Position;
import game.Rules;

public class HeadQuartersManager {

	public static final HeadQuartersManager MANAGER = new HeadQuartersManager();

	private final HeadQuarters ownHeadQuarters = HeadQuarters.createHQFoundation();
	private final HeadQuarters opponentHeadQuarters = HeadQuarters.createHQFoundation();

	public HeadQuarters getOwnHeadQuarters() {
		return ownHeadQuarters;
	}

	public HeadQuarters getOpponentHeadQuarters() {
		return opponentHeadQuarters;
	}

	public void createOrUpdateBuilding(final int owner, final int type, final Position coordenates) {
		final List<Building> buildings = owner == 0 ? ownHeadQuarters.getBuildings()
				: opponentHeadQuarters.getBuildings();
		final Optional<Building> possibleBuilding = buildings.stream()
				.filter(b -> b.getCoordenates().equals(coordenates)).findFirst();

		if (possibleBuilding.isPresent()) {
			return;
		}

		final BuildingBuilder builder = new BuildingBuilder();
		builder.owner(owner);
		builder.type(type);
		builder.coordenates(coordenates);
		buildings.add(builder.build());
	}

	public void updateHeadQuarters(final int gold, final int income, final int opponentGold, final int opponentIncome) {
		if (ownHeadQuarters.getCoordenates() == Position.create(-1, -1)) {
			setHQPosition(true);
		}
		ownHeadQuarters.setGold(gold);
		ownHeadQuarters.setIncome(income);

		if (opponentHeadQuarters.getCoordenates() == Position.create(-1, -1)) {
			setHQPosition(false);
		}
		opponentHeadQuarters.setGold(opponentGold);
		opponentHeadQuarters.setIncome(opponentGold);
	}

	/**
	 * Cria um novo HQ recebe como parametro um booleano que diz se o HQ pertence ao
	 * jogador(true) ou ao bot (false)
	 */
	private void setHQPosition(final boolean own) {

		final HeadQuarters hq = own ? ownHeadQuarters : opponentHeadQuarters;
		final List<Building> builds = hq.getBuildings().stream().filter(b -> b.isOwn() == own).collect(toList());

		final Optional<Building> possibleHQ = builds.stream().filter(b -> b.getType().contentEquals("HQ")).findFirst();
		if (!possibleHQ.isPresent()) {
			throw new IllegalArgumentException("Deve existir um HQ");
		}
		final Building building = possibleHQ.get();
		Game.GAME.log("Create hq isOwn: " + own);
		Game.GAME.log(building.getCoordenates());

		hq.setCoordenates(building.getCoordenates());
	}

	public void updateUnits(final int owner, final int id, final int level, final Position position) {
		if (owner == Rules.AlliesUnits.getValue()) {
			ownHeadQuarters.updateUnits(id, level, position);
			return;
		}
		opponentHeadQuarters.updateUnits(id, level, position);
	}

}
