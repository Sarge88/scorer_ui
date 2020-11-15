package gkalapis.scorerui.ui.livematches;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.PlScoresApplication;
import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.api.Result;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class LiveMatchesActivity extends DrawerActivity implements LiveMatchesScreen {

    @Inject
    LiveMatchesPresenter liveMatchesPresenter;

    private RecyclerView recyclerViewMatches;
    private ArrayList<Match> matchArrayList;
    private List<FavouriteMatch> favouriteMatchList;
    private LiveMatchesAdapter liveMatchesAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_livematches, R.string.actual_matches);

        PlScoresApplication.injector.inject(this);

        recyclerViewMatches = (RecyclerView) findViewById(R.id.recyclerViewMatches);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMatches.setLayoutManager(llm);

        matchArrayList = new ArrayList<>();
        liveMatchesAdapter = new LiveMatchesAdapter(getApplicationContext(), matchArrayList);
        liveMatchesAdapter.setOnItemClickListener(new LiveMatchesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Match match = matchArrayList.get(position);

                if (match.isFavouriteMatch()) {
                    Toast.makeText(getApplicationContext(), "Please use the Favourite matches screen to remove matches from favourites", Toast.LENGTH_LONG).show();
                } else {
                    FavouriteMatch favouriteMatch = createFavouriteMatchFromFixture(match);
                    addMatchToFavourites(favouriteMatch);
                    Toast.makeText(getApplicationContext(), "Match added to favourites!", Toast.LENGTH_LONG).show();
                }

                updateFixtures(liveMatchesPresenter.getFavouriteMatches());
                liveMatchesAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewMatches.setAdapter(liveMatchesAdapter);

        swipeRefreshLayoutMatches = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutTable);
        swipeRefreshLayoutMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                liveMatchesPresenter.refreshMatches();
            }
        });
    }

    private void updateFixtures(List<FavouriteMatch> favouriteMatches) {
        for (Match match : matchArrayList) {
            for (FavouriteMatch favouriteMatch : favouriteMatches) {
                if (isFixtureAndFavMatchSame(match, favouriteMatch)) {
                    //match.setFavourite(true);
                }
            }
        }
    }

    private boolean isFixtureAndFavMatchSame(Match match, FavouriteMatch favouriteMatch) {
        return match.getDateTime().equals(favouriteMatch.getDate()) && match.getHomeTeamName().equals(favouriteMatch.getHomeTeamName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        liveMatchesPresenter.attachScreen(this);
        favouriteMatchList = liveMatchesPresenter.getFavouriteMatches();
        liveMatchesPresenter.refreshMatches();
    }

    @Override
    protected void onStop() {
        super.onStop();
        liveMatchesPresenter.detachScreen();
    }

    @Override
    public void showLiveMatches(List<Match> matches) {
        if (swipeRefreshLayoutMatches != null) {
            swipeRefreshLayoutMatches.setRefreshing(false);
        }

        matchArrayList.clear();
        matchArrayList.addAll(matches);
        updateFixtures(favouriteMatchList);
        liveMatchesAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMatchToFavourites(FavouriteMatch favouriteMatch) {
        liveMatchesPresenter.addFavouriteMatch(favouriteMatch);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutMatches != null) {
            swipeRefreshLayoutMatches.setRefreshing(false);
        }
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    private FavouriteMatch createFavouriteMatchFromFixture(Match match) {
        FavouriteMatch favouriteMatch = new FavouriteMatch();

        favouriteMatch.setDate(match.getDateTime().toString());
        favouriteMatch.setHomeTeamName(match.getHomeTeamName());
        favouriteMatch.setAwayTeamName(match.getAwayTeamName());

        // TODO: possible NPE
        favouriteMatch.setHomeTeamGoals(String.valueOf(match.getHomeTeamGoals()));
        favouriteMatch.setAwayTeamGoals(String.valueOf(match.getAwayTeamGoals()));

        return favouriteMatch;
    }

}
