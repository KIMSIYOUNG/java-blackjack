package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DealerTest {
	@Test
	void constructorTest() {
		assertThat(new Dealer()).isInstanceOf(Dealer.class);
	}
}