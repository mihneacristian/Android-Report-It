package com.mihneacristian.report_it.presentation.binderAdapter;

import android.widget.ProgressBar;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mihneacristian.report_it.domain.entity.IssuesEntity;
import com.mihneacristian.report_it.presentation.adapters.IssuesAdapter;

import java.util.List;

public class IssuesBindingAdapter {

    @BindingAdapter("allIssuesList")
    public static void setIssues(RecyclerView recyclerView, List<IssuesEntity> issues) {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new IssuesAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        if (issues != null) {
            ((IssuesAdapter) adapter).updateIssues(issues);
        }
    }
}