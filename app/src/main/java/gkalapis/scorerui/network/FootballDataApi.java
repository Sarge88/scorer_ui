package gkalapis.scorerui.network;

import java.util.List;

import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.model.api.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballDataApi {

    /**
     * Returns list of matches
     * Returns list of matches in the PL in the next 7 days
     * @return Call<Fixtures>
     */
    @GET("/matches/findByStatus?status=SCHEDULED")
    Call<List<Match>> listMatches(); //
    /**
     * Returns the league table
     * Returns the league table in the PL on the current matchday
     * @return Call<Table>
     */
    //@GET("/table/")
    Call<Table> getLeagueTable();

    @GET("/users/findAll")
    Call<List<User>> listUsers();
}
