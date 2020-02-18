package game;

class Player {

	public static void main(final String args[]) {

		Game.GAME.setMines();

		while (true) {
			Game.GAME.run();
		}
	}
}
