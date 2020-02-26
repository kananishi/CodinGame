package game.stategy.move;

import game.entities.HeadQuartersManager;
import game.entities.Unit;

public class HoldTheHeadQuartersStrategy implements Move {

	public static final HoldTheHeadQuartersStrategy STRATEGY = new HoldTheHeadQuartersStrategy();

	@Override
	public String move(final Unit unit) {
		return command(unit.getId(), HeadQuartersManager.MANAGER.getOwnHeadQuarters().getCoordenates());
	}

}
