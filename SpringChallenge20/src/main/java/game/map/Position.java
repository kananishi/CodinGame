package game.map;

import java.util.Comparator;
import java.util.Objects;

public final class Position implements Comparator<Position> {

	private int x;
	private int y;

	private Position north;
	private Position south;
	private Position east;
	private Position west;

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

	public boolean isValid() {
		return x >= 0 && x < 12 && y >= 0 && x < 12;
	}

	public int distanceTo(final Position position) {
		return Math.abs(x - position.getX()) + Math.abs(y - position.getY());
	}

	public Position getNorthPosition() {
		if (north == null) {
			north = Position.create(x, y + 1);
		}
		return north;
	}

	public Position getSouthPosition() {
		if (south == null) {
			south = Position.create(x, y - 1);
		}
		return south;
	}

	public Position getEastPosition() {
		if (east == null) {
			east = Position.create(x + 1, y);
		}
		return east;
	}

	public Position getWestPosition() {
		if (west == null) {
			west = Position.create(x - 1, y);
		}
		return west;
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

	@Override
	public int compare(final Position p1, final Position p2) {
		return distanceTo(p1) - distanceTo(p2);
	}
}