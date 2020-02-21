package game.map;

import game.Position;
import game.Rules;

final class Map {
	private final char[][] map = new char[Rules.MapWide.getValue()][Rules.MapHigh.getValue()];

	void setMap(final Position position, final char value) {
		map[position.getX()][position.getY()] = value;
	}

	Position getPositionWithChar(final char key) {
		for (int x = 0; x < Rules.MapWide.getValue(); x++) {
			for (int y = 0; y < Rules.MapHigh.getValue(); y++) {
				if (key == map[x][y]) {
					return Position.create(x, y);
				}
			}
		}
		return null;
	}

	Position getOpenSpace(final Position position) {
		if (map[position.getX() + 1][position.getY()] != '#') {
			return Position.create(position.getX(), position.getY() + 1);
		}
		if (map[position.getX()][position.getY() + 1] != '#') {
			return Position.create(position.getX() + 1, position.getY());
		}
		return Position.create(position.getX() + 1, position.getY() + 1);
	}
}
