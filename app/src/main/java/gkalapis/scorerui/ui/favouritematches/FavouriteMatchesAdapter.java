package gkalapis.scorerui.ui.favouritematches;

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
import gkalapis.scorerui.model.db.FavouriteMatch;

public class FavouriteMatchesAdapter extends RecyclerView.Adapter<FavouriteMatchesAdapter.ViewHolder> {

    private static FavouriteMatchesAdapter.ClickListener clickListener;
    private Context context;
    private ArrayList<FavouriteMatch> favouriteMatchList;

    public FavouriteMatchesAdapter(Context context, ArrayList<FavouriteMatch> favouriteMatchList) {
        this.context = context;
        this.favouriteMatchList = favouriteMatchList;
    }

    public FavouriteMatchesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourites_row, viewGroup, false);
        return new FavouriteMatchesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavouriteMatchesAdapter.ViewHolder holder, int position) {
        FavouriteMatch favouriteMatch = favouriteMatchList.get(position);

        LocalDateTime dateTime = LocalDateTime.ofInstant(favouriteMatch.getDate().toInstant(), ZoneId.systemDefault());
        holder.tvDate.setText(dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth());
        holder.tvTime.setText(dateTime.getHour() + ":" + (dateTime.getMinute() == 0 ? "00" : dateTime.getMinute()));

        holder.tvHomeTeam.setText(favouriteMatch.getHomeTeamName());
        holder.tvAwayTeam.setText(favouriteMatch.getAwayTeamName());
        holder.tvHomeTeamGoals.setText(favouriteMatch.getHomeTeamGoals());
        holder.tvAwayTeamGoals.setText(favouriteMatch.getAwayTeamGoals());

        holder.favIcon.setImageResource(R.drawable.star_checked);
    }

    @Override
    public int getItemCount() {
        return favouriteMatchList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        FavouriteMatchesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }

}