package controller;

import java.util.List;

import domain.participant.Dealer;
import domain.participant.Player;

public interface ControllerPolicy {
	void gameStart(List<Player> players, Dealer dealer);
}
