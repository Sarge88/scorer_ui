package gkalapis.scorerui.ui.livematches;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.livematches.CreateBetEvent;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.interactor.livematches.LiveMatchesInteractor;
import gkalapis.scorerui.interactor.main.NotificationReceiver;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.common.CommonPresenter;
import gkalapis.scorerui.ui.main.MainActivity;

import static android.content.Context.ALARM_SERVICE;

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

    public void addFavouriteMatch(FavouriteMatch favouriteMatch, Context context) {
        favouriteMatchesInteractor.createFavouriteMatch(favouriteMatch);
        setAlarm(favouriteMatch, context);
    }

    public void setAlarm(FavouriteMatch match, Context context){

        LocalDateTime dateTime = LocalDateTime.ofInstant(match.getDate().toInstant(), ZoneId.systemDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, dateTime.getYear());
        calendar.set(Calendar.MONTH, dateTime.getMonthValue());
        calendar.set(Calendar.DAY_OF_MONTH, dateTime.getDayOfMonth());
        //calendar.set(Calendar.HOUR_OF_DAY, dateTime.getHour());
        calendar.set(Calendar.HOUR_OF_DAY, dateTime.getHour()-1);
        calendar.set(Calendar.MINUTE, dateTime.getMinute());


        Intent myIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, match.hashCode(), myIntent,0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        Log.d("LiveMacthesPresenter", "Alarm has been scheduled");
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
                liveMatchesInteractor.createBet(context, matchId, homeBet, awayBet);
            }
        });
    }
}
