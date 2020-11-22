package gkalapis.scorerui.ui.livematches;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.livematches.CreateBetEvent;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.interactor.livematches.LiveMatchesInteractor;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.common.CommonPresenter;

public class LiveMatchesPresenter extends CommonPresenter<LiveMatchesScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    LiveMatchesInteractor liveMatchesInteractor;

    @Inject
    FavouriteMatchesInteractor favouriteMatchesInteractor;

    @Override
    public void attachScreen(LiveMatchesScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void refreshMatches() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                liveMatchesInteractor.getLiveMatches();
            }
        });
    }

    public void addFavouriteMatch(FavouriteMatch favouriteMatch) {
        favouriteMatchesInteractor.createFavouriteMatch(favouriteMatch);
    }

    public List<FavouriteMatch> getFavouriteMatches() {
        return favouriteMatchesInteractor.getFavouriteMatches();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetLiveMatchesEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.showLiveMatches(event.getItems());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final CreateBetEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.betCreatedSuccessfully(event.getItems().get(0).toString());
            }
        }
    }

    public void createBet(final Context context, final Integer matchId, final int homeBet, final int awayBet) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                liveMatchesInteractor.createBet(context, matchId,homeBet,awayBet);
            }
        });
    }
}
