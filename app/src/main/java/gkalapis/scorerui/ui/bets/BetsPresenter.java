package gkalapis.scorerui.ui.bets;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.bets.BetsInteractor;
import gkalapis.scorerui.interactor.bets.GetBetsEvent;
import gkalapis.scorerui.ui.common.CommonPresenter;


public class BetsPresenter extends CommonPresenter<BetsScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    BetsInteractor betsInteractor;


    @Override
    public void attachScreen(BetsScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void showBets(Context context) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                betsInteractor.getBets(context);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetBetsEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.showBets(event.getItems());
            }
        }
    }

}
