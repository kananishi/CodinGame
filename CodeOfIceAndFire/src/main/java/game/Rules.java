package game;

public enum Rules {
	MapWide(12),
	MapHigh(12),
	IncomeForCell(1),
	Unit1Cost(10),
	Unit1Income(1);
	

	private int value;

	private Rules(final int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}
}
