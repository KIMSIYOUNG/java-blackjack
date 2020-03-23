package domain.result;

import domain.gamer.Gamer;

public class BlackjackResult implements ResultPolicy {
	@Override
	public boolean compare(Gamer gamer, Gamer other) {
		return gamer.isBlackJack() && other.isNotBlackJack();
	}
}
