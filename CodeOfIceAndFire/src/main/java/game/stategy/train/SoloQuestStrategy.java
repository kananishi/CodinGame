package game.stategy.train;

import game.Position;
import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
import game.entities.Units;
import game.map.MapManager;
import game.stategy.move.EconomyBasedStrategy;

/**
 * Treina apenas uma unidade. E envia para atacar o HQ do oponente. Pros: Melhor
 * economia, funciona para sair do wood 3
 */
public class SoloQuestStrategy implements Train {

	public static final Train STRATEGY = new SoloQuestStrategy();

	@Override
	public String train(final int level) {
		final StringBuilder builder = new StringBuilder();
		final HeadQuarters ownHeadQuarters = HeadQuartersManager.MANAGER.getOwnHeadQuarters();
		if (ownHeadQuarters.getArmy().size() == 0 && ownHeadQuarters.getGold() > Units.Level1.getTrainCost() * 2) {
			final Position trainPosition = MapManager.MANAGER.getTrainArea();
			builder.append(command(level, trainPosition));
			ownHeadQuarters.addUnitToTrain(Unit.ghostTrain(1, trainPosition, EconomyBasedStrategy.STRATEGY));
		}
		return builder.toString();
	}
}
