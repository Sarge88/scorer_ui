package gkalapis.scorerui.ui;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.ui.favouritematches.FavouriteMatchesPresenter;
import gkalapis.scorerui.ui.livematches.LiveMatchesPresenter;
import gkalapis.scorerui.ui.livetable.LiveTablePresenter;
import gkalapis.scorerui.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;
import gkalapis.scorerui.ui.users.UsersPresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public LiveMatchesPresenter provideLiveMatchesPresenter() {
        return new LiveMatchesPresenter();
    }

    @Provides
    @Singleton
    public LiveTablePresenter provideLiveTablePresenter() {
        return new LiveTablePresenter();
    }

    @Provides
    @Singleton
    public FavouriteMatchesPresenter provideFavouriteMatchesPresenter() {
        return new FavouriteMatchesPresenter();
    }

    @Provides
    @Singleton
    public UsersPresenter provideUsersPresenter() {
        return new UsersPresenter();
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
