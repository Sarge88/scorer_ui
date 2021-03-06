package gkalapis.scorerui.interactor;

import gkalapis.scorerui.interactor.bets.BetsInteractor;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.livematches.LiveMatchesInteractor;
import gkalapis.scorerui.interactor.livetable.LiveTableInteractor;
import dagger.Module;
import dagger.Provides;
import gkalapis.scorerui.interactor.main.MainInteractor;
import gkalapis.scorerui.interactor.main.UserCacheInteractor;
import gkalapis.scorerui.interactor.users.UsersInteractor;

@Module
public class InteractorModule {

    public static final long COMPETITION_ID = 445L;
    public static final String FILTER_FOR_NEXT_7_DAYS = "n7";

    @Provides
    public LiveMatchesInteractor provideLiveMatchesInteractor() {
        return new LiveMatchesInteractor();
    }

    @Provides
    public LiveTableInteractor provideLiveTableInteractor() {
        return new LiveTableInteractor();
    }

    @Provides
    public FavouriteMatchesInteractor provideFavouriteMatchesInteractor() { return new FavouriteMatchesInteractor(); }

    @Provides
    public UsersInteractor provideUsersInteractor() {
        return new UsersInteractor();
    }

    @Provides
    public UserCacheInteractor userCacheInteractor() { return new UserCacheInteractor(); }

    @Provides
    public BetsInteractor betsInteractor() { return new BetsInteractor(); }

    @Provides
    public MainInteractor MainInteractor() { return new MainInteractor(); }
}
