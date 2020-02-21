package game.entities;

import game.Position;

public class BuildingBuilder {
	boolean owned = false; // true: Owned false: Enemy
	String type = "";
	Position coordenates = null;

	public BuildingBuilder() {
	}

	public BuildingBuilder owner(final int value) {
		owned = value == 0 ? true : false;
		return this;
	}

	public BuildingBuilder type(final int value) {
		type = value == 0 ? "HQ" : "";
		return this;
	}

	public BuildingBuilder coordenates(final Position value) {
		coordenates = value;
		return this;
	}

	Building build() {
		return new Building(this);
	}
}
