package game.map;

import java.util.Arrays;
import java.util.Optional;

enum CellType {
	FLOOR(" "), WALL("#");

	private final String code;

	private CellType(final String code) {
		this.code = code;
	}

	static CellType getCell(final String code) {
		final Optional<CellType> cell = Arrays.stream(CellType.values()).filter(c -> c.getCode().equalsIgnoreCase(code))
				.findFirst();
		if (!cell.isPresent()) {
			throw new IllegalArgumentException("Invalid Cell Type");
		}
		return cell.get();
	}

	public String getCode() {
		return code;
	}

}