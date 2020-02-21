package game.stategy.train;

import game.Position;
import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.map.MapManager;

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
		if (ownHeadQuarters.getUnits().size() == 0 && ownHeadQuarters.getGold() > 21) {
			final Position hq = ownHeadQuarters.getCoordenates();
			Position trainPosition = MapManager.MANAGER.getTrainArea();
			if (trainPosition.equals(hq)) {
				trainPosition = Position.create(hq.getX(), hq.getY() + 1);
			}
			builder.append(command("train", level, trainPosition));
		}

		return builder.toString();
	}
}
