package game.stategy.train;

import static java.util.stream.Collectors.toList;

import java.util.List;

import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
import game.map.MapManager;
import game.stategy.move.HoldTheHeadQuartersStrategy;

public class DefendWhenEnemyIsCloseStrategy implements Train {

	public static final Train STRATEGY = new DefendWhenEnemyIsCloseStrategy();

	@Override
	public String train(final int level) {
		final StringBuilder builder = new StringBuilder();
		final HeadQuartersManager managerHQ = HeadQuartersManager.MANAGER;
		final HeadQuarters ownHeadQuarters = managerHQ.getOwnHeadQuarters();

		final List<Unit> closeUnits = HeadQuartersManager.MANAGER.getOpponentHeadQuarters().getArmy().parallelStream()
				.filter(u -> u.getCoordenates().distanceTo(ownHeadQuarters.getCoordenates()) == 5).collect(toList());

		if (closeUnits.isEmpty()) {
			return builder.toString();
		}

		if (ownHeadQuarters.getGold() > managerHQ.getLevelTrainCost(level)) {
			builder.append(command("train", level, MapManager.MANAGER.getTrainArea()));
			ownHeadQuarters.addUnitToTrain(
					Unit.ghostTrain(level, MapManager.MANAGER.getTrainArea(), HoldTheHeadQuartersStrategy.STRATEGY));
		}

		return builder.toString();
	}

}
