package gkalapis.scorerui.interactor.bets;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.main.UserCacheInteractor;
import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class BetsInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;

    @Inject
    UserCacheInteractor userCacheInteractor;

    public BetsInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getBets(Context context) {
        GetBetsEvent event = new GetBetsEvent();
        String name = userCacheInteractor.getUser(context);
        try {
            Call<List<Bet>> call = scorerAPI.listBets(name);
            Response<List<Bet>> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, response.body());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
