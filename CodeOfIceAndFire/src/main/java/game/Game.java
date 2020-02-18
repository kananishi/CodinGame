package game;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class Game implements Output {

	public static Game GAME = new Game();

	private HeadQuarters ownHeadQuarters;
	private HeadQuarters opponentHeadQuarters;

	private int numberMineSpots;
	private final List<Position> mineSpots = new ArrayList<>();

	private final Map map = new Map();

	private int buildingCount;
	private final List<Building> buildings = new ArrayList<>();

	private Game() {
	}

	HeadQuarters getOwnHeadQuarters() {
		return ownHeadQuarters;
	}

	HeadQuarters getOpponentHeadQuarters() {
		return opponentHeadQuarters;
	}

	List<Position> getMineSpots() {
		return mineSpots;
	}

	Map getMap() {
		return map;
	}

	List<Building> getBuildings() {
		return buildings;
	}

	void setMines() {
		numberMineSpots = InputHolder.input.nextInt();
		for (int i = 0; i < numberMineSpots; i++) {
			mineSpots.add(Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt()));
		}
	}

	void run() {
		updateParameters();
		exec(Strategy.TrainOneAndMoveToOpponentHQ.run());
	}

	void updateParameters() {
		final int gold = InputHolder.input.nextInt();
		final int income = InputHolder.input.nextInt();
		final int opponentGold = InputHolder.input.nextInt();
		final int opponentIncome = InputHolder.input.nextInt();

		for (int i = 0; i < Rules.MapWide.getValue(); i++) {
			map.setMapOnLine(i, InputHolder.input.next());
		}

		createOrUpdateBuildings(InputHolder.input.nextInt());

		createOrUpdateHeadQuarters(gold, income, opponentGold, opponentIncome);

		createOrUpdateUnits(InputHolder.input.nextInt());
	}

	private void createOrUpdateBuildings(final int numberOfBuildings) {
		for (int i = 0; i < numberOfBuildings; i++) {
			final int owner = InputHolder.input.nextInt();
			final int type = InputHolder.input.nextInt();
			final Position coordenates = Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt());

			final Optional<Building> possibleBuilding = buildings.stream()
					.filter(b -> b.getCoordenates().equals(coordenates)).findFirst();

			if (possibleBuilding.isPresent()) {
				log("Building already exists");
				continue;
			}

			final Building.Builder builder = new Building.Builder();
			builder.owner(owner);
			builder.type(type);
			builder.coordenates(coordenates);
			buildings.add(builder.build());
		}
	}

	private void createOrUpdateHeadQuarters(final int gold, final int income, final int opponentGold,
			final int opponentIncome) {
		if (ownHeadQuarters == null) {
			ownHeadQuarters = createHQ(true);
		}
		ownHeadQuarters.setGold(gold);
		ownHeadQuarters.setIncome(income);

		if (opponentHeadQuarters == null) {
			opponentHeadQuarters = createHQ(false);
		}
		opponentHeadQuarters.setGold(opponentGold);
		opponentHeadQuarters.setIncome(opponentGold);
	}

	/**
	 * Cria um novo HQ recebe como parametro um booleano que diz se o HQ pertence ao
	 * jogador(true) ou ao bot (false)
	 */
	private HeadQuarters createHQ(final boolean own) {
		final List<Building> builds = buildings.stream().filter(b -> b.isOwn() == own).collect(toList());

		final Optional<Building> possibleHQ = builds.stream().filter(b -> b.getType().contentEquals("HQ")).findFirst();
		if (!possibleHQ.isPresent()) {
			throw new IllegalArgumentException("Deve existir um HQ");
		}
		final Building building = possibleHQ.get();
		log("Create hq isOwn: " + own);
		log(building.getCoordenates());

		return new HeadQuarters(building.getCoordenates(), builds);
	}

	private void createOrUpdateUnits(final int unitCount) {
		for (int i = 0; i < unitCount; i++) {
			final int owner = InputHolder.input.nextInt();
			final int id = InputHolder.input.nextInt();
			final int level = InputHolder.input.nextInt();
			final Position position = Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt());
			if (owner == Rules.AlliesUnits.getValue()) {
				ownHeadQuarters.updateUnits(id, level, position);
				continue;
			}
			opponentHeadQuarters.updateUnits(id, level, position);
		}
	}
}