package com.jpiser.hubclient.presentation.features.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.features.profile.model.RepoModel;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.RepoHolder>{

    public interface RepoTapper{
        void onRepoTapped(RepoModel repoModel);
    }

    List<RepoModel> repoModels;
    RepoTapper repoTapper;

    public ProfileRecyclerViewAdapter(RepoTapper repoTapper, List<RepoModel> repoModels) {
        this.repoModels = repoModels;
        this.repoTapper = repoTapper;
    }

    @Override
    public ProfileRecyclerViewAdapter.RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_profile_repo, parent, false);
        return new RepoHolder(view, repoTapper);
    }

    @Override
    public void onBindViewHolder(ProfileRecyclerViewAdapter.RepoHolder holder, int position) {
        holder.bind(repoModels.get(position));
    }

    @Override
    public int getItemCount() {
        return repoModels.size();
    }

    public static class RepoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nameTextView;
        private TextView descriptionTextView;
        private final RepoTapper repoTapper;
        private RepoModel repo;

        public RepoHolder(View itemView, RepoTapper repoTapper) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            this.repoTapper = repoTapper;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            repoTapper.onRepoTapped(repo);
        }

        public void bind(RepoModel repoModel) {
            this.repo = repoModel;
            nameTextView.setText(repoModel.getName());
            descriptionTextView.setText(repoModel.getDescription());
        }
    }
}
