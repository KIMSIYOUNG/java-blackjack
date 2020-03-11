package controller;

import java.util.List;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayersFactory;
import domain.result.ResultPolicy;
import domain.rule.RulePolicy;
import view.InputView;

public class GameController {
	public void run(ControllerPolicy controllerPolicy, ResultPolicy resultPolicy) {

		List<Player> players = PlayersFactory.of(InputView.inputUserNames());
		Dealer dealer = new Dealer();

		controllerPolicy.gameStart(players, dealer);
		// GameResult gameResult = resultPocliy.run(Deaelr , List<Plyr>);
		// OutputView.printGameResult(gameResult);
	}
}
