package game;
final class Human extends Character{

	private Human(final int id, final Position position) {
		super(id,position);
	}

	public static Human createHumanInCoordenates(final int id, final int x, final int y) {
		return new Human(id, new Position(x,y) );
	}

	public double getDistanceToAsh(){
		return super.getDistanceTo(Character.ash.getCurrentPosition());
	}

	public double getDistanceToNearestZombie() {
		double distanceToNearestZombie = Double.MAX_VALUE;
		for (final Zombie zombie : Game.getZombies()) {
			final double distanceToZombie = zombie.getDistanceTo(super.getCurrentPosition());
			if(distanceToZombie < distanceToNearestZombie) {
				distanceToNearestZombie = distanceToZombie;
			}
		}
		return distanceToNearestZombie;
	}

	public boolean canBeSaved() {
		return Double.compare(stepsFromAsh(),stepsFromNearesZombie()) <= 0;
	}

	private double stepsFromAsh() {
		return (getDistanceToAsh() - Rules.ASHRANGE.getValue()) / Rules.ASHSTEP.getValue() ;
	}

	private double stepsFromNearesZombie() {
		return getDistanceToNearestZombie() / Rules.ZOMBIESSTEP.getValue();
	}

	@Override
	public String toString() {
		return "Id: " + getId() + "Position: " + getCurrentPosition();
	}

}