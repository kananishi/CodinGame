package game.pac;

import game.map.Position;

public class PacMan {

	private final int id;
	private final boolean mine;
	private Position position;
	private final String typeId;
	private int speedTurnsLeft;
	private int abilityCooldown;

	private PacMan(final int id, final boolean mine, final Position position, final String typeId) {
		this.id = id;
		this.mine = mine;
		this.position = position;
		this.typeId = typeId;
	}

	public static PacMan create(final int id, final boolean mine, final Position position, final String typeId) {
		return new PacMan(id, mine, position, typeId);
	}

	public int getId() {
		return id;
	}

	public boolean isMine() {
		return mine;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	public String getTypeId() {
		return typeId;
	}

	public int getSpeedTurnsLeft() {
		return speedTurnsLeft;
	}

	public int getAbilityCooldown() {
		return abilityCooldown;
	}

	public String move(final Position position) {
		return "MOVE " + getId() + " " + position.getX() + " " + position.getY();

	}

}
