package gkalapis.scorerui.ui.users;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.interactor.users.GetUsersEvent;
import gkalapis.scorerui.interactor.users.UsersInteractor;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.Presenter;
import gkalapis.scorerui.ui.common.CommonPresenter;
import gkalapis.scorerui.ui.favouritematches.FavouriteMatchesScreen;

public class UsersPresenter extends CommonPresenter<UsersScreen> {


    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    UsersInteractor usersInteractor;

    @Override
    public void attachScreen(UsersScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void showUsers() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                usersInteractor.getUsers(); //interactorbol
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetUsersEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.showUsers(event.getItems());
            }
        }
    }

}