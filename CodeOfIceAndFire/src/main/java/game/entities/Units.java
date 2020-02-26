package game.entities;

public enum Units {
	Level1(10, 1), Level2(20, 4), Level3(30, 201);

	private int trainCost;
	private int upKeep;

	private Units(final int trainCost, final int upKeep) {
		this.trainCost = trainCost;
		this.upKeep = upKeep;
	}

	public int getTrainCost() {
		return trainCost;
	}

	public int getUpKeep() {
		return upKeep;
	}
}
