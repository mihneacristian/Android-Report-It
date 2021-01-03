package com.mihneacristian.report_it.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mihneacristian.report_it.domain.entity.IssuesEntity;
import com.mihneacristian.report_it.domain.useCase.FetchIssuesUseCase;

import java.util.List;

public class MapViewModel extends ViewModel {

    public ObservableArrayList<IssuesEntity> issuesList = new ObservableArrayList<>();
    private final FetchIssuesUseCase fetchIssuesUseCase;

    public MapViewModel(FetchIssuesUseCase fetchIssuesUseCase) {
        this.fetchIssuesUseCase = fetchIssuesUseCase;
        LiveData<List<IssuesEntity>> listLiveData = fetchIssuesUseCase.getIssuesUseCase();
        if (listLiveData != null) {
            listLiveData.observeForever(issues -> this.issuesList.addAll(issues));
        }
    }
}