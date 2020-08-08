package game.map;

import java.util.List;
import java.util.Optional;

public class MapManager {

	private final Map map;

	private MapManager(final Map map) {
		this.map = map;
	}

	public static MapManager createMap(final int mapWidth, final int mapHeight) {
		return new MapManager(new Map(mapWidth, mapHeight));
	}

	public void setMap(final int line, final String lineInfo) {
		for (int i = 0; i < map.getMapWidth(); i++) {
			final String code = String.valueOf(lineInfo.charAt(i));
			final Cell cell = Cell.getCell(code);
			map.setMapPosition(Position.create(i, line), cell);
		}
	}

	public void updateMapOnPosition(final Position position, final OccupationType pellet) {
		map.updateMap(position, pellet);
	}

	public void clearMap() {
		for (int x = 0; x < map.getMapWidth(); x++) {
			for (int y = 0; y < map.getMapHeight(); y++) {
				map.updateMap(Position.create(x, y), OccupationType.EMPTY);
			}
		}
	}

	private final Position close = null;

	public Position getClosestSP(final Position position) {
		if (close != null && close.distanceTo(position) == 0) {
			return close;
		}

		final List<Position> positions = map.getPositionWithOccupationType(OccupationType.SUPER_PELLET);
		final Optional<Position> min = positions.stream().min((p1, p2) -> position.compare(p1, p2));
		return min.isPresent() ? min.get() : map.getPositionWithOccupationType(OccupationType.PELLET).get(0);
	}

}
