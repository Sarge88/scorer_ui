package gkalapis.scorerui.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.api.MatchWrapper;
import gkalapis.scorerui.model.api.Result;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.model.api.Team;
import gkalapis.scorerui.network.FootballDataApi;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockFootballDataApi implements FootballDataApi {
    @Override
    public Call<MatchWrapper> listMatches() {
        final MatchWrapper matchWrapper = new MatchWrapper();
        List<Match> matchList = new ArrayList<>();
        Match match = new Match();
        match.setDateTime(new Date("2018-01-07-08T20:00:00Z"));
        match.setHomeTeamName("Arsenal");
        match.setAwayTeamName("West Ham");

        Result result = new Result();
        result.setGoalsAwayTeam("0");
        result.setGoalsHomeTeam("4");
        matchList.add(match);

        matchWrapper.setMatches(matchList);

        Call<MatchWrapper> fixturesCall = new Call<MatchWrapper>() {
            @Override
            public Response<MatchWrapper> execute() throws IOException {
                return Response.success(matchWrapper);
            }

            @Override
            public void enqueue(Callback<MatchWrapper> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MatchWrapper> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return fixturesCall;
    }

    @Override
    public Call<Table> getLeagueTable() {
        final Table table = new Table();
        List<Team> standing = new ArrayList<>();

        Team team = new Team();
        team.setName("Arsenal");
        standing.add(team);

        table.setStanding(standing);


        Call<Table> tableCall = new Call<Table>() {
            @Override
            public Response<Table> execute() throws IOException {
                return Response.success(table);
            }

            @Override
            public void enqueue(Callback<Table> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Table> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return tableCall;
    }

}
