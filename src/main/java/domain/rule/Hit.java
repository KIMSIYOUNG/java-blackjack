package domain.rule;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public class Hit implements RulePolicy {
	private static final int DEALER_HIT_POINT = 16;
	private static final int PLAYER_HIT_POINT = 20;

	private final Deck deck;

	public Hit(Deck deck) {
		this.deck = deck;
	}

	@Override
	public boolean canApply(Player player) {
		return player.calculateScore() <= PLAYER_HIT_POINT;
	}

	@Override
	public boolean canApply(Dealer dealer) {
		return dealer.calculateScore() <= DEALER_HIT_POINT;
	}

	@Override
	public void apply(Participant participant) {
		participant.receiveCard(deck.drawCard());
	}
}