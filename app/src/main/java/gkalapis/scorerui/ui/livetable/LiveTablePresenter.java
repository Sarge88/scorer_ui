package gkalapis.scorerui.ui.livetable;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.livetable.GetLiveTableEvent;
import gkalapis.scorerui.interactor.livetable.LiveTableInteractor;
import gkalapis.scorerui.ui.common.CommonPresenter;

public class LiveTablePresenter extends CommonPresenter<LiveTableScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    LiveTableInteractor liveTableInteractor;

    @Override
    public void attachScreen(LiveTableScreen screen) {
        super.attachScreen(screen);
        ScorerUiApplication.injector.inject(this);
    }

    public void refreshTable() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                liveTableInteractor.getLiveTable();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetLiveTableEvent event) {
        if (event.getThrowable() != null) {
            handleNetworkError(event);
        } else {
            if (screen != null) {
                screen.showLiveTable(event.getItems());
            }
        }
    }


}
