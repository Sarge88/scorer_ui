package gkalapis.scorerui.mock;

import javax.inject.Singleton;

import gkalapis.scorerui.network.FootballDataApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MockNetworkModule {

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());

    }

    @Provides
    @Singleton
    public FootballDataApi provideFootballDataApi(Retrofit.Builder retrofitBuilder) {
        return new MockFootballDataApi();
    }

}
