package game;

interface Output {
	default void exec(final String commands) {
		System.out.println(commands);
	}

	default void log(final Object message) {
		System.err.println(String.valueOf(message));
	}
}
