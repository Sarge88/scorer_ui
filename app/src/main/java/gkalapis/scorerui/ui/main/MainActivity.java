package gkalapis.scorerui.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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


        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((TextView)findViewById(R.id.register_name_input_field)).getText().toString();
                String password = ((TextView)findViewById(R.id.register_password_input_field)).getText().toString();

                if(name.length() > 0 && password.length() > 0){
                    mainPresenter.register(getApplicationContext(), ((TextView)findViewById(R.id.register_name_input_field)).getText().toString(),((TextView)findViewById(R.id.register_password_input_field)).getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please fill both name and password!", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button btnRestore = (Button) findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.restore();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        LinearLayout registerContainer = findViewById(R.id.register_container);
        LinearLayout restoreContainer = findViewById(R.id.restore_container);

        if(!mainPresenter.isRegisterVisible(getApplicationContext())) {
            restoreContainer.setVisibility(View.GONE);
            registerContainer.setVisibility(View.GONE);
        }
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

    @Override
    public void userSuccessfullyRegistered(String name) {
        Toast.makeText(getApplicationContext(), name + " is successfully created.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(getApplicationContext(), "Problem is: "+ errorMsg, Toast.LENGTH_SHORT).show();
    }
}
