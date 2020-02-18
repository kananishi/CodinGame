package game;

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
		this.owned = owner;
	}

	private Building(final Builder builder) {
		coordenates = builder.coordenates;
		type = builder.type;
		owned = builder.owned;
	}

	public static class Builder {
		private boolean owned = false; // true: Owned false: Enemy
		private String type = "";
		private Position coordenates = null;

		public Builder() {
		}

		public Builder owner(final int value) {
			owned = value == 0 ? true : false;
			return this;
		}

		public Builder type(final int value) {
			type = value == 0 ? "HQ" : "";
			return this;
		}

		public Builder coordenates(final Position value) {
			coordenates = value;
			return this;
		}

		Building build() {
			return new Building(this);
		}
	}
}
