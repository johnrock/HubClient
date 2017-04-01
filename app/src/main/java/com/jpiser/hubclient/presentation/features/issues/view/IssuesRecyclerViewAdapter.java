package com.jpiser.hubclient.presentation.features.issues.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.models.IssueModel;
import com.jpiser.hubclient.presentation.models.IssueUserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssuesRecyclerViewAdapter extends RecyclerView.Adapter<IssuesRecyclerViewAdapter.IssueHolder> {

    public interface IssueTapper{
        void loadIssue(IssueModel issueModel);
    }

    private List<IssueModel> issueModels;
    private IssueTapper issueTapper;

    public IssuesRecyclerViewAdapter(List<IssueModel> issueModels, IssueTapper issueTapper) {
        this.issueModels = issueModels;
        this.issueTapper = issueTapper;
    }

    @Override
    public IssuesRecyclerViewAdapter.IssueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feaure_issues_issue, parent, false);
        return new IssueHolder(view, issueTapper);
    }

    @Override
    public void onBindViewHolder(IssuesRecyclerViewAdapter.IssueHolder holder, int position) {
        holder.bind(issueModels.get(position));
    }

    @Override
    public int getItemCount() {
        return issueModels.size();
    }

    public static class IssueHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final IssueTapper issueTapper;
        private IssueModel issueModel;

        @BindView(R.id.title) TextView titleTextView;
        @BindView(R.id.state) TextView stateTextView;
        @BindView(R.id.number) TextView numberTextView;
        @BindView(R.id.name) TextView nameTextView;


        public IssueHolder(View itemView, IssueTapper issueTapper) {
            super(itemView);
            this.issueTapper = issueTapper;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            issueTapper.loadIssue(this.issueModel);
        }

        public void bind(IssueModel issueModel) {
            this.issueModel = issueModel;
            titleTextView.setText(issueModel.getTitle());
            stateTextView.setText(issueModel.getState());
            numberTextView.setText("#" + issueModel.getNumber());
            IssueUserModel issueUserModel = issueModel.getIssueUserModel();
            if(issueUserModel != null){
                nameTextView.setText(issueUserModel.getLogin());
            }
        }
    }
}
