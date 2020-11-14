package gkalapis.scorerui;

import com.orm.SugarApp;

import gkalapis.scorerui.ui.UIModule;

public class ScorerUiApplication extends SugarApp {

    public static ScorerUiApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerScorerUiApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
