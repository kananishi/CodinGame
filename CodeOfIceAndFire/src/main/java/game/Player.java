package game;

import game.stategy.game.BasicStrategy;

class Player {

	public static void main(final String args[]) {

		Game.GAME.setMines();

		while (true) {
			Game.GAME.run(BasicStrategy.STRATEGY);
		}
	}
}
