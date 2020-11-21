package gkalapis.scorerui.interactor.livematches;

import java.util.List;
import javax.inject.Inject;
import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class LiveMatchesInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;

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

}
