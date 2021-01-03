package com.mihneacristian.report_it.presentation.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mihneacristian.report_it.domain.entity.IssuesEntity;
import com.mihneacristian.report_it.domain.useCase.FetchIssuesUseCase;

import java.util.List;

public class IssuesViewModel extends ViewModel {


    public ObservableArrayList<IssuesEntity> issuesList = new ObservableArrayList<>();
    private final FetchIssuesUseCase fetchIssuesUseCase;

    public IssuesViewModel(FetchIssuesUseCase fetchIssuesUseCase) {
        this.fetchIssuesUseCase = fetchIssuesUseCase;
        LiveData<List<IssuesEntity>> listLiveData = fetchIssuesUseCase.getIssuesUseCase();
        if (listLiveData != null) {
            listLiveData.observeForever(issues -> this.issuesList.addAll(issues));
        }
    }
}