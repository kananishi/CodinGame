package game;
final class Zombie extends Character{
	private final Position nextPosition;

	private Zombie(final int id, final Position currentPosition, final Position nextPosition) {
		super(id, currentPosition);
		this.nextPosition = nextPosition;
	}

	public static Zombie create(final int id, final Position currentPosition, final Position nextPosition) {
		return new Zombie(id, currentPosition, nextPosition);
	}

	public Position getNextPosition() {
		return nextPosition;
	}

	protected double getDistanceToNextPosition() {
		return super.getDistanceTo(nextPosition);
	}

}