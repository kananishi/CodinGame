package game;

class Character {
	private final int id;
	private final Position currentPosition;

	public static Character ash = new Character(0, new Position(0, 0));

	public Character(final int id, final Position currentPosition){
		this.id = id;
		this.currentPosition = currentPosition;
	}

	public int getId() {
		return id;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public double getDistanceTo(final Position position){
		final double dx = Math.pow(position.getX() - currentPosition.getX(), 2);
		final double dy= Math.pow(position.getY() - currentPosition.getY(), 2);
		return Math.sqrt(dx+dy);
	}
}
