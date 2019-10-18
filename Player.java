import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		while (true) {
			Game.run(Strategy.SaveNearestHuman);
		}
	}
}

final class Game{

	private Game() {
	}

	//Constantes
	private static final int wide = 16000;
	private static final int high = 9000;
	private static final int ashStep = 1000;
	private static final int ashRage = 2000;
	private static final int zombiesStep = 400;

	final static Scanner input = new Scanner(System.in);

	private static int humanCount;
	private static final List<Human> humans = new ArrayList<>();
	private static int zombieCount;
	private static final List<Zombie> zombies = new ArrayList<>();

	// Pontuacao
	// If several zombies are destroyed during on the same round,
	// the nth zombie killed's worth is multiplied by the (n+2)th
	// number of the Fibonnacci sequence (1, 2, 3, 5, 8, and so on).
	// As a consequence, you should kill the maximum amount of zombies
	// during a same turn.
	int pointGainPerKill = (int)Math.pow(humanCount, 2) * 10;

	public static void run(final Strategy strategy) {
		setParameters();
		move(strategy.run());
	}

	private static void setParameters() {
		Human.ash.getPosition().setX(input.nextInt());
		Human.ash.getPosition().setY(input.nextInt());
		humanCount = input.nextInt();
		humans.clear();
		zombies.clear();
		for (int i = 0; i < humanCount; i++) {
			humans.add(Human.createHumanInCoordenates(input.nextInt(), input.nextInt(), input.nextInt()));
		}
		final int zombieCount = input.nextInt();
		for (int i = 0; i < zombieCount; i++) {
			final int zombieId = input.nextInt();
			final Position zombieCurrentPosition = new Position(input.nextInt(), input.nextInt());
			final Position zombieNextPosition = new Position(input.nextInt(),input.nextInt());
			zombies.add(Zombie.create(zombieId, zombieCurrentPosition, zombieNextPosition));
		}
	}

	private static void move(final Position destination) {
		System.out.println(String.format("%d %d", destination.getX(), destination.getY()));
	}


	public static void log( final Object message) {
		// To debug: System.err.println("Debug messages...");
		System.err.println(String.valueOf(message));
	}

	public static int getHumanCount() {
		return humanCount;
	}

	public static int getZombieCount() {
		return zombieCount;
	}

	public static List<Human> getHumans() {
		return humans;
	}

	public static List<Zombie> getZombies() {
		return zombies;
	}

}

enum Strategy{
	SaveNearestHuman {
		@Override
		public Position run() {
			final Optional<Human> nearestHuman = humans.stream()
					.min((first, second) -> Double.compare(first.getDistanceToAsh(Human.ash.getPosition()), second.getDistanceToAsh(Human.ash.getPosition())));
			if(nearestHuman.isPresent()) {
				return nearestHuman.get().getPosition();
			}
			return null;
		}
	};

	private static List<Human> humans = Game.getHumans();
	private static List<Zombie> zombies = Game.getZombies();
	public abstract Position run();
}

final class Human{
	private int id;
	private Position position;

	public static Human ash = new Human(0, new Position(0, 0));

	private Human(final int id, final Position position) {
		this.id = id;
		this.position = position;
	}
	public static Human createHumanInCoordenates(final int id, final int x, final int y) {
		return new Human(id, new Position(x,y) );
	}

	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(final Position position) {
		this.position = position;
	}

	public double getDistanceToAsh(final Position ash){
		final double dx = Math.pow(ash.getX() - position.getX(), 2);
		final double dy= Math.pow(ash.getY() - position.getY(), 2);
		return Math.sqrt(dx+dy);
	}
}

final class Zombie{
	private int id;
	private final Position currentPosition;
	private final Position nextPosition;

	private Zombie(final int id, final Position currentPosition, final Position nextPosition) {
		this.id = id;
		this.currentPosition = currentPosition;
		this.nextPosition = nextPosition;
	}

	public static Zombie create(final int id, final Position currentPosition, final Position nextPosition) {
		return new Zombie(id, currentPosition, nextPosition);
	}

	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}
	public Position getCurrentPosition() {
		return currentPosition;
	}

	public Position getNextPosition() {
		return nextPosition;
	}

	public double getDistanceTo(final Position position){
		final double dx = Math.pow(position.getX() - nextPosition.getX(), 2);
		final double dy= Math.pow(position.getY() - nextPosition.getY(), 2);
		return Math.sqrt(dx+dy);
	}
}

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
}