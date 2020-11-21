package gkalapis.scorerui;

import javax.inject.Singleton;

import gkalapis.scorerui.interactor.InteractorModule;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.livematches.LiveMatchesInteractor;
import gkalapis.scorerui.interactor.livetable.LiveTableInteractor;
import gkalapis.scorerui.interactor.main.MainInteractor;
import gkalapis.scorerui.interactor.users.UsersInteractor;
import gkalapis.scorerui.network.NetworkModule;
import gkalapis.scorerui.ui.UIModule;
import gkalapis.scorerui.ui.favouritematches.FavouriteMatchesActivity;
import gkalapis.scorerui.ui.favouritematches.FavouriteMatchesPresenter;
import gkalapis.scorerui.ui.livematches.LiveMatchesActivity;
import gkalapis.scorerui.ui.livematches.LiveMatchesPresenter;
import gkalapis.scorerui.ui.livetable.LiveTableActivity;
import gkalapis.scorerui.ui.livetable.LiveTablePresenter;
import gkalapis.scorerui.ui.main.MainActivity;
import gkalapis.scorerui.ui.main.MainPresenter;
import dagger.Component;
import gkalapis.scorerui.ui.users.UsersActivity;
import gkalapis.scorerui.ui.users.UsersPresenter;

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface ScorerUiApplicationComponent {


    void inject(MainActivity mainActivity);
    void inject(MainInteractor mainInteractor);
    void inject(MainPresenter mainPresenter);

    void inject(LiveMatchesActivity liveMatchesActivity);
    void inject(LiveMatchesInteractor liveMatchesInteractor);
    void inject(LiveMatchesPresenter liveMatchesPresenter);

    void inject(LiveTableActivity liveTableActivity);
    void inject(LiveTableInteractor liveTableInteractor);
    void inject(LiveTablePresenter liveTablePresenter);

    void inject(FavouriteMatchesActivity favouriteMatchesActivity);
    void inject(FavouriteMatchesInteractor favouriteMatchesInteractor);
    void inject(FavouriteMatchesPresenter favouriteMatchesPresenter);

    void inject(UsersActivity usersActivity);
    void inject(UsersInteractor usersInteractor);
    void inject(UsersPresenter usersPresenter);
}
