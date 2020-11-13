package gkalapis.scorerui.ui.livetable;

import java.util.List;

import gkalapis.scorerui.model.api.Team;
import gkalapis.scorerui.ui.common.CommonScreen;

public interface LiveTableScreen extends CommonScreen {

    void showLiveTable(List<Team> teams);
}
