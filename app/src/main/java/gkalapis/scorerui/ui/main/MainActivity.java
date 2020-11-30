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

    LinearLayout inputContainer;
    TextView welcomeTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_main, R.string.welcome);

        inputContainer = findViewById(R.id.input_container);
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
                String name = ((TextView)findViewById(R.id.name_input_field)).getText().toString();
                String password = ((TextView)findViewById(R.id.password_input_field)).getText().toString();

                if(name.length() > 0 && password.length() > 0){
                    mainPresenter.register(getApplicationContext(), name, password);
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
                String name = ((TextView)findViewById(R.id.name_input_field)).getText().toString();
                String password = ((TextView)findViewById(R.id.password_input_field)).getText().toString();
                if(name.length() > 0 && password.length() > 0){
                    mainPresenter.restore(getApplicationContext(), name, password);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please fill both name and password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        welcomeTextview = findViewById(R.id.tvWelcome);

        if(!mainPresenter.isRegisterVisible(getApplicationContext())){
            welcomeTextview.setText(String.format(getString(R.string.welcome_text), mainPresenter.getUserName(getApplicationContext()))); //userCacheInteractor
        }
        else
        {
            welcomeTextview.setText(String.format(getString(R.string.welcome_text), "")); //userCacheInteractor
        }

        if(!mainPresenter.isRegisterVisible(getApplicationContext())) {
            inputContainer.setVisibility(View.GONE);
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
        inputContainer.setVisibility(View.GONE);
        welcomeTextview.setText(String.format(getString(R.string.welcome_text), mainPresenter.getUserName(getApplicationContext()))); //userCacheInteractor
        Toast.makeText(getApplicationContext(), name + " is successfully created.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userSuccessfullyRestored(String name) {
        inputContainer.setVisibility(View.GONE);
        welcomeTextview.setText(String.format(getString(R.string.welcome_text), mainPresenter.getUserName(getApplicationContext()))); //userCacheInteractor
        Toast.makeText(getApplicationContext(), name + " is successfully restored.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showRegisterError() {
        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRestoreError() {
        Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String errorMsg){
        Toast.makeText(getApplicationContext(), "Something went wrong" + errorMsg, Toast.LENGTH_SHORT).show();
    }
}
