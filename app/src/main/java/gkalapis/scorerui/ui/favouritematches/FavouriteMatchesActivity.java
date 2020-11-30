package gkalapis.scorerui.ui.favouritematches;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gkalapis.scorerui.ScorerUiApplication;
import gkalapis.scorerui.R;
import gkalapis.scorerui.model.db.FavouriteMatch;
import gkalapis.scorerui.ui.menu.DrawerActivity;

public class FavouriteMatchesActivity extends DrawerActivity implements FavouriteMatchesScreen{

    @Inject
    FavouriteMatchesPresenter favouriteMatchesPresenter;

    private RecyclerView recyclerViewFavouriteMatches;
    private ArrayList<FavouriteMatch> favouriteMatchesArrayList;
    private FavouriteMatchesAdapter favouriteMatchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addView(R.layout.activity_favouritematches, R.string.favourite_matches);

        ScorerUiApplication.injector.inject(this);

        //HEADER
        ConstraintLayout favouriteMatchesHeader = findViewById(R.id.favourite_matches_header);
        //favouriteMatchesHeader.findViewById(R.id.favIcon).setVisibility(View.GONE);
        ((TextView) favouriteMatchesHeader.findViewById(R.id.tvDate)).setText("Date");
        favouriteMatchesHeader.findViewById(R.id.tvTime).setVisibility(View.GONE);
        ((TextView) favouriteMatchesHeader.findViewById(R.id.tvHomeTeam)).setText("Teams");
        favouriteMatchesHeader.findViewById(R.id.tvAwayTeam).setVisibility(View.GONE);


        recyclerViewFavouriteMatches = (RecyclerView) findViewById(R.id.recyclerViewFavouriteMatches);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFavouriteMatches.setLayoutManager(llm);

        favouriteMatchesArrayList = new ArrayList<>();
        favouriteMatchesAdapter = new FavouriteMatchesAdapter(getApplicationContext(), favouriteMatchesArrayList);
        favouriteMatchesAdapter.setOnItemClickListener(new FavouriteMatchesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                FavouriteMatch favouriteMatch = favouriteMatchesArrayList.get(position);
                favouriteMatchesPresenter.removeFavouriteMatch(favouriteMatch.getId(), getApplicationContext());
                favouriteMatchesPresenter.showFavouriteMatches();
            }
        });

        recyclerViewFavouriteMatches.setAdapter(favouriteMatchesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        favouriteMatchesPresenter.attachScreen(this);
        favouriteMatchesPresenter.showFavouriteMatches();
    }

    @Override
    protected void onStop() {
        super.onStop();
        favouriteMatchesPresenter.detachScreen();
    }

    @Override
    public void showFavouriteMatches(List<FavouriteMatch> favouriteMatches) {
        favouriteMatchesArrayList.clear();
        favouriteMatchesArrayList.addAll(favouriteMatches);
        favouriteMatchesAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeFromFavourites(long rowId) {
        favouriteMatchesPresenter.removeFavouriteMatch(rowId, getApplicationContext());
    }
}
