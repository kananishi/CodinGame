package game.stategy;

import game.Position;

public interface CommandStrategy {

	default String command(final String command, final int param, final Position position) {
		return String.format("%s %d %d %d;", command.toUpperCase(), param, position.getX(), position.getY());
	}

}
