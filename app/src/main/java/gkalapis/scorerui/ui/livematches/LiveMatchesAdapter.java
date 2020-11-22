package gkalapis.scorerui.ui.livematches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.Match;

public class LiveMatchesAdapter extends RecyclerView.Adapter<LiveMatchesAdapter.ViewHolder> {

    private static ClickListener favClickListener;
    private static SaveClickListener saveClickListener;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Match match = matchList.get(position);

        LocalDateTime dateTime = LocalDateTime.ofInstant(match.getDateTime().toInstant(), ZoneId.systemDefault());
        holder.tvDate.setText(dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth());
        holder.tvTime.setText(dateTime.getHour() + ":" + (dateTime.getMinute() == 0 ? "00" : dateTime.getMinute()));
        holder.tvHomeTeam.setText(match.getHomeTeamName());
        holder.tvAwayTeam.setText(match.getAwayTeamName());

        if(match.getHomeTeamGoals() != null && match.getAwayTeamGoals() != null) {
            holder.tvHomeTeamGoals.setText(match.getHomeTeamGoals().toString());
            holder.tvAwayTeamGoals.setText(match.getAwayTeamGoals().toString());
        }


        if (match.isFavouriteMatch()) {
            holder.favIcon.setImageResource(R.drawable.star_checked);
            holder.favIcon.setTag(R.drawable.star_checked);
        } else {
            holder.favIcon.setImageResource(R.drawable.star_unchecked);
            holder.favIcon.setTag(R.drawable.star_unchecked);
        }

        holder.favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        holder.saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check home and away bet is empty
                saveClickListener.onItemClick(holder.getAdapterPosition(), Integer.valueOf(holder.tvHomeTeamGoals.getText().toString()),Integer.valueOf(holder.tvAwayTeamGoals.getText().toString()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saveIcon;
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
            saveIcon = (ImageView) itemView.findViewById(R.id.saveIcon);
        }

    }

    public void setOnFavClickListener(ClickListener clickListener) {
        LiveMatchesAdapter.favClickListener = clickListener;
    }

    public void setOnSaveClickListener(SaveClickListener clickListener) {
        LiveMatchesAdapter.saveClickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position);
    }

    public interface SaveClickListener {
        void onItemClick(int matchPosition, int homeBet, int awayBet);
    }
}
