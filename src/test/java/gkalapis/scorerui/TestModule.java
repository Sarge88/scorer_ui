package gkalapis.scorerui;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import gkalapis.scorerui.di.Network;
import gkalapis.scorerui.ui.favouritematches.FavouriteMatchesPresenter;
import gkalapis.scorerui.ui.livematches.LiveMatchesPresenter;
import gkalapis.scorerui.ui.livetable.LiveTablePresenter;
import gkalapis.scorerui.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

	private Context context;

	public TestModule(Context context) {
		this.context = context;
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public MainPresenter provideMainPresenter() {
		return new MainPresenter();
	}

	@Provides
	@Singleton
	public LiveMatchesPresenter provideLiveMatchesPresenter() {
		return new LiveMatchesPresenter();
	}

	@Provides
	@Singleton
	public LiveTablePresenter provideLiveTablePresenter() {
		return new LiveTablePresenter();
	}

	@Provides
	@Singleton
	public FavouriteMatchesPresenter provideFavouriteMatchesPresenter() {
		return new FavouriteMatchesPresenter();
	}

	@Provides
	@Singleton
	@Network
	public Executor provideNetworkExecutor() {
		return new UiExecutor();
	}

}
