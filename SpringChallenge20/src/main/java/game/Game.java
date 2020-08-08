package game;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import game.map.MapManager;
import game.map.OccupationType;
import game.map.Position;
import game.pac.PacMan;

final class Game {

	private int myScore;
	private int opponentScore;

	private final MapManager mapManager;

	private int visiblePacCount;
	List<PacMan> pacs = new ArrayList<>();

	private int visiblePelletCount;

	private Game(final MapManager mapManager) {
		this.mapManager = mapManager;
	}

	static Game create(final MapManager mapManager) {
		return new Game(mapManager);
	}

	void updateParameters(final Scanner input) {
		myScore = input.nextInt();
		opponentScore = input.nextInt();
		visiblePacCount = input.nextInt(); // all your pacs and enemy pacs in sight
		setPacs(input);
		visiblePelletCount = input.nextInt(); // all pellets in sight
		setPellets(input);
	}

	private void setPacs(final Scanner input) {
		for (int i = 0; i < visiblePacCount; i++) {
			final int pacId = input.nextInt(); // pac number (unique within a team)
			final boolean mine = input.nextInt() != 0; // true if this pac is yours
			final Position position = new Position(input.nextInt(), input.nextInt());
			final String typeId = input.next(); // unused in wood leagues
			final int speedTurnsLeft = input.nextInt(); // unused in wood leagues
			final int abilityCooldown = input.nextInt(); // unused in wood leagues

			final Optional<PacMan> possiblePac = pacs.stream().filter(p -> p.getId() == pacId && p.isMine() == mine)
					.findFirst();
			if (!possiblePac.isPresent()) {
				pacs.add(PacMan.create(pacId, mine, position, typeId));
				continue;
			}

			final PacMan pac = possiblePac.get();
			pac.setPosition(position);
		}
	}

	private void setPellets(final Scanner input) {

		// Resetar posicao dos pellets
		mapManager.clearMap();

		for (int i = 0; i < visiblePelletCount; i++) {
			final Position position = new Position(input.nextInt(), input.nextInt());
			final int value = input.nextInt();
			mapManager.updateMapOnPosition(position, OccupationType.getPelletType(value));
		}
	}

	String run(final MapManager mapManager) {
		final List<PacMan> minePacs = pacs.stream().filter(p -> p.isMine()).collect(toList());
		final List<String> moves = minePacs.stream()
				.map(p -> p.move(mapManager.getClosestSP(p.getPosition())))
				.collect(toList());
		return String.join(" | ", moves);
	}

}
