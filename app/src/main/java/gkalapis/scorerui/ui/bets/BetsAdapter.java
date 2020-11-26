package gkalapis.scorerui.ui.bets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Bet;
import gkalapis.scorerui.model.api.Match;


public class BetsAdapter extends RecyclerView.Adapter<BetsAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Bet> betList;

    public BetsAdapter(Context context, ArrayList<Bet> betList) {
        this.context = context;
        this.betList = betList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bet_row, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BetsAdapter.ViewHolder holder, int position) {
        Bet bet = betList.get(position);
        Match match = bet.getMatch();

        LocalDateTime dateTime = LocalDateTime.ofInstant(match.getDateTime().toInstant(), ZoneId.systemDefault());
        holder.tvDate.setText(dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth());
        holder.tvTime.setText(dateTime.getHour() + ":" + (dateTime.getMinute() == 0 ? "00" : dateTime.getMinute()));
        holder.tvHomeTeam.setText(match.getHomeTeamName());
        holder.tvAwayTeam.setText(match.getAwayTeamName());
        holder.tvHomeTeamGoals.setText(match.getHomeTeamGoals() == null ? "-" : match.getHomeTeamGoals().toString());
        holder.tvAwayTeamGoals.setText(match.getAwayTeamGoals() == null ? "-" : match.getAwayTeamGoals().toString());

        holder.tvHomeTeamBetGoals.setText(String.valueOf(bet.getHomeTeamGoals()));
        holder.tvAwayTeamBetGoals.setText(String.valueOf(bet.getAwayTeamGoals()));
    }

    @Override
    public int getItemCount() {
        return betList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate;
        TextView tvTime;
        TextView tvHomeTeam;
        TextView tvAwayTeam;
        TextView tvHomeTeamGoals;
        TextView tvAwayTeamGoals;
        TextView tvHomeTeamBetGoals;
        TextView tvAwayTeamBetGoals;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvHomeTeam = (TextView) itemView.findViewById(R.id.tvHomeTeam);
            tvAwayTeam = (TextView) itemView.findViewById(R.id.tvAwayTeam);
            tvHomeTeamGoals = (TextView) itemView.findViewById(R.id.tvHomeTeamGoals);
            tvAwayTeamGoals = (TextView) itemView.findViewById(R.id.tvAwayTeamGoals);
            tvHomeTeamBetGoals = (TextView) itemView.findViewById(R.id.tvHomeTeamBetGoals);
            tvAwayTeamBetGoals = (TextView) itemView.findViewById(R.id.tvAwayTeamBetGoals);
        }

    }

}
