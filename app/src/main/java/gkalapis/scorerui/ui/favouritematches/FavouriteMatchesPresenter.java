package gkalapis.scorerui.ui.favouritematches;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.favouritematches.FavouriteMatchesInteractor;
import gkalapis.scorerui.interactor.main.NotificationReceiver;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.Presenter;

import static android.content.Context.ALARM_SERVICE;

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

    public void removeFavouriteMatch(long id, Context context) {
        favouriteMatchesInteractor.deleteFavouriteMatch(id);
        removeNotification(context, FavouriteMatch.findById(FavouriteMatch.class, id));
    }

    public void removeNotification(Context context, FavouriteMatch favouritematch){
        if (favouritematch != null){
            Intent myIntent = new Intent(context, NotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, favouritematch.hashCode(), myIntent,0);
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
        else{
            Log.d("null object...", "null object");
        }
    }

}
