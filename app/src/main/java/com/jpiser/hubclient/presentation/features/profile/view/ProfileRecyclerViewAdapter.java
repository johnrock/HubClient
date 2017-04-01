package com.jpiser.hubclient.presentation.features.profile.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.models.RepoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        @BindView(R.id.name)        TextView nameTextView;
        @BindView(R.id.description) TextView descriptionTextView;
        @BindView(R.id.language)    TextView languageTextView;
        @BindView(R.id.stargazers)  TextView stargazersTextView;
        @BindView(R.id.forks)       TextView forksTextView;

        private final RepoTapper repoTapper;
        private RepoModel repo;

        public RepoHolder(View itemView, RepoTapper repoTapper) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
            languageTextView.setText(repoModel.getLanguage());
            stargazersTextView.setText(String.valueOf(repoModel.getStargazersCount()));
            forksTextView.setText(String.valueOf(repoModel.getForksCount()));
        }
    }
}
