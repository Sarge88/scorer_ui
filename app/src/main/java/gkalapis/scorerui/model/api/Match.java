package gkalapis.scorerui.model.api;

import java.util.Date;

public class Match {

    private Integer id;

    private Team homeTeam;

    private Team awayTeam;

    private String homeTeamName;

    private String awayTeamName;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    private String status;

    private int round;

    private Date dateTime;

    private String matchResult;

    private boolean favouriteMatch;

    public Match() {}

    public Match(Integer id, Team homeTeam, Team awayTeam, String homeTeamName, String awayTeamName,
                 Integer homeTeamGoals, Integer awayTeamGoals, String status, int round, Date dateTime, String matchResult) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.status = status;
        this.round = round;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMatchResult() { return matchResult; }

    public void setMatchResult(String matchResult) { this.matchResult = matchResult; }

    public boolean isFavouriteMatch() { return favouriteMatch; }

    public void setFavouriteMatch(boolean favouriteMatch) { this.favouriteMatch = favouriteMatch; }

}
