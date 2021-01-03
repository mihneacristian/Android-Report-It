package com.mihneacristian.report_it.domain.useCase;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.mihneacristian.report_it.domain.entity.IssuesEntity;
import com.mihneacristian.report_it.domain.mediator.IssuesMediator;

import java.util.List;

public class FetchIssuesUseCase {

    public final IssuesMediator issuesMediator;

    public FetchIssuesUseCase(IssuesMediator issuesMediator) {
        this.issuesMediator = issuesMediator;
    }

    public LiveData<List<IssuesEntity>> getIssuesUseCase() {
        return issuesMediator.getIssuesMediator();
    }
}
