package gkalapis.scorerui.interactor.users;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.model.api.UserWrapper;
import gkalapis.scorerui.network.FootballDataApi;
import retrofit2.Call;
import retrofit2.Response;

public class UsersInteractor extends CommonNetworkInteractor {

    @Inject
    FootballDataApi footballDataApi;

    public UsersInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getLiveMatches() {
        GetLiveMatchesEvent event = new GetLiveMatchesEvent();

        try {
            Call<UserWrapper> call = footballDataApi.listUsers();
            Response<UserWrapper> response = call.execute();

            throwExceptionIfNecessary(response);
            //createAndPostEvent(event, response, response.body().getUsers());   TODO
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
