package game;

import java.util.ArrayList;
import java.util.List;

import game.entities.HeadQuartersManager;
import game.map.MapManager;
import game.stategy.game.GameStrategy;

public final class Game implements Output {

	public static Game GAME = new Game();
	private static int rounds = 0;

	private int numberMineSpots;
	private final List<Position> mineSpots = new ArrayList<>();

	private final MapManager mapManager = MapManager.MANAGER;
	private final HeadQuartersManager headQuartersManager = HeadQuartersManager.MANAGER;

	private Game() {
	}

	List<Position> getMineSpots() {
		return mineSpots;
	}

	void setMines() {
		numberMineSpots = InputHolder.input.nextInt();
		for (int i = 0; i < numberMineSpots; i++) {
			mineSpots.add(Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt()));
		}
	}

	void run(final GameStrategy strategy) {
		updateParameters();
		String commands = strategy.run();
		if (commands.isEmpty()) {
			commands = "WAIT;";
		}
		exec(commands + "MSG Round: " + rounds + ";");
	}

	void updateParameters() {
		final int gold = InputHolder.input.nextInt();
		final int income = InputHolder.input.nextInt();
		final int opponentGold = InputHolder.input.nextInt();
		final int opponentIncome = InputHolder.input.nextInt();

		for (int i = 0; i < MapManager.MapWide; i++) {
			mapManager.updateMapOnLine(i, InputHolder.input.next());
		}

		updateBuildings(InputHolder.input.nextInt());
		headQuartersManager.updateHeadQuarters(gold, income, opponentGold, opponentIncome);
		createOrUpdateUnits(InputHolder.input.nextInt());
		rounds++;
	}

	private void updateBuildings(final int numberOfBuildings) {
		for (int i = 0; i < numberOfBuildings; i++) {
			final int owner = InputHolder.input.nextInt();
			final int type = InputHolder.input.nextInt();
			final Position coordenates = Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt());
			headQuartersManager.createOrUpdateBuilding(owner, type, coordenates);
		}
	}

	private void createOrUpdateUnits(final int unitCount) {
		for (int i = 0; i < unitCount; i++) {
			final int owner = InputHolder.input.nextInt();
			final int id = InputHolder.input.nextInt();
			final int level = InputHolder.input.nextInt();
			final Position position = Position.create(InputHolder.input.nextInt(), InputHolder.input.nextInt());
			headQuartersManager.updateUnit(owner, id, level, position);
		}
	}

}