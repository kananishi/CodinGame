package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Game{

	private Game() {
	}

	final static Scanner input = new Scanner(System.in);

	private static int humansCount;
	private static List<Human> humans = new ArrayList<>(humansCount);
	private static int zombieCount;
	private static List<Zombie> zombies = new ArrayList<>(humansCount);

	// Pontuacao
	// If several zombies are destroyed during on the same round,
	// the nth zombie killed's worth is multiplied by the (n+2)th
	// number of the Fibonnacci sequence (1, 2, 3, 5, 8, and so on).
	// As a consequence, you should kill the maximum amount of zombies
	// during a same turn.
	int pointGainPerKill = (int)Math.pow(humansCount, 2) * 10;

	protected static void run(final Strategy strategy) {
		setParameters();
		move(strategy.run());
	}

	protected static void setParameters() {
		Character.ash.getCurrentPosition().setX(input.nextInt());
		Character.ash.getCurrentPosition().setY(input.nextInt());
		humansCount = input.nextInt();
		humans.clear();
		zombies.clear();
		for (int i = 0; i < humansCount; i++) {
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
		return humansCount;
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