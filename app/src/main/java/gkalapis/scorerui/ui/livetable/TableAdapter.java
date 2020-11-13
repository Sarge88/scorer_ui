package gkalapis.scorerui.ui.livetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Team;

public class TableAdapter extends Adapter<TableAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Team> teamList;

    public TableAdapter(Context context, ArrayList<Team> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.table_row, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = teamList.get(position);

        holder.tvRank.setText(String.valueOf(position+1));
//        holder.tvTeam.setText(team.getTeamName());
//        holder.tvPlayed.setText(String.valueOf(team.getPlayedGames()));
//        holder.tvWin.setText(String.valueOf(team.getWins()));
//        holder.tvDraw.setText(String.valueOf(team.getDraws()));
//        holder.tvLoose.setText(String.valueOf(team.getLosses()));
//        holder.tvGoals.setText(team.getGoals() + ":" + team.getGoalsAgainst());
//        holder.tvPoints.setText(String.valueOf(team.getPoints()));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRank;
        TextView tvTeam;
        TextView tvPlayed;
        TextView tvWin;
        TextView tvDraw;
        TextView tvLoose;
        TextView tvGoals;
        TextView tvPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRank = (TextView) itemView.findViewById(R.id.tvRank);
            tvTeam = (TextView) itemView.findViewById(R.id.tvTeam);
            tvPlayed = (TextView) itemView.findViewById(R.id.tvPlayed);
            tvWin = (TextView) itemView.findViewById(R.id.tvWin);
            tvDraw = (TextView) itemView.findViewById(R.id.tvDraw);
            tvLoose = (TextView) itemView.findViewById(R.id.tvLoose);
            tvGoals = (TextView) itemView.findViewById(R.id.tvGoals);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
        }
    }
}
