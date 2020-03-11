package domain.rule;

import domain.participant.Participant;

public interface RulePolicy {
	void apply(Participant participant);
}
