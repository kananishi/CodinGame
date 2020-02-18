package game;

class Map {
	private final char[][] map = new char[Rules.MapWide.getValue()][Rules.MapHigh.getValue()];

	void setMapOnLine(final int line, final String lineInfo) {
		for (int i = 0; i < Rules.MapWide.getValue(); i++) {
			map[line][i] = lineInfo.charAt(i);
		}
	}

	char[][] getMap() {
		return map;
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

	Position moveToOpenSpace(final Position position) {
		if (map[position.getX() + 1][position.getY()] != '#') {
			return Position.create(position.getX(), position.getY() + 1);
		}
		if (map[position.getX()][position.getY() + 1] != '#') {
			return Position.create(position.getX() + 1, position.getY());
		}
		return Position.create(position.getX() + 1, position.getY() + 1);
	}
}
