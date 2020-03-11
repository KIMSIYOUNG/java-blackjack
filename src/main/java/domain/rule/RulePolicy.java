package domain.rule;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public interface RulePolicy {
	boolean canApply(Player player);

	boolean canApply(Dealer dealer);

	void apply(Participant participant);
}
