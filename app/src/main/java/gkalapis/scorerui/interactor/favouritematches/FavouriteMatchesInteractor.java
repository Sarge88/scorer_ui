package gkalapis.scorerui.interactor.favouritematches;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.model.db.FavouriteMatch;

public class FavouriteMatchesInteractor {

    public FavouriteMatchesInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public List<FavouriteMatch> getFavouriteMatches() {
        return  Lists.newArrayList( FavouriteMatch.findAll(FavouriteMatch.class));
    }

    public void createFavouriteMatch(FavouriteMatch favouriteMatch) {
        favouriteMatch.save();
    }

    public void deleteFavouriteMatch(long id) {
        FavouriteMatch favouriteMatch = FavouriteMatch.findById(FavouriteMatch.class, id);
        favouriteMatch.delete();
    }

}
