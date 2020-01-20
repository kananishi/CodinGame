package game;

public class Unit {

	private int id;
	private int level;
	Position coordenates;

	Unit(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Position getCoordenates() {
		return coordenates;
	}

	public void setCoordenates(Position coordenates) {
		this.coordenates = coordenates;
	}
}
