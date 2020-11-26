package gkalapis.scorerui.interactor.bets;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class BetsInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;


    public BetsInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getBets() {
        GetBetsEvent event = new GetBetsEvent();

        try {
            Call<List<Bet>> call = scorerAPI.listBets();
            Response<List<Bet>> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, response.body());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
