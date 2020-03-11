package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.result.Score;
import domain.rule.RulePolicy;

public abstract class Participant {
	private final String name;
	private final List<Card> cards;

	public Participant(String name) {
		validate(name);

		this.name = name;
		this.cards = new ArrayList<>();
	}

	private void validate(String name) {
		validateNull(name);
		validateSpace(name);
	}

	private void validateNull(String name) {
		if (Objects.isNull(name)) {
			throw new NullPointerException("이름은 null이 될 수 없습니다.");
		}
	}

	private void validateSpace(String name) {
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
		}
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	public boolean canHit() {
		return Score.calculate(cards) <= getHitPoint();
	}

	public void act(RulePolicy rulePolicy) {
		rulePolicy.apply(this);
	}

	public abstract int getHitPoint();

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Participant that = (Participant)o;
		return Objects.equals(name, that.name) &&
			Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, cards);
	}
}
