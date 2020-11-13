package gkalapis.scorerui.interactor.livetable;

import javax.inject.Inject;

import gkalapis.scorerui.PlScoresApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.network.FootballDataApi;
import retrofit2.Call;
import retrofit2.Response;

public class LiveTableInteractor extends CommonNetworkInteractor {

    @Inject
    FootballDataApi footballDataApi;

    public LiveTableInteractor() {
        PlScoresApplication.injector.inject(this);
    }

    public void getLiveTable() {
        GetLiveTableEvent event = new GetLiveTableEvent();

        try {
            Call<Table> tableCall = footballDataApi.getLeagueTable();
            Response<Table> response = tableCall.execute();

            throwExceptionIfNecessary(response);
            creaateAndPostEvent(event, response, response.body().getStanding());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}