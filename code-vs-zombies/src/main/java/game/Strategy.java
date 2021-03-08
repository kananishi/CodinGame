package game;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

enum Strategy{
	SaveNearestHuman {
		@Override
		public Position run() {
			final Optional<Human> nearestHuman = Game.getHumans().stream()
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
			final Optional<Human> nearestHuman = getRescueableHumans().stream()
					.min((first, second) -> Double.compare(first.getDistanceToAsh(), second.getDistanceToAsh()));
			if(!nearestHuman.isPresent()) {
				Game.log("Save nearest human");
				return SaveNearestHuman.run();
			}
			return nearestHuman.get().getCurrentPosition();
		}
	},
	SaveHumansInDanger{
		@Override
		public Position run() {
			final List<Human> rescuableHumans = getRescueableHumans();
			rescuableHumans.sort((first,second) -> (int)(first.getDistanceToAsh() - second.getDistanceToAsh()));
			rescuableHumans.forEach(h -> Game.log(String.valueOf(h.getDistanceToAsh())));
			return rescuableHumans.get(rescuableHumans.size()-1).getCurrentPosition();
		}
	};

	public static List<Human> getRescueableHumans() {
		return Game.getHumans().stream()
				.filter(human -> human.canBeSaved())
				.collect(toList());
	}

	public abstract Position run();
}
