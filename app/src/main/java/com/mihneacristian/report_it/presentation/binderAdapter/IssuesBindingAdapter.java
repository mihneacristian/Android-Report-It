package com.mihneacristian.report_it.presentation.binderAdapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
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

    @BindingAdapter({"photoURL"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(new CustomTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        //TODO
                    }
                });
    }
}