package gkalapis.scorerui.interactor.livematches;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.main.UserCacheInteractor;
import gkalapis.scorerui.model.api.BetResponse;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class LiveMatchesInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;

    @Inject
    UserCacheInteractor userCacheInteractor;


    public LiveMatchesInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getLiveMatches() {
        GetLiveMatchesEvent event = new GetLiveMatchesEvent();

        try {
            Call<List<Match>> call = scorerAPI.listMatches();
            Response<List<Match>> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, response.body());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }

    public void createBet(Context context, final Integer matchId, final int homeBet, final int awayBet) {
        CreateBetEvent event = new CreateBetEvent();

        try {
            Call<BetResponse> call = scorerAPI.createBet(matchId, userCacheInteractor.getUser(context), homeBet, awayBet);;
            Response<BetResponse> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, Arrays.asList(response.body()));
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
