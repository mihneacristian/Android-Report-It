package com.mihneacristian.report_it.presentation.adapters;

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

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {

    private final List<IssuesEntity> issuesEntityList;

    public MapAdapter() {
        this.issuesEntityList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MapAdapter.MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentIssuesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.fragment_map,
                parent,
                false);
        return new MapViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MapAdapter.MapViewHolder holder, int position) {
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

    public class MapViewHolder extends RecyclerView.ViewHolder {

        private final FragmentIssuesBinding binding;

        public MapViewHolder(FragmentIssuesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(IssuesEntity model) {
            binding.setIssuesEntityFragment(model);
        }
    }
}
