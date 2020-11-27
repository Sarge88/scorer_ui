package gkalapis.scorerui.interactor.main;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.interactor.common.CommonNetworkInteractor;
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

        Encoder encoder = Base64.getEncoder();
        String encodedPassword = encoder.encodeToString(password.getBytes());
        Log.d("mainInteractor", "HashedPassword: "+encodedPassword);

        try {
            Call<String> userCall = scorerAPI.createUser(name, encodedPassword);
            Response<String> response = userCall.execute();

            userCacheInteractor.setUser(context, name);

            throwExceptionIfNecessary(response); // ha valami rossz akk nem megy el
            createAndPostEvent(event, response, Arrays.asList(response.body())); //event elküldésre kerül
        } catch (Exception e) {
            createAndPostErrorEvent(event, e);
        }
    }

    public void restore() {

    }

    public boolean isUserExist(Context context) {
        return userCacheInteractor.getUser(context) != null;
    }

    public String getUser(Context context) {
        return userCacheInteractor.getUser(context);
    }

}
