package game;

public class Building {

	private boolean owner;	//0: Owned 1: Enemy
	private String type;	// 0:HQ
	private Position coordenates;
	
	private Building(Position coordenates, String type, boolean owner) {
		this.coordenates = coordenates;
		this.type = type;
		this.owner = owner;
	}
	
	public static Building create(Position coordenates, String type, boolean owner) {
		return new Building(coordenates, type, owner);
	}
	
	static class Builder{
		private boolean owner = false;	//true: Owned false: Enemy
		private String type = "";
		private Position coordenates = null;
		public Builder(){
		}
		public Builder owner(int value){
			this.owner = value == 1 ? true : false; 
			return this;
		}
		public Builder type(int value){
			this.type = value == 0 ? "HQ" : "";
			return this;
		}
		public Builder coordenates(Position value){
			this.coordenates = value;
			return this;
		}
		
		Building build() {
			return new Building(this.coordenates, this.type, this.owner);
		}
	}
	
	public boolean isOwn() {
		return owner;
	}

	public String getType() {
		return type;
	}

	public Position getCoordenates() {
		return coordenates;
	}
	
}
