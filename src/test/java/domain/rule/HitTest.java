package domain.rule;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.participant.Dealer;
import domain.participant.Player;

class HitTest {
	private Hit hit;

	@BeforeEach
	void setUp() {
		hit = new Hit(new Deck());
	}

	@Test
	@DisplayName("플레이어가 20이하의 경우 추가 카드를 받을 수 있는지")
	void canReceiveMore() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.SEVEN, Type.DIAMOND));
		player.receiveCard(new Card(Symbol.SEVEN, Type.CLUB));
		player.receiveCard(new Card(Symbol.SIX, Type.DIAMOND));

		assertThat(hit.canApply(player)).isTrue();
	}

	@Test
	@DisplayName("플레이어가 20초과의 경우 추가 카드를 받을 수 없는지")
	void canNotReceiveMore() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.EIGHT, Type.DIAMOND));
		player.receiveCard(new Card(Symbol.SEVEN, Type.CLUB));
		player.receiveCard(new Card(Symbol.SIX, Type.DIAMOND));

		assertThat(hit.canApply(player)).isFalse();
	}

	@Test
	@DisplayName("딜러가 16이하의 경우 추가 카드를 받을 수 있는지")
	void canReceiveToDealer() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(Symbol.FIVE, Type.DIAMOND));
		dealer.receiveCard(new Card(Symbol.SEVEN, Type.CLUB));
		dealer.receiveCard(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(hit.canApply(dealer)).isTrue();
	}

	@Test
	@DisplayName("딜러가 16초과의 경우 추가 카드를 받을 수 없는지")
	void canNotReceiveToDealer() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(Symbol.FIVE, Type.DIAMOND));
		dealer.receiveCard(new Card(Symbol.EIGHT, Type.CLUB));
		dealer.receiveCard(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(hit.canApply(dealer)).isFalse();
	}
}