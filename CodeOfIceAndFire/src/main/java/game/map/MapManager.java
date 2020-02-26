package game.map;

import game.Position;
import game.entities.HeadQuartersManager;

public class MapManager {

	public static final int MapWide = 12;
	public static final int MapHigh = 12;

	public static final MapManager MANAGER = new MapManager();

	private final Map map = new Map();

	private MapManager() {
	}

	public void updateMapOnLine(final int line, final String lineInfo) {
		for (int i = 0; i < MapManager.MapWide; i++) {
			map.setMap(Position.create(line, i), lineInfo.charAt(i));
		}
	}

	public Position getTrainArea() {
		Position trainPosition = map.getPositionWithChar(MapCodes.ACTIVE_OWNED.getCode());
		final HeadQuartersManager managerHQ = HeadQuartersManager.MANAGER;
		if (managerHQ.isHeadQuarters(trainPosition)) {
			trainPosition = managerHQ.getOwnHeadQuarters().getAcess().stream().findFirst()
					.orElse(Position.INVALID_POSITION);
		}
		return trainPosition;
	}

	public Position getTrainAreaNearHQ() {
		final Position trainCoordenates = map.getPositionWithChar(MapCodes.ACTIVE_OWNED.getCode());
		return trainCoordenates;
	}

	public Position getOpenSpace(final Position position) {
		return map.getOpenSpace(position);
	}

}
