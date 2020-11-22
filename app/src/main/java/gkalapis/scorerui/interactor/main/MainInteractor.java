package gkalapis.scorerui.interactor.main;

import android.content.Context;

import java.util.Arrays;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
import gkalapis.scorerui.interactor.livetable.GetLiveTableEvent;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.network.ScorerAPI;
import retrofit2.Call;
import retrofit2.Response;

public class MainInteractor extends CommonNetworkInteractor {

    @Inject
    ScorerAPI scorerAPI;

    @Inject
    UserCacheInteractor userCacheInteractor;

    public MainInteractor() {
        ScorerUiApplication.injector.inject(this);
    }

    public void register(Context context, String name, String  password) {
        RegisterEvent event = new RegisterEvent();

        try {
            Call<String> userCall = scorerAPI.createUser(name, password);
            Response<String> response = userCall.execute();

            userCacheInteractor.setUser(context, name);

            throwExceptionIfNecessary(response);
            createAndPostEvent(event, response, Arrays.asList(response.body()));
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }

    public boolean isUserExist(Context context) {
        return userCacheInteractor.getUser(context) != null;
    }

}
