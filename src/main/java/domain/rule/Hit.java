package domain.rule;

import domain.card.Deck;
import domain.participant.Participant;

public class Hit implements RulePolicy {
	private final Deck deck;

	public Hit(Deck deck) {
		this.deck = deck;
	}

	@Override
	public void apply(Participant participant) {
		participant.receiveCard(deck.drawCard());
	}
}