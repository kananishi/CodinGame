import java.util.*;
import java.io.*;
import java.math.*;

class Player {

	private static Position ash = new Position(0,0);
	private static int humanCount;
	private static int zombieCount;
	private static final List<Char> humans = new ArrayList<>(10);
	private static final List<Char> zombies = new ArrayList<>();
	
	//Constantes
	private static final int wide = 16000;
	private static final int high = 9000;
	private static final int ashStep = 1000;
	private static final int ashRage = 2000;
	private static final int zombiesStep = 400;

	// Pontuacao
	int pointGainPerKill = (int)Math.pow(humanCount, 2) * 10;

	public static void main(final String args[]) {
		while (true) {
			setParameters();
			log();
			move();
		}
	}

	private static void setParameters() {
		final Scanner in = new Scanner(System.in);
		ash.setX(in.nextInt());
		ash.setY(in.nextInt());
		humanCount = in.nextInt();
		for (int i = 0; i < humanCount; i++) {
			humans.clear();
			humans.add(new Char(in.nextInt(), in.nextInt(), in.nextInt()));
		}
		final int zombieCount = in.nextInt();
		for (int i = 0; i < zombieCount; i++) {
			final int zombieId = in.nextInt();
			final int zombieX = in.nextInt();
			final int zombieY = in.nextInt();
			final int zombieXNext = in.nextInt();
			final int zombieYNext = in.nextInt();
		}
	}

	private static void move() {
		Optional<Char> optionalNearest = humans.stream()
		.max((first, second) -> Double.compare(first.getDistanceToAsh(ash), second.getDistanceToAsh(ash)));
		
		if(optionalNearest.isPresent()){
		    final Char nearest = optionalNearest.get();
		    System.err.println(nearest.getDistanceToAsh(ash));
		// Write an action using System.out.println()
		// Your destination coordinates
		System.out.println(String.format("%d %d", nearest.getPosition().getX(), nearest.getPosition().getY()));
		}
	}

	private static void log() {
		// To debug: System.err.println("Debug messages...");
		System.err.println("Debug message...");
	}

	private static double getDistance(final Position a, final Position b) {
		final double dx = Math.pow(a.getX() - b.getX(), 2);
		final double dy= Math.pow(a.getY() - b.getY(), 2);
		return Math.sqrt(dx+dy);
	}
}

final class Char{
	private int id;
	private Position position;

	public Char(final int id, final int x, final int y) {
		this.id = id;
		position = new Position(x, y);
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
	    final double dx = Math.pow(ash.getX() - this.position.getX(), 2);
		final double dy= Math.pow(ash.getY() - this.position.getY(), 2);
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
