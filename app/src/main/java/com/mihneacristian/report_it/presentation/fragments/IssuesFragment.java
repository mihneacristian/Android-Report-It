package com.mihneacristian.report_it.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mihneacristian.report_it.R;
import com.mihneacristian.report_it.data.remote.ApplicationAPI;
import com.mihneacristian.report_it.data.remote.RemoteDataSource;
import com.mihneacristian.report_it.databinding.RecyclerViewBinding;
import com.mihneacristian.report_it.domain.mediator.IssuesMediator;
import com.mihneacristian.report_it.domain.repository.IssuesRepository;
import com.mihneacristian.report_it.domain.useCase.FetchIssuesUseCase;
import com.mihneacristian.report_it.presentation.viewmodel.IssuesViewModel;

public class IssuesFragment extends Fragment {

    public IssuesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                IssuesRepository repository = new RemoteDataSource(ApplicationAPI.createAPI());
                IssuesMediator mediator = new IssuesMediator(repository);
                FetchIssuesUseCase useCase = new FetchIssuesUseCase(mediator);
                return (T) new IssuesViewModel(useCase);
            }
        };
        IssuesViewModel viewModel = new ViewModelProvider(this, factory).get(IssuesViewModel.class);

        RecyclerViewBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.recycler_view, container, false);
        binding.setIssuesListViewModel(viewModel);

        return binding.getRoot();
    }
}
