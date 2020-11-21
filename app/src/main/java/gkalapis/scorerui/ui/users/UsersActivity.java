package gkalapis.scorerui.ui.users;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.R;
import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.api.User;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.livematches.LiveMatchesAdapter;
import gkalapis.scorerui.ui.livematches.LiveMatchesPresenter;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class UsersActivity extends DrawerActivity implements UsersScreen {

    @Inject
    UsersPresenter usersPresenter;

    private RecyclerView recyclerViewMatches;
    private ArrayList<User> userArrayList;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_users, R.string.users); // strings.xml
        ScorerUiApplication.injector.inject(this);

        recyclerViewMatches = (RecyclerView) findViewById(R.id.userList); //activity users
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMatches.setLayoutManager(llm);

        userArrayList = new ArrayList<>();
        usersAdapter = new UsersAdapter(userArrayList);

        recyclerViewMatches.setAdapter(usersAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        usersPresenter.attachScreen(this);
        usersPresenter.showUsers();
    }

    @Override
    protected void onStop() {
        super.onStop();
        usersPresenter.detachScreen();
    }


    @Override
    public void showUsers(List<User> users) {
        userArrayList.clear();
        userArrayList.addAll(users);
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNetworkError(String errorMsg) {

    }


}
