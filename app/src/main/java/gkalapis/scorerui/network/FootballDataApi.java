package gkalapis.scorerui.network;

import gkalapis.scorerui.model.api.MatchWrapper;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.model.api.UserWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballDataApi {

    /**
     * Returns list of matches
     * Returns list of matches in the PL in the next 7 days
     * @return Call<Fixtures>
     */
    @GET("/matches/search/findByStatus?status=SCHEDULED")
    //@GET("competitions/{compId}/matches")
    Call<MatchWrapper> listMatches(); //
    /**
     * Returns the league table
     * Returns the league table in the PL on the current matchday
     * @return Call<Table>
     */
    @GET("/users/search/findAllByOrderByPointsDesc")
    Call<Table> getLeagueTable();

    @GET("/users/findAll")
    Call<UserWrapper> listUsers();

}
