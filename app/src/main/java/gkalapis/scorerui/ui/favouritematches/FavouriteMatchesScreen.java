package gkalapis.scorerui.ui.favouritematches;

import java.util.List;

import gkalapis.scorerui.model.db.FavouriteMatch;

public interface FavouriteMatchesScreen {

    void showFavouriteMatches(List<FavouriteMatch> favouriteMatchList);

    void removeFromFavourites(long rowId);
}
