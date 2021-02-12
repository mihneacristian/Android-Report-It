package com.mihneacristian.report_it.data.remote;

import com.mihneacristian.report_it.data.dto.IssuesDTO;
import com.mihneacristian.report_it.domain.repository.IssuesRepository;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class RemoteDataSource implements IssuesRepository {

    private static final String TAG = "RemoteDataSource";
    private final ApplicationAPI applicationAPI;

    public RemoteDataSource(ApplicationAPI api) {
        this.applicationAPI = api;
    }

    @Override
    public List<IssuesDTO> getIssues() {
        try {
            List<IssuesDTO> issuesAPIResponses = applicationAPI.getIssues().execute().body();
            if (issuesAPIResponses != null)
                return issuesAPIResponses;
            return Collections.emptyList();
        } catch (Exception e) {
            Timber.tag(TAG).w("Something went terribly wrong!");
            return Collections.emptyList();
        }
    }
}
