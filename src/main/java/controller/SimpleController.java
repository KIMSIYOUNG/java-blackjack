package controller;

import java.util.List;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.rule.YesOrNo;
import view.InputView;
import view.OutputView;

public class SimpleController implements ControlPolicy {
	@Override
	public void gameStart(List<Player> players, Dealer dealer) {
		Deck deck = new Deck();
		for (Player player : players) {
			player.receiveCard(deck.drawCard());
			player.receiveCard(deck.drawCard());
		}
		dealer.receiveCard(deck.drawCard());
		dealer.receiveCard(deck.drawCard());

		OutputView.printReceivedCards(players, dealer);

		for (Player player : players) {
			while (player.canReceiveMore() && YesOrNo.of(InputView.inputReceiveMore(player)).isYes()) {
				player.receiveCard(deck.drawCard());
				OutputView.printCards(player);
			}
		}
		while (dealer.canReceiveMore()) {
			dealer.receiveCard(deck.drawCard());
			OutputView.printDealerCards(dealer);
		}
	}
}
