package game;

class Unit implements Move {

	private final int id;
	private int level;
	Position coordenates;

	public Unit(final int id, final int level, final Position coordenates) {
		this.id = id;
		this.level = level;
		this.coordenates = coordenates;
	}

	public int getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public Position getCoordenates() {
		return coordenates;
	}

	public void setCoordenates(final Position coordenates) {
		this.coordenates = coordenates;
	}

	@Override
	public String move(final Position destination) {
		return String.format("MOVE %d %d %d;", id, destination.getX(), destination.getY());
	}

}
