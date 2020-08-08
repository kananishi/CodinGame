package game;

import java.util.Scanner;

import game.map.MapManager;

/**
 * DONE : - Infra basica - Procurar Super Pellets - Location system - encontrar
 * super pellets perto
 *
 * TODO : - colisoes - descrever caminho (sentido de deslocamento - u/p, l/r)
 **/
public class Player {

	public static void main(final String args[]) {

		final Scanner input = new Scanner(System.in);

		final MapManager mapManager = generateMap(input);
		final Game game = Game.create(mapManager);

		// game loop
		while (true) {
			game.updateParameters(input);

			final String result = game.run(mapManager);

			System.out.println(result); // MOVE <pacId> <x> <y>
		}
	}

	private static MapManager generateMap(final Scanner input) {
		final int width = input.nextInt();
		final int height = input.nextInt();

		final MapManager mapManager = MapManager.createMap(width, height);
		if (input.hasNextLine()) {
			System.err.print(input.nextLine());
		}
		for (int line = 0; line < height; line++) {
			final String row = input.nextLine(); // one line of the grid: space " " is floor, pound "#" is wall
			mapManager.setMap(line, row);
		}
		return mapManager;
	}
}