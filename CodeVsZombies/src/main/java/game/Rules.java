package game;

enum Rules {
	WIDE( 16000),
	HIGH( 9000),
	ASHSTEP(1000),
	ASHRAGE( 2000),
	ZOMBIESSTEP(400);

	private int value;

	private Rules(final int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}


}
