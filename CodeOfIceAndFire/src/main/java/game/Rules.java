package game;

public enum Rules {
	IncomeForCell(1), AlliesUnits(0);

	private int value;

	private Rules(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
