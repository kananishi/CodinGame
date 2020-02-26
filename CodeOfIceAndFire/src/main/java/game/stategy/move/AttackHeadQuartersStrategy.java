package game.stategy.move;

import game.entities.HeadQuartersManager;
import game.entities.Unit;

public class AttackHeadQuartersStrategy implements Move {

	public static final Move STRATEGY = new AttackHeadQuartersStrategy();

	@Override
	public String move(final Unit unit) {
		final StringBuilder builder = new StringBuilder();
		builder.append(command(unit.getId(), HeadQuartersManager.MANAGER.getOpponentHeadQuarters().getCoordenates()));
		return builder.toString();
	}

}
