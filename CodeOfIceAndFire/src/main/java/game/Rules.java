package game;

public enum Rules {
	MapWide(12),
	MapHigh(12),
	IncomeForCell(1),
	AlliesUnits(0),
	Unit1Cost(10),
	Unit1UpKeep(1);


	private int value;

	private Rules(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
