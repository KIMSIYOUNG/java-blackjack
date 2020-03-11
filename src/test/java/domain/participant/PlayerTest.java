package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.rule.Hit;

class PlayerTest {
	@Test
	@DisplayName("입력받은 문자열이 null인지 검증")
	void validateNull() {
		assertThatThrownBy(() -> {
			new Player(null);
		}).isInstanceOf(NullPointerException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "  "})
	@DisplayName("입력받은 문자열이 공백인지 검증")
	void validateSpace(String name) {
		assertThatThrownBy(() -> {
			new Player(name);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("플레이어가 지급받은 카드를 갖고 있는지 확인")
	void receiveCard() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.ACE, Type.CLUB));

		assertThat(player.getCards()).contains(new Card(Symbol.ACE, Type.CLUB));
	}

	@Test
	@DisplayName("계산결과가 정확한지 테스트")
	void calculateScoreTest() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.EIGHT, Type.DIAMOND));
		player.receiveCard(new Card(Symbol.SEVEN, Type.CLUB));
		player.receiveCard(new Card(Symbol.SIX, Type.DIAMOND));

		assertThat(player.calculateScore()).isEqualTo(21);
	}

	@Test
	@DisplayName("한장을 추가적으로 지급하는지")
	void applyTest() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(Symbol.FIVE, Type.DIAMOND));
		dealer.receiveCard(new Card(Symbol.FOUR, Type.DIAMOND));
		dealer.act(new Hit(new Deck()));

		assertThat(dealer.getCards()).hasSize(3);
	}
}