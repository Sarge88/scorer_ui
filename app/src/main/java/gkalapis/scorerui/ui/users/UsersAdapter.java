package gkalapis.scorerui.ui.users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gkalapis.scorerui.R;
import gkalapis.scorerui.model.api.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public UsersAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    private ArrayList<User> userList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvUserName.setText(user.getName());
        holder.tvPosition.setText(String.valueOf(position + 1));
        holder.tvPoints.setText(String.valueOf(user.getPoints()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        TextView tvPosition;
        TextView tvPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
        }
    }
}
