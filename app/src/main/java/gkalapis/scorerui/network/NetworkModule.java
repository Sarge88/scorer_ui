package gkalapis.scorerui.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String ENDPOINT_ADDRESS = "http://10.0.2.2:8080/";

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().setLenient().create());
        // return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
        // return new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create());
        return new Retrofit.Builder().addConverterFactory(gsonConverterFactory);
    }

    @Provides
    @Singleton
    public ScorerAPI provideFootballDataApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(ENDPOINT_ADDRESS).build().create(ScorerAPI.class);
    }
}