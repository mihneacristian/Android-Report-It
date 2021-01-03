package com.mihneacristian.report_it.domain.repository;

import com.mihneacristian.report_it.data.dto.IssuesDTO;

import java.util.List;

public interface IssuesRepository {

    List<IssuesDTO> getIssues();
}
