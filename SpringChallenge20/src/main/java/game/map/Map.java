package game.map;

import java.util.ArrayList;
import java.util.List;

final class Map {

	private final int mapWidth;
	private final int mapHeight;

	// top left corner is (x=0, y=0)
	private final Cell[][] map;

	public Map(final int width, final int height) {
		mapWidth = width;
		mapHeight = height;
		map = new Cell[mapWidth][mapHeight];
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	void setMapPosition(final Position position, final Cell cell) {
		map[position.getX()][position.getY()] = cell;
	}

	void updateMap(final Position position, final OccupationType occupation) {
		final Cell cell = map[position.getX()][position.getY()];
		cell.setOccupation(occupation);
	}

	List<Position> getPositionWithOccupationType(final OccupationType occupation) {
		final ArrayList<Position> positions = new ArrayList<Position>();
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				if (map[x][y].getOccuppation().equals(occupation)) {
					positions.add(Position.create(x, y));
				}
			}
		}
		return positions;
	}
}
