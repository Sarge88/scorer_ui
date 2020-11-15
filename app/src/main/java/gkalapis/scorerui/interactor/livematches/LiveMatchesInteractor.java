package gkalapis.scorerui.interactor.livematches;

import javax.inject.Inject;

import gkalapis.scorerui.PlScoresApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.model.api.MatchWrapper;
import gkalapis.scorerui.network.FootballDataApi;
import retrofit2.Call;
import retrofit2.Response;

public class LiveMatchesInteractor extends CommonNetworkInteractor {

    @Inject
    FootballDataApi footballDataApi;

    public LiveMatchesInteractor() {
        PlScoresApplication.injector.inject(this);
    }

    public void getLiveMatches() {
        GetLiveMatchesEvent event = new GetLiveMatchesEvent();

        try {
            Call<MatchWrapper> call = footballDataApi.listMatches();
            Response<MatchWrapper> response = call.execute();

            throwExceptionIfNecessary(response);
            creaateAndPostEvent(event, response, response.body().getMatches());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }

}
