package game.stategy.train;

import static java.util.stream.Collectors.toList;

import java.util.List;

import game.Position;
import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
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
			final Position trainArea = closerToWichAcess(closeUnits);
			builder.append(command("train", level, trainArea));
			ownHeadQuarters.addUnitToTrain(Unit.ghostTrain(level, trainArea, HoldTheHeadQuartersStrategy.STRATEGY));
		}

		return builder.toString();
	}

	private Position closerToWichAcess(final List<Unit> units) {
		final List<Position> gateway = HeadQuartersManager.MANAGER.getOwnHeadQuarters().getAcess();
		Position closer = null;
		int closerDistance = Integer.MAX_VALUE;
		for (final Unit unit : units) {
			for (final Position access : gateway) {
				final int distance = unit.getCoordenates().distanceTo(access);
				if (closerDistance > distance) {
					closerDistance = distance;
					closer = access;
				}
			}
		}
		return closer;
	}

}
