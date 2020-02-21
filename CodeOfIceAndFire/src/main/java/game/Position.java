package game;

import java.util.Objects;

public final class Position {
	private int x;
	private int y;

	public static final Position INVALID_POSITION = new Position(-1, -1);

	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public static Position create(final int x, final int y) {
		return new Position(x, y);
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Position) {
			final Position pos = (Position) obj;
			if (this == pos) {
				return true;
			}
			if (x == pos.getX() && y == pos.getY()) {
				return true;
			}
		}
		return false;

	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "(x,y) = (" + x + "," + y + ")";
	}
}