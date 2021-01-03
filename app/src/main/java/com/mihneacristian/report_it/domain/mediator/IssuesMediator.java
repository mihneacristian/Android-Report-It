package com.mihneacristian.report_it.domain.mediator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mihneacristian.report_it.data.dto.IssuesDTO;
import com.mihneacristian.report_it.domain.repository.IssuesRepository;
import com.mihneacristian.report_it.domain.entity.IssuesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IssuesMediator {

    private final IssuesRepository remoteRepository;
    private final ExecutorService executorService;
    private final MutableLiveData<List<IssuesEntity>> liveDataIssues;

    public IssuesMediator(IssuesRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
        this.executorService = Executors.newSingleThreadExecutor();
        this.liveDataIssues = new MutableLiveData<>();
    }

    public LiveData<List<IssuesEntity>> getIssuesMediator() {

        ArrayList<IssuesEntity> items = new ArrayList<>();
        executorService.execute(() -> {

            for (IssuesDTO dto : remoteRepository.getIssues()) {
                items.add(new IssuesEntity(dto.getIssueTitle(),
                        dto.getIssueDescription(),
                        dto.getIssueType(),
                        dto.getIssueSeverity(),
                        dto.getDateAdded(),
                        dto.getLat(),
                        dto.getLng(),
                        dto.getUserName(),
                        dto.getUserEmailAddress(),
                        dto.getPhotoURL()));
            }
            this.liveDataIssues.postValue(items);
        });
        return liveDataIssues;
    }
}
