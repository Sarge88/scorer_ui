package gkalapis.scorerui.ui.main;

import java.util.List;

import gkalapis.scorerui.ui.common.CommonScreen;

public interface MainScreen extends CommonScreen {

    void showLiveMatches();

    void userSuccessfullyRegistered(String name);

    void showRegisterError();

    void showRestoreError();
}
