package gkalapis.scorerui.ui.livematches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Match;

public class LiveMatchesAdapter extends RecyclerView.Adapter<LiveMatchesAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private Context context;
    private ArrayList<Match> matchList;

    public LiveMatchesAdapter(Context context, ArrayList<Match> matchList) {
        this.context = context;
        this.matchList = matchList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_row, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { //ez jelenit meg egy sort,  holder az objektum
        Match match = matchList.get(position);

        String[] dateAndTime = match.getDateTime().toString().split("T");
        holder.tvDate.setText(dateAndTime[0]);
        holder.tvTime.setText(dateAndTime[1].substring(0, dateAndTime[1].lastIndexOf(":")));
        holder.tvHomeTeam.setText(match.getHomeTeamName());
        holder.tvAwayTeam.setText(match.getAwayTeamName());

        holder.tvHomeTeamGoals.setText(match.getHomeTeamGoals() == null ? "-" : match.getHomeTeamGoals().toString());
        holder.tvAwayTeamGoals.setText(match.getAwayTeamGoals() == null ? "-" : match.getAwayTeamGoals().toString());

        // TODO
        //if (match.isFavourite()) {
        if (false) {
            holder.favIcon.setImageResource(R.drawable.star_checked);
            holder.favIcon.setTag(R.drawable.star_checked);
        } else {
            holder.favIcon.setImageResource(R.drawable.star_unchecked);
            holder.favIcon.setTag(R.drawable.star_unchecked);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDate;
        TextView tvTime;
        TextView tvHomeTeam;
        TextView tvAwayTeam;
        TextView tvHomeTeamGoals;
        TextView tvAwayTeamGoals;
        ImageView favIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvHomeTeam = (TextView) itemView.findViewById(R.id.tvHomeTeam);
            tvAwayTeam = (TextView) itemView.findViewById(R.id.tvAwayTeam);
            tvHomeTeamGoals = (TextView) itemView.findViewById(R.id.tvHomeTeamGoals);
            tvAwayTeamGoals = (TextView) itemView.findViewById(R.id.tvAwayTeamGoals);
            favIcon = (ImageView) itemView.findViewById(R.id.favIcon);
            favIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        LiveMatchesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }
}
