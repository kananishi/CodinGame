package game;

enum Strategy {
	Default {
		@Override
		public String run() {
			final StringBuilder builder = new StringBuilder();
			if (Game.GAME.getOwnHeadQuarters().getGold() > 21) {
				builder.append(train(1, Position.create(0, 1)));
			}
			Game.GAME.log(Game.GAME.getOwnHeadQuarters().getGold());
			for (final Unit unit : Game.GAME.getOwnHeadQuarters().getUnits()) {
				builder.append(unit.move(Game.GAME.getMap().moveToOpenSpace(unit.getCoordenates())));
			}
			if (builder.toString().isEmpty()) {
				return "WAIT;";
			}
			return builder.toString();
		}
	},
	TrainOneAndMoveToOpponentHQ {
		@Override
		public String run() {
			final StringBuilder builder = new StringBuilder();
			if (Game.GAME.getOwnHeadQuarters().getUnits().size() == 0
					&& Game.GAME.getOwnHeadQuarters().getGold() > 21) {
				final Position hq = Game.GAME.getOwnHeadQuarters().getCoordenates();
				Position trainPosition = getTrainArea();
				if (trainPosition.equals(hq)) {
					trainPosition = Position.create(hq.getX(), hq.getY() + 1);
				}
				builder.append(train(1, trainPosition));
			}

			for (final Unit unit : Game.GAME.getOwnHeadQuarters().getUnits()) {
				builder.append(unit.move(Game.GAME.getOpponentHeadQuarters().getCoordenates()));
			}

			if (builder.toString().isEmpty()) {
				return "WAIT;MSG TEST;";
			}
			return builder.toString();
		}
	};

	public abstract String run();

	private static String train(final int lvl, final Position coordenates) {
		return String.format("TRAIN %d %d %d;", lvl, coordenates.getX(), coordenates.getY());
	}

	private static Position getTrainArea() {
		return Game.GAME.getMap().getPositionWithChar('O');
	}

}
