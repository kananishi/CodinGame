package game.map;

public final class Cell {

	private final CellType type;
	private OccupationType occupation;

	private Cell(final CellType type) {
		this.type = type;
	}

	public static Cell getCell(final String code) {
		return new Cell(CellType.getCell(code));
	}

	public CellType getType() {
		return type;
	}

	public OccupationType getOccuppation() {
		return occupation;
	}

	public void setOccupation(final OccupationType occupation) {
		this.occupation = occupation;
	}

}
