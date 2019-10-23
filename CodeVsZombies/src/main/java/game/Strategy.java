package game;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

enum Strategy{
	SaveNearestHuman {
		@Override
		public Position run() {
			final Optional<Human> nearestHuman = humans.stream()
					.min((first, second) -> Double.compare(first.getDistanceToAsh(),second.getDistanceToAsh()));
			if(nearestHuman.isPresent()) {
				return nearestHuman.get().getCurrentPosition();
			}
			return null;
		}
	},
	SaveOnlyWhoCanBeSaved{
		@Override
		public Position run() {
			final Optional<Human> nearestHuman = humans.stream()
					.filter(human -> human.canBeSaved())
					.collect(toList())
					.stream()
					.min((first, second) -> Double.compare(first.getDistanceToAsh(), second.getDistanceToAsh()));
			if(nearestHuman.isPresent()) {
				return nearestHuman.get().getCurrentPosition();
			}
			return null;
		}
	}
	;

	private static List<Human> humans = Game.getHumans();

	public abstract Position run();

}
