package game;
final class Position{
	private int x;
	private int y;

	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public static Position create(final int x, final int y){
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
	public String toString() {
		return "(x,y) = (" + x + "," + y + ")" ;
	}
}