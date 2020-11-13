package gkalapis.scorerui.ui.livematches;

import java.util.List;

import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.common.CommonScreen;

public interface LiveMatchesScreen extends CommonScreen {

    void showLiveMatches(List<Match> matches);

    void addMatchToFavourites(FavouriteMatch favouriteMatch);
}
