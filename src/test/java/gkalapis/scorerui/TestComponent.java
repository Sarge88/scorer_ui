package gkalapis.scorerui;

import javax.inject.Singleton;

import gkalapis.scorerui.interactor.InteractorModule;
import gkalapis.scorerui.mock.MockNetworkModule;
import dagger.Component;
@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class})
public interface TestComponent extends PlScoresApplicationComponent {
}
