package gkalapis.scorerui.interactor.users;

import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.livematches.GetLiveMatchesEvent;
import gkalapis.scorerui.model.api.User;
import gkalapis.scorerui.network.FootballDataApi;
import retrofit2.Call;
import retrofit2.Response;

public class UsersInteractor extends CommonNetworkInteractor {

    @Inject
    FootballDataApi footballDataApi;

    public UsersInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getUsers() {
        GetUsersEvent event = new GetUsersEvent();

        try {
            Call <List<User>> call = footballDataApi.listUsers();
            Response <List<User>> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, response.body());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
