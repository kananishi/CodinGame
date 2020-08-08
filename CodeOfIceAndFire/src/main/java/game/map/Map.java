package game.map;

import game.Position;

final class Map {
	private final char[][] map = new char[MapManager.MapWide][MapManager.MapHigh];

	void setMap(final Position position, final char value) {
		map[position.getX()][position.getY()] = value;
	}

	Position getPositionWithChar(final char key) {
		for (int x = 0; x < MapManager.MapWide; x++) {
			for (int y = 0; y < MapManager.MapHigh; y++) {
				if (key == map[x][y]) {
					return Position.create(x, y);
				}
			}
		}
		return null;
	}

	Position getOpenSpaceFromSpace(final Position position) {
		if (map[position.getX() + 1][position.getY()] != '#') {
			return Position.create(position.getX(), position.getY() + 1);
		}
		if (map[position.getX()][position.getY() + 1] != '#') {
			return Position.create(position.getX() + 1, position.getY());
		}
		return Position.create(position.getX() + 1, position.getY() + 1);
	}
}
