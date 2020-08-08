package game.map;

import java.util.Arrays;
import java.util.Optional;

public enum OccupationType {
	WALL("#", -1), EMPTY(" ", 0), PELLET("P", 1), SUPER_PELLET("SP", 10);

	private final String code;
	private final int value;

	private OccupationType(final String code, final int value) {
		this.code = code;
		this.value = value;
	}

	public static OccupationType getPelletType(final int value) {
		final Optional<OccupationType> pellet = Arrays.stream(OccupationType.values()).filter(p -> p.getValue() == value)
				.findFirst();
		if (!pellet.isPresent()) {
			throw new IllegalArgumentException("Invalid pallet value");
		}
		return pellet.get();
	}

	public String getCode() {
		return code;
	}

	public Integer getValue() {
		return value;
	}

}
