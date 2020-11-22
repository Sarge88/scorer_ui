package gkalapis.scorerui.ui.main;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.main.MainInteractor;
import gkalapis.scorerui.interactor.main.RegisterEvent;
import gkalapis.scorerui.ui.common.CommonPresenter;

public class MainPresenter extends CommonPresenter<MainScreen> {

    @Inject
    MainInteractor mainInteractor;

    @Inject
    @Network
    Executor networkExecutor;

    public void startLiveMatchesActivity() {
        screen.showLiveMatches();
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void register(final Context context, final String name, final String  password) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mainInteractor.register(context, name, password);
            }
        });


    }

    public void restore() {
    }

    public boolean isRegisterVisible(Context context) {
        return !mainInteractor.isUserExist(context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final RegisterEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.userSuccessfullyRegistered(event.getItems().get(0));
            }
        }
    }
}
