package gkalapis.scorerui.ui.livetable;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Team;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class LiveTableActivity extends DrawerActivity implements LiveTableScreen {

    @Inject
    LiveTablePresenter liveTablePresenter;

    private RecyclerView recyclerViewTeams;
    private ArrayList<Team> teamList;
    private TableAdapter tableAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_livetable, R.string.standing);

        ScorerUiApplication.injector.inject(this);

        recyclerViewTeams = (RecyclerView) findViewById(R.id.recyclerViewTeams);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTeams.setLayoutManager(llm);

        teamList = new ArrayList<>();
        tableAdapter = new TableAdapter(getApplicationContext(), teamList);
        recyclerViewTeams.setAdapter(tableAdapter);

        swipeRefreshLayoutTable = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutTable);
        swipeRefreshLayoutTable.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                liveTablePresenter.refreshTable();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        liveTablePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        liveTablePresenter.detachScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        liveTablePresenter.refreshTable();
    }


    @Override
    public void showLiveTable(List<Team> teams) {
        if (swipeRefreshLayoutTable != null) {
            swipeRefreshLayoutTable.setRefreshing(false);
        }

        teamList.clear();
        teamList.addAll(teams);
        tableAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutTable != null) {
            swipeRefreshLayoutTable.setRefreshing(false);
        }

        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
