package gkalapis.scorerui.ui.bets;

import java.util.List;

import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.ui.common.CommonScreen;

public interface BetsScreen extends CommonScreen {

    void showBets(List<Bet> bets);
}
