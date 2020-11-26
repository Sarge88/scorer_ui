package gkalapis.scorerui.network;

import java.util.List;

import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.model.api.BetResponse;
import gkalapis.scorerui.model.api.Match;
import gkalapis.scorerui.model.api.Table;
import gkalapis.scorerui.model.api.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ScorerAPI {


    //@GET("/table/")
    //Call<Table> getLeagueTable();

    @GET("/matches/findByStatus?status=SCHEDULED")
    Call<List<Match>> listMatches(); //

    @GET("bet/list")
    Call<List<Bet>> listBets();

    @GET("/users/findAll")
    Call<List<User>> listUsers();

    @POST("users/create")
    Call<String> createUser(@Query("name") String name, @Query("password")String password);

    @POST("bet/create")
    Call<BetResponse> createBet(@Query("matchIds") Integer matchId, @Query("userId")String userId, @Query("homeGoals")Integer homeTeamGoals, @Query("awayGoals")Integer awayTeamGoals);


}
