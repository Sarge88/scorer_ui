package gkalapis.scorerui.ui.favouritematches;

import java.util.ArrayList;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.Presenter;

public class FavouriteMatchesPresenter extends Presenter<FavouriteMatchesScreen> {

    @Inject
    FavouriteMatchesInteractor favouriteMatchesInteractor;

    @Override
    public void attachScreen(FavouriteMatchesScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void showFavouriteMatches() {
        screen.showFavouriteMatches(getFavouriteMatches());
    }

    public ArrayList<FavouriteMatch> getFavouriteMatches() {
        return (ArrayList<FavouriteMatch>) favouriteMatchesInteractor.getFavouriteMatches();
    }

    public void removeFavouriteMatch(long id) {
        favouriteMatchesInteractor.deleteFavouriteMatch(id);
    }

}
