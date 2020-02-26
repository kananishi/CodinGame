package game.stategy.move;

import game.Position;
import game.entities.Unit;
import game.stategy.CommandStrategy;

public interface Move extends CommandStrategy {
	String move(Unit unit);

	default String command(final int param, final Position position) {
		return CommandStrategy.super.command("Move", param, position);
	}
}
