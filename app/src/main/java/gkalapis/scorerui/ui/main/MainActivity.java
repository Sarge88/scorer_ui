package gkalapis.scorerui.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.R;
import gkalapis.scorerui.ui.livematches.LiveMatchesActivity;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class MainActivity extends DrawerActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_main, R.string.welcome);

        ScorerUiApplication.injector.inject(this);

        Button btnShowLiveMatches = (Button) findViewById(R.id.btnShowLiveMatches);
        btnShowLiveMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.startLiveMatchesActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showLiveMatches() {
        Intent intent = new Intent(MainActivity.this, LiveMatchesActivity.class);
        startActivity(intent);
    }
}
