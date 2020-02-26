package game.stategy.train;

import game.Position;
import game.stategy.CommandStrategy;

public interface Train extends CommandStrategy {
	String train(final int level);

	default String command(final int param, final Position position) {
		return CommandStrategy.super.command("Train", param, position);
	}
}
