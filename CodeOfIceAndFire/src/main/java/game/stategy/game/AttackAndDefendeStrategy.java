package game.stategy.game;

import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.stategy.train.DefendWhenEnemyIsCloseStrategy;
import game.stategy.train.SoloQuestStrategy;

public class AttackAndDefendeStrategy implements GameStrategy {

	public final static AttackAndDefendeStrategy STRATEGY = new AttackAndDefendeStrategy();

	@Override
	public String run() {
		final StringBuilder commands = new StringBuilder();
		final HeadQuarters ownHeadQuarters = HeadQuartersManager.MANAGER.getOwnHeadQuarters();

		// Atacar
		ownHeadQuarters.setStrategy(SoloQuestStrategy.STRATEGY);
		commands.append(ownHeadQuarters.runStrategy(1));

		// defesa
		ownHeadQuarters.setStrategy(DefendWhenEnemyIsCloseStrategy.STRATEGY);
		commands.append(ownHeadQuarters.runStrategy(2));

		// executar movimentacao
		ownHeadQuarters.getArmy().forEach(u -> commands.append(u.runStrategy()));
		return commands.toString();
	}

}
