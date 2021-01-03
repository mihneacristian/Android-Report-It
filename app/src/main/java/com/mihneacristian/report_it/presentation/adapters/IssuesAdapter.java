package com.mihneacristian.report_it.presentation.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mihneacristian.report_it.R;
import com.mihneacristian.report_it.databinding.FragmentIssuesBinding;
import com.mihneacristian.report_it.domain.entity.IssuesEntity;

import java.util.ArrayList;
import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder> {
    private final List<IssuesEntity> issuesEntityList;

    public IssuesAdapter() {
        this.issuesEntityList = new ArrayList<>();
    }

    @NonNull
    @Override
    public IssuesAdapter.IssuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentIssuesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.fragment_issues,
                parent,
                false);
        return new IssuesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IssuesAdapter.IssuesViewHolder holder, int position) {
        IssuesEntity issuesEntity = issuesEntityList.get(position);
        holder.bind(issuesEntity);
    }

    @Override
    public int getItemCount() {
        return issuesEntityList.size();
    }

    public void updateIssues(List<IssuesEntity> updatedList) {
        this.issuesEntityList.clear();
        this.issuesEntityList.addAll(updatedList);
        notifyDataSetChanged();
    }

    public class IssuesViewHolder extends RecyclerView.ViewHolder {
        private final FragmentIssuesBinding binding;

        public IssuesViewHolder(FragmentIssuesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(IssuesEntity model) {
            binding.setIssuesEntityFragment(model);
        }
    }
}
