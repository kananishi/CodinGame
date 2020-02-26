package game.stategy.train;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import game.Position;
import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
import game.map.MapManager;

public class TrainToDefendStrategy implements Train {

	@Override
	public String train(final int level) {
		final StringBuilder builder = new StringBuilder();
		final HeadQuarters ownHeadQuarters = HeadQuartersManager.MANAGER.getOwnHeadQuarters();
		final List<Unit> closeUnits = HeadQuartersManager.MANAGER.getOpponentHeadQuarters().getArmy().parallelStream()
				.filter(u -> u.getCoordenates().distanceTo(ownHeadQuarters.getCoordenates()) == 2).collect(toList());

		if (closeUnits.isEmpty()) {
			return builder.toString();
		}

		final List<Unit> unitsToTrain = new ArrayList<>();
		for (final Unit unit : closeUnits) {
			final Position coordenates = MapManager.MANAGER.getTrainArea();
			unitsToTrain.add(new Unit(0, unit.getLevel() + 1, coordenates));
		}

		int spends = 0;
		for (final Unit unit : unitsToTrain) {
			spends += HeadQuartersManager.MANAGER.getLevelTrainCost(unit.getLevel());
		}

		if (ownHeadQuarters.getGold() > spends) {
			unitsToTrain.parallelStream()
					.forEach(u -> builder.append(command("train", u.getLevel(), u.getCoordenates())));
		}

		return builder.toString();
	}
}
