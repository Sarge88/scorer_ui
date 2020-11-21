package gkalapis.scorerui.interactor.users;

import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.model.api.User;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class UsersInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;

    public UsersInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void getUsers() {
        GetUsersEvent event = new GetUsersEvent();

        try {
            Call <List<User>> call = scorerAPI.listUsers();
            Response <List<User>> response = call.execute();

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, response.body());
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }
}
