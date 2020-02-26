package game.stategy.train;

import game.Position;
import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
import game.map.MapManager;
import game.stategy.move.AttackHeadQuartersStrategy;

/**
 * Treina apenas uma unidade. Melhor economia
 *
 */
public class SoloQuestStrategy implements Train {

	public static final Train STRATEGY = new SoloQuestStrategy();

	@Override
	public String train(final int level) {
		final StringBuilder builder = new StringBuilder();
		final HeadQuarters ownHeadQuarters = HeadQuartersManager.MANAGER.getOwnHeadQuarters();
		if (ownHeadQuarters.getArmy().size() == 0 && ownHeadQuarters.getGold() > 21) {
			final Position trainPosition = MapManager.MANAGER.getTrainArea();
			builder.append(command(level, trainPosition));
			ownHeadQuarters.addUnitToTrain(Unit.ghostTrain(1, trainPosition, AttackHeadQuartersStrategy.STRATEGY));
		}
		return builder.toString();
	}
}
