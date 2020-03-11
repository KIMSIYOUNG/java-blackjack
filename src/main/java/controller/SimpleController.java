package controller;

import java.util.List;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.rule.RulePolicy;
import domain.rule.Hit;
import domain.rule.YesOrNo;
import view.InputView;
import view.OutputView;

public class SimpleController implements ControllerPolicy {

	@Override
	public void gameStart(List<Player> players, Dealer dealer) {
		Deck deck = new Deck();
		Hit hit = new Hit(deck);
		for (Player player : players) {
			player.act(hit);
			player.act(hit);
		}
		dealer.receiveCard(deck.drawCard());
		dealer.receiveCard(deck.drawCard());

		OutputView.printReceivedCards(players, dealer);

		for (Player player : players) {
			while (player.canHit() && YesOrNo.of(InputView.inputReceiveMore(player)).isYes()) {
				player.act(hit);
				OutputView.printCards(player);
			}
		}
		while (dealer.canHit()) {
			dealer.act(hit);
			OutputView.printDealerCards(dealer);
		}
	}
}
