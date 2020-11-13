package gkalapis.scorerui.model.db;

import com.orm.SugarRecord;

public class FavouriteMatch extends SugarRecord {

    private String date;

    private String homeTeamName;

    private String homeTeamGoals;

    private String awayTeamName;

    private String awayTeamGoals;

    public FavouriteMatch() {
    }

    public FavouriteMatch(String date, String homeTeamName, String homeTeamGoals, String awayTeamName, String awayTeamGoals) {
        this.date = date;
        this.homeTeamName = homeTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamName = awayTeamName;
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(String homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(String awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
}
