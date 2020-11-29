package gkalapis.scorerui.ui.bets;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.R;
import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class BetsActivity extends DrawerActivity implements BetsScreen {

    @Inject
    BetsPresenter betsPresenter;

    private RecyclerView recyclerViewBets;
    private ArrayList<Bet> betArrayList;
    private BetsAdapter betsAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutBets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_bets, R.string.bets);

        ScorerUiApplication.injector.inject(this);

        //HEADER
        ConstraintLayout betHeader = findViewById(R.id.bet_header);

        ((TextView) betHeader.findViewById(R.id.tvDate)).setText("Date");
        betHeader.findViewById(R.id.tvTime).setVisibility(View.GONE);
        ((TextView) betHeader.findViewById(R.id.tvHomeTeam)).setText("Teams");
        betHeader.findViewById(R.id.tvAwayTeam).setVisibility(View.GONE);
        ((TextView) betHeader.findViewById(R.id.tvHomeTeamGoals)).setText("Goals");
        betHeader.findViewById(R.id.tvAwayTeamGoals).setVisibility(View.GONE);
        ((TextView) betHeader.findViewById(R.id.tvHomeTeamBetGoals)).setText("Bets");
        betHeader.findViewById(R.id.tvAwayTeamBetGoals).setVisibility(View.GONE);

        recyclerViewBets = (RecyclerView) findViewById(R.id.recyclerViewBets);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBets.setLayoutManager(llm);

        betArrayList = new ArrayList<>();
        betsAdapter = new BetsAdapter(getApplicationContext(), betArrayList);


        recyclerViewBets.setAdapter(betsAdapter);
    }

    @Override
    public void showBets(List<Bet> bets) {
        if (swipeRefreshLayoutBets != null) {
            swipeRefreshLayoutBets.setRefreshing(false);
        }

        betArrayList.clear();
        betArrayList.addAll(bets);
        betsAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onStart() {
        super.onStart();
        betsPresenter.attachScreen(this);
        betsPresenter.showBets(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        betsPresenter.detachScreen();
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutBets != null) {
            swipeRefreshLayoutBets.setRefreshing(false);
        }
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }


}
