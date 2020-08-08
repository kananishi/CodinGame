package game.stategy.move;

import game.Position;
import game.entities.Unit;
import game.map.MapManager;

public class EconomyBasedStrategy implements Move {

	public static final Move STRATEGY = new EconomyBasedStrategy();

	@Override
	public String move(final Unit unit) {
		final Position destination = MapManager.MANAGER.getOpenSpace(unit.getCoordenates());
		System.out.println(destination);
		return command(unit.getId(), destination);
	}

}
