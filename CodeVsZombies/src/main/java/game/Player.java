package game;

class Player {

	public static void main(final String args[]) {
		while (true) {
			Game.run(Strategy.SaveOnlyWhoCanBeSaved);
		}
	}
}

