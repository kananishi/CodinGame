package game.stategy.move;

import game.entities.Unit;
import game.stategy.CommandStrategy;

public interface Move extends CommandStrategy {
	String move(Unit unit);
}
