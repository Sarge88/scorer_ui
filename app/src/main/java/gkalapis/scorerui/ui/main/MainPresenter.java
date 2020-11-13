package gkalapis.scorerui.ui.main;

import gkalapis.scorerui.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    public void startLiveMatchesActivity() {
        screen.showLiveMatches();
    }
}
