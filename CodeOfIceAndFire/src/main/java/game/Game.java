package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

final class Game{

	private Game() {
	}

	final static Scanner input = new Scanner(System.in);

	private static List<Position> mineSpots = new ArrayList<>();
	private static int gold;
	private static int income;
	private static int opponentGold;
	private static int opponentIncome;
	private static char[][] map;
	private static int buildingCount;
	private static List<Building> buildings = new ArrayList<>();
	private static int unitCount;
	private static List<Unit> allies = new ArrayList<>();
	private static List<Unit> enimies = new ArrayList<>();

	protected static void run(final Strategy strategy) {
		setParameters();
		if(gold > 10) {
			train(1, Position.create(0, 1));
		}
		for(Unit unit : allies) {
			move(unit.getId(),Position.create(12, 12));
		}
	}

	protected static void setParameters() {
		int numberMineSpots = input.nextInt();
		for (int i = 0; i < numberMineSpots; i++) {
			mineSpots.add(Position.create(input.nextInt(), input.nextInt()));
		}
		
		gold = input.nextInt();
        income = input.nextInt();
        opponentGold = input.nextInt();
        opponentIncome = input.nextInt();
        
        for (int i = 0; i < Rules.MapWide.getValue(); i++) {
        	setMapOnLine(i, input.next());
        }
        
        buildingCount = input.nextInt();
        for (int i = 0; i < buildingCount; i++) {
        	Building.Builder building = new Building.Builder();
        	building.owner(input.nextInt());
        	building.type(input.nextInt());
        	building.coordenates(Position.create(input.nextInt(), input.nextInt()));
        }
        
        unitCount = input.nextInt();
        for (int i = 0; i < unitCount; i++) {
            int owner = input.nextInt();
            Unit unit = new Unit();
            unit.setId(input.nextInt());
            unit.setLevel(input.nextInt());
            unit.setCoordenates(Position.create(input.nextInt(), input.nextInt()));
            if(owner == 1) {
            	allies.add(unit);
            	return ;
            }
            enimies.add(unit);
        }
	}
	
	private static void setMapOnLine(int line, String lineInfo) {
		for(int i = 0; i < Rules.MapWide.getValue(); i++) {
			map[line][i] = lineInfo.charAt(i);
		}
	}

	private static void move(final int id, final Position destination) {
		System.out.println(String.format("MOVE %d %d %d", id, destination.getX(), destination.getY()));
	}
	
	private static void train(final int level, final Position coordenates) {
		System.out.println(String.format("TRAIN %d %d %d", level, coordenates.getX(), coordenates.getY()));
	}


	public static void log( final Object message) {
		// To debug: System.err.println("Debug messages...");
		System.err.println(String.valueOf(message));
	}
}