package game.map;

import game.Position;
import game.Rules;

public class MapManager {

	public static final MapManager MANAGER = new MapManager();

	private final Map map = new Map();

	private MapManager() {
	}

	public void updateMapOnLine(final int line, final String lineInfo) {
		for (int i = 0; i < Rules.MapWide.getValue(); i++) {
			map.setMap(Position.create(line, i), lineInfo.charAt(i));
		}
	}

	public Position getTrainArea() {
		return map.getPositionWithChar(MapCodes.ACTIVE_OWNED.getCode());
	}

	public Position getOpenSpace(final Position position) {
		return map.getOpenSpace(position);
	}

}
