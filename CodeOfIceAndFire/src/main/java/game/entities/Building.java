package game.entities;

import game.Position;

class Building {

	private final boolean owned; // 0: Owned 1: Enemy
	private final String type; // 0:HQ
	private final Position coordenates;

	public boolean isOwn() {
		return owned;
	}

	public String getType() {
		return type;
	}

	public Position getCoordenates() {
		return coordenates;
	}

	public static Building create(final Position coordenates, final String type, final boolean owner) {
		return new Building(coordenates, type, owner);
	}

	public Building(final Position coordenates, final String type, final boolean owner) {
		this.coordenates = coordenates;
		this.type = type;
		owned = owner;
	}

	Building(final BuildingBuilder builder) {
		coordenates = builder.coordenates;
		type = builder.type;
		owned = builder.owned;
	}
}
